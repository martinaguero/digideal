package org.trimatek.digidata;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Server extends AbstractVerticle {

	private final static Logger logger = Logger.getLogger(Server.class.getName());
	private final static int PORT = 8282;

	@Override
	public void start(Future<Void> fut) {

		Router router = Router.router(vertx);

		router.route("/digidata/serial").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setStatusCode(200);
			response.putHeader("content-type", "text/plain; charset=utf-8").end(Serializer.getInstance().newSerial());
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

	private void serialGen(RoutingContext routingContext) {
		routingContext.response().setStatusCode(201).putHeader("content-type", "text/plain; charset=utf-8")
				.end(Serializer.getInstance().newSerial());
	}
}