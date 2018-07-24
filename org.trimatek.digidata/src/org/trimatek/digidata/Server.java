package org.trimatek.digidata;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Server extends AbstractVerticle {

	private final static Logger logger = Logger.getLogger(Server.class.getName());
	private final static int PORT = 8282;

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			req.response().putHeader("content-type", "text/plain").end(Serializer.getInstance().newSerial());
		}).listen(PORT);
		logger.log(Level.INFO, "HTTP server started on port " + PORT);		
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