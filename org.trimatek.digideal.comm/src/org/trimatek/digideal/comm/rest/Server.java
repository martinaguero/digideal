package org.trimatek.digideal.comm.rest;

import org.trimatek.digideal.model.Draft;

import com.google.gson.Gson;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {

  public static void main(String[] args) {
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
        response
            .putHeader("content-type", "text/html")
            .end("<h1>Hola, soy DigiDeal</h1>");
  });
    
    router.route("/api/drafts*").handler(BodyHandler.create());
    router.post("/api/drafts").handler(this::addOne);
    
    
    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(
            config().getInteger("http.port", 8080),
            result -> {
              if (result.succeeded()) {
            	  startFuture.complete();
              } else {
            	  startFuture.fail(result.cause());
              }
            }
        );

  }
 
  private void addOne(RoutingContext routingContext) {

	    final Draft draft = new Gson().fromJson(routingContext.getBodyAsString(),Draft.class);

	    System.out.println(draft.getText());

	    routingContext.response()
	        .setStatusCode(201)
	        .putHeader("content-type", "application/json; charset=utf-8")
	        .end(new Gson().toJson(draft));
	}
  
  
}