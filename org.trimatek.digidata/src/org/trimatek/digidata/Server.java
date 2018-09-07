package org.trimatek.digidata;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.trimatek.digidata.btc.fee.FeeLookup;
import org.trimatek.digidata.btc.fee.FeeLookup.FEES;
import org.trimatek.digidata.btc.fee.RateLookup;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

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

		router.route("/digidata/rate/btc/usd").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(rateLookup.getUsdRate());
		});

		router.route("/digidata/help").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(printHelp());
		});

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
		return "/digidata/serial \t\t\t Unique serial from: base36(epoch+counter)\n"
				+ "/digidata/fee/[fast/mid/slow] \t\t Fee prediction for confirmation from: "
				+ Config.BTC_FEE_PREDICTION_EARN_URL + " updated every " + Config.BTC_FEE_MINUTES_TO_UPDATE
				+ " minutes\n" + "/digidata/fee/[fast/mid/slow]/hist \t Historic fee prediction from: "
				+ Config.BTC_FEE_PREDICTION_EARN_URL + " updated every " + Config.BTC_FEE_HOURS_TO_UPDATE_HISTORIC
				+ " hours\n" + "/digidata/fee/status \t\t\t Sum of historic fee prediction received samples\n"
				+ "/digidata/rate/btc/usd \t\t\t Exchange rate from: " + Config.BTC_USD_RATE_BCINFO_URL
				+ " updated every " + Config.BTC_RATE_HOURS_UPDATE + " hours\n";
	}

}