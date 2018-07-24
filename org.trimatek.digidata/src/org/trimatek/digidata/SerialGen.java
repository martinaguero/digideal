package org.trimatek.digidata;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class SerialGen extends AbstractVerticle {
 
	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
		}).listen(8080);
		System.out.println("HTTP server started on port 8080");
	}
	
	
	public static final void main(String args[]) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new SerialGen(), ar -> {
			if (ar.failed()) {
				ar.cause().printStackTrace();
			}
		});
	}
}