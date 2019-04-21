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

		router.route("/digidata/trading/add").handler(this::addTradingResult);

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
				+ "/digidata/strategy/active \t\t with <name,version> parameters\n" + "/digidata/trading/records \t\t last "
				+ Config.TRADING_MAX_RESULTS + " records\n";
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
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end((trades != null ? format(trades) : ""));
	}

	private String format(List<Trade> trades) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"ID" + "\tSESSION" + "\t\t\tACCOUNT" + "\t\tINSTRUMENT" + "\tCONNECTION" + "\tSTRATEGY" + "\tRESULT\n");
		for (Trade trade : trades) {
			sb.append(trade.getId() + "\t" + trade.getSession() + "\t" + trade.getAccount() + "\t\t"
					+ trade.getInstrument() + "\t\t" + trade.getConnection() + "\t\t" + trade.getStrategy() + "\t"
					+ trade.getResult().setScale(4, RoundingMode.HALF_UP) + "\n");
		}
		return sb.toString();
	}

	private void addTradingResult(RoutingContext routingContext) {
		Trade t = new Trade();
		t.setSession(routingContext.request().getParam("session"));
		t.setAccount(routingContext.request().getParam("account"));
		t.setConnection(routingContext.request().getParam("connection"));
		t.setInstrument(routingContext.request().getParam("instrument"));
		t.setStrategy(routingContext.request().getParam("strategy"));
		String result = routingContext.request().getParam("result");
		if (result.indexOf(",") != -1) {
			result = result.replace(",", ".");
		}
		t.setResult(new BigDecimal(result));
		logger.log(Level.INFO, "New trading record received");
		Repository.getInstance().save(t);
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end("Added " + t.getId());
	}

}