package org.trimatek.digideal.comm.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.compiler.actions.Compile;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.model.Source;

import com.google.gson.Gson;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle implements Launcher {
	
	protected final static Logger logger = Logger.getLogger(Server.class.getName());
	private int PORT = 9090;
	
	public Server(int port) {
		PORT = port;
	}

	public static void main(String args[]) {
		Server server = new Server(9090);
		server.init();
	}

	public void init() {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Server(PORT), ar -> {
			if (ar.failed()) {
				ar.cause().printStackTrace();
			}
		});
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {

		Router router = Router.router(vertx);

		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Hi, I'm DigiDeal</h1>");
		});

		router.route("/api/drafts*").handler(BodyHandler.create());
		router.post("/api/drafts").handler(this::addOne);

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", PORT),
				result -> {
					if (result.succeeded()) {
						startFuture.complete();
						logger.log(Level.INFO, "DigiDeal REST server ready");
					} else {
						logger.log(Level.INFO, "DigiDeal REST server init fail");
						startFuture.fail(result.cause());
					}
				});

	}

	private void addOne(RoutingContext routingContext) {

		String message = "Compile fail";
		Source source = new Gson().fromJson(routingContext.getBodyAsString(), Source.class);

		if (source != null) {
			Contract cnt = new Contract(source);
			Compile c = new Compile(); 
			cnt = c.exec(cnt);
		}

		System.out.println(source.getText());

		routingContext.response().setStatusCode(201).putHeader("content-type", "text/plain; charset=utf-8")
				.end(message);
	}

}