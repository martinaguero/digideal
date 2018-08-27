package org.trimatek.digidata;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digidata.fee.FeeLookup;
import org.trimatek.digidata.fee.FeeLookup.FEES;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Server extends AbstractVerticle {

	private final static Logger logger = Logger.getLogger(Server.class.getName());
	private final static int PORT = 8282;

	@Override
	public void start(Future<Void> fut) {

		Router router = Router.router(vertx);
		FeeLookup feeLookup = FeeLookup.getInstance();

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
		
		router.route("/digidata/fee/err").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(feeLookup.getErrors());
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

	public static final void main(String args[]) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Server(), ar -> {
			if (ar.failed()) {
				ar.cause().printStackTrace();
			}
		});
	}

}