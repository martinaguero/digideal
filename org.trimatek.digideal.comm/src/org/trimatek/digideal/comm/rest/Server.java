package org.trimatek.digideal.comm.rest;

import org.trimatek.digideal.compiler.actions.Compile;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Source;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.repo.Repository;

import com.google.gson.Gson;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle implements Launcher {

	public static void main(String args[]) {
		Server server = new Server();
		server.init();
	}

	public void init() {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Server(), ar -> {
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
			response.putHeader("content-type", "text/html").end("<h1>Hola, soy DigiDeal</h1>");
		});

		router.route("/api/drafts*").handler(BodyHandler.create());
		router.post("/api/drafts").handler(this::addOne);

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8080),
				result -> {
					if (result.succeeded()) {
						startFuture.complete();
					} else {
						startFuture.fail(result.cause());
					}
				});

	}

	private void addOne(RoutingContext routingContext) {

		String message = "compile fail";
		Source source = new Gson().fromJson(routingContext.getBodyAsString(), Source.class);

		if (source != null) {
			Contract cnt = new Contract(source);
			Compile c = new Compile(); 
			cnt = c.exec(cnt);
		}

		System.out.println(source.getText());

		// compilar
		// sino, mensaje de error a la web
		// if(draft!=null) {// si la compilación fue exitosa, se guarda el Draft
		// Repository.getInstance().save(new Contract(draft.getText(),null));
		// message = "compile success";
		// }

		routingContext.response().setStatusCode(201).putHeader("content-type", "text/plain; charset=utf-8")
				.end(message);
	}

}