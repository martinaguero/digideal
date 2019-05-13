package org.trimatek.digidata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.trimatek.digidata.btc.fee.FeeLookup;
import org.trimatek.digidata.btc.fee.FeeLookup.FEES;
import org.trimatek.digidata.btc.fee.RateLookup;
import org.trimatek.digidata.trading.Repository;
import org.trimatek.digidata.trading.model.Strategy;
import org.trimatek.digidata.trading.model.Trade;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {

	private final static int PORT = 8282;
	private static Logger logger;
	static {
		InputStream inputStream = Server.class.getResourceAsStream("/logging.properties");
		if (null != inputStream) {
			try {
				LogManager.getLogManager().readConfiguration(inputStream);

			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, "init logging system", e);
			}
		}
	}

	@Override
	public void start(Future<Void> fut) {

		Router router = Router.router(vertx);
		FeeLookup feeLookup = FeeLookup.getInstance();
		RateLookup rateLookup = RateLookup.getInstance();

		router.route("/digidata/serial").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(Serializer.getInstance().newSerial());
		});

		router.route("/digidata/fee/fast").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getFee(FEES.FAST));
		});

		router.route("/digidata/fee/slow").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getFee(FEES.SLOW));
		});

		router.route("/digidata/fee/mid").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getFee(FEES.MID));
		});

		router.route("/digidata/fee/fast/hist").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getHisto(FEES.FAST));
		});

		router.route("/digidata/fee/slow/hist").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getHisto(FEES.SLOW));
		});

		router.route("/digidata/fee/mid/hist").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getHisto(FEES.MID));
		});

		router.route("/digidata/fee/status").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getStatus());
		});

		router.route("/digidata/rates/btc").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(rateLookup.getRates());
		});

		router.route("/digidata/help").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(printHelp());
		});

		router.route("/digidata/strategy/active").handler(this::getStrategy);

		router.route("/digidata/trading/records").handler(this::getTradingRecords);

		router.route(Config.ROUTE_TRADE_LOG).handler(this::getTradeLog);

		router.route("/digidata/trading*").handler(BodyHandler.create());
		router.post("/digidata/trading/add").handler(this::addTradingResult);

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", PORT),
				result -> {
					if (result.succeeded()) {
						fut.complete();
						logger.log(Level.INFO, "DigiData server ready");
					} else {
						fut.fail(result.cause());
						logger.log(Level.SEVERE, "DigiData server init fail");
					}
				});
	}

	public static final void main(String args[]) throws SecurityException, IOException {

		logger = Logger.getLogger(Server.class.getCanonicalName());
		logger.log(Level.INFO, "Ready logging");

		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Server(), ar -> {
			if (ar.failed()) {
				ar.cause().printStackTrace();
			}
		});
	}

	private String printHelp() {
		return "DigiData v." + Config.DIGIDATA_VERSION + "\n"
				+ "/digidata/serial \t\t\t Unique serial from: base36(epoch+counter)\n"
				+ "/digidata/fee/[fast/mid/slow] \t\t Fee prediction for confirmation from: "
				+ Config.BTC_FEE_PREDICTION_EARN_URL + " updated every " + Config.BTC_FEE_MINUTES_TO_UPDATE
				+ " minutes\n" + "/digidata/fee/[fast/mid/slow]/hist \t Historic fee prediction from: "
				+ Config.BTC_FEE_PREDICTION_EARN_URL + " updated every " + Config.BTC_FEE_HOURS_TO_UPDATE_HISTORIC
				+ " hours\n" + "/digidata/fee/status \t\t\t Sum of historic fee prediction received samples\n"
				+ "/digidata/rates/btc \t\t\t Exchange rates from: " + "https://blockchain.info/" + " updated every "
				+ Config.BTC_RATE_HOURS_UPDATE + " hours\n"
				+ "/digidata/strategy/active \t\t with <name,version> parameters\n"
				+ "/digidata/trading/records \t\t last " + Config.TRADING_MAX_RESULTS + " records\n"
				+ "/digidata/trade/log \t\t\t with <id> parameter\n";
	}

	private void getStrategy(RoutingContext routingContext) {
		String name = routingContext.request().getParam("name");
		String version = routingContext.request().getParam("version");
		logger.log(Level.INFO, "New strategy status request for = " + name + "/" + version);
		Strategy s = Repository.getInstance().loadStrategy(name, version);
		if (s != null) {
			Repository.getInstance().refreshStrategy(s);
		}
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end((s != null ? s.isActive() : false) + "");
	}

	private void getTradingRecords(RoutingContext routingContext) {
		logger.log(Level.INFO, "New trading records request");
		List<Trade> trades = Repository.getInstance().loadTrades();
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/html; charset=utf-8")
				.end((trades != null ? format(trades) : ""));
	}

	private String format(List<Trade> trades) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Last 100 trades</title><style>\r\n"
				+ "th {text-align: left;}" + " </style>\r " + "</head>\r\n" + "<body>");
		sb.append("<table style=\"width:100%\">\r\n" + "  <tr>");
		sb.append("<th>ID</th>" + "<th>SESSION</th>" + "<th>ACCOUNT</th>" + "<th>INSTRUMENT</th>"
				+ "<th>CONNECTION</th>" + "<th>STRATEGY</th>" + "<th>RESULT</th>" + "<th>LOG</th>");
		sb.append("</tr>");
		String url, bar = null;
		for (Trade trade : trades) {
			url = Config.SERVER_NAME + ":" + PORT + Config.ROUTE_TRADE_LOG + "?id=" + trade.getId();
			bar = trade.getSession().substring(trade.getSession().indexOf("[") + 1,
					trade.getSession().indexOf("]"));
			sb.append("<tr>\n");
			sb.append("<td>" + trade.getId() + "</td>" + "<td>" + trade.getSession() + "</td>" + "<td>"
					+ trade.getAccount() + "</td>" + "<td>" + trade.getInstrument() + "</td>" + "<td>"
					+ trade.getConnection() + "</td>" + "<td>" + trade.getStrategy() + "</td>" + "<td>"
					+ trade.getResult().setScale(4, RoundingMode.HALF_UP) + "</td>" + "<td>" + "<a href=http://" + url
					+ ">[" + bar + "]</a>" + "</td>");
			sb.append("</tr>\n");
		}
		sb.append("</body>\r\n" + "</html>");
		return sb.toString();
	}

	private void addTradingResult(RoutingContext routingContext) {
		Trade t = new Trade();
		t.setSession(routingContext.request().getParam("session"));
		t.setAccount(routingContext.request().getParam("account"));
		t.setConnection(routingContext.request().getParam("connection"));
		t.setInstrument(routingContext.request().getParam("instrument"));
		t.setStrategy(routingContext.request().getParam("strategy"));
		t.setLog(routingContext.getBodyAsString());
		String result = routingContext.request().getParam("result");
		if (result.indexOf(",") != -1) {
			result = result.replace(",", ".");
		}
		t.setResult(new BigDecimal(result));
		logger.log(Level.INFO,
				"New trading record received for: " + t.getLog().substring(0, t.getLog().indexOf("]") + 1));
		Repository.getInstance().save(t);
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end("Added " + t.getId());
	}

	private void getTradeLog(RoutingContext routingContext) {
		logger.log(Level.INFO, "New trade log request");
		String id = routingContext.request().getParam("id");
		Trade t = Repository.getInstance().loadTrade(Long.parseLong(id));
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end((t != null ? t.getLog() : ""));
	}

}