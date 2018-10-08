package org.trimatek.digideal.comm.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.compiler.actions.Compile;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.model.Source;
import org.trimatek.digideal.model.Ticket;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.repo.RepositorySupport;

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
		RepositorySupport.getInstance();
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
			response.setStatusCode(200);
			response.putHeader("content-type", "text/html")
					.end("<h1>Hi, I'm DigiDeal, version " + Config.getValue("DIGIDEAL_VERSION") + "</h1>");
		});

		router.route("/api/sources*").handler(BodyHandler.create());
		router.post("/api/sources").handler(this::addSource);

		router.route("/api/tickets*").handler(BodyHandler.create());
		router.post("/api/tickets").handler(this::addTicket);

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

	private void addSource(RoutingContext routingContext) {
		String result = null;
		int status = 201;
		Source source = new Gson().fromJson(routingContext.getBodyAsString(), Source.class);
		if (source != null) {
			logger.log(Level.INFO, "Ready to compile: " + source.getText());
			Contract cnt = new Contract(source);
			Compile c = new Compile();
			cnt = c.exec(cnt);
			if (cnt != null) {
				result = "Compile success";
			} else {
				result = "Compile failed";
				status = 409;
			}
		}
		routingContext.response().setStatusCode(status).putHeader("content-type", "text/plain; charset=utf-8")
				.end(result);
	}

	private void addTicket(RoutingContext routingContext) {
		String result = null;
		int status = 201;

		Ticket ticket = new Gson().fromJson(routingContext.getBodyAsString(), Ticket.class);
		if (ticket != null) {
			logger.log(Level.INFO, "New ticket for: " + ticket.getDealId());
			RepositorySupport.getInstance().save(ticket);
			result = "Ticket received";
		} else {
			result = "Ticket could not be received";
			status = 409;
		}
		routingContext.response().setStatusCode(status).putHeader("content-type", "text/plain; charset=utf-8")
				.end(result);
	}

}