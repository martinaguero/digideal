package org.trimatek.digideal.comm.rest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.compiler.actions.Compile;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.model.Source;
import org.trimatek.digideal.model.Status;
import org.trimatek.digideal.model.Ticket;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.model.utils.StatusFactory;
import org.trimatek.digideal.model.utils.Tools;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.repo.RepositorySupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	private File CLI;

	public Server(int port) {
		PORT = port;
		RepositorySupport.getInstance();
		try {
			InputStream is = Server.class.getResourceAsStream("/sys/Admin.class");
			File file = File.createTempFile("Admin", ".class");
			Path path = file.toPath();
			Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
			is.close();
			CLI = path.toFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.WARNING, "Could not locate Admin class file");
		}
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

		router.route("/api/status").handler(this::getStatus);

		router.route("/sys/admin").handler(this::sendCli);

		router.route("/sys/update").handler(this::update);

		router.route("/sys/retrieve").handler(this::retrieve);

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

	private void getStatus(RoutingContext routingContext) {
		Status status = new Status();
		String id = routingContext.request().getParam("id");
		if (id != null) {
			Contract cnt = Repository.getInstance().loadContract(id);
			if (cnt != null) {
				status = StatusFactory.build(cnt);
				status.setResult(200);// OK
			} else {
				status.setId(id);
				status.setResult(404);// NOT_FOUND
			}
		} else {
			status.setId(id);
			status.setResult(400);// BAD_REQUEST
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8")
				.end(gson.toJson(status));
	}

	private void sendCli(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		response.putHeader("Content-Description", "File Transfer");
		response.putHeader("Content-Type", "application/octet-stream");
		response.putHeader("Content-Disposition", "attachment; filename=" + "Admin.class");
		response.putHeader("Content-Transfer-Encoding", "binary");
		response.putHeader("Expires", "0");
		response.putHeader("Pragma", "Public");
		response.putHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.putHeader("Content-Length", "" + CLI.length());
		response.sendFile(CLI.getAbsolutePath());
	}

	private void update(RoutingContext routingContext) {
		String result = "Contract instructions could not be updated";
		String in = routingContext.request().getParam("in");
		String set = routingContext.request().getParam("set");
		String to = routingContext.request().getParam("to");
		String key = routingContext.request().getParam("key");

		logger.log(Level.INFO, "Update request with params in=" + in + " set=" + set + " for=" + to);
		logger.log(Level.INFO, "Loading contract: " + in + " for update");

		if (in != null && set != null && to != null && key != null
				&& key.equalsIgnoreCase(Config.getValue("ADMIN_KEY"))) {
			Contract cnt = Repository.getInstance().loadContract(in);
			if (cnt != null) {
				Properties prop = new Properties();
				try {
					prop.load(new ByteArrayInputStream(cnt.getInstructions()));
					prop.put(to, set);
					cnt.setInstructions(Tools.toByteArray(prop));
					Repository.getInstance().save(cnt);
					prop.load(new ByteArrayInputStream(cnt.getInstructions()));
					result = Tools.toString(prop);
				} catch (IOException e) {
					logger.log(Level.SEVERE, result);
				}
			}
		}

		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8").end(result);
	}

	private void retrieve(RoutingContext routingContext) {
		String in = routingContext.request().getParam("in");
		String key = routingContext.request().getParam("key");
		String result = "Contract instructions could not be retrieved";

		logger.log(Level.INFO, "Retrieve request with param in=" + in);

		if (in != null && key.equalsIgnoreCase(Config.getValue("ADMIN_KEY"))) {
			Contract cnt = Repository.getInstance().loadContract(in);
			if (cnt != null) {
				try {
					Properties prop = new Properties();
					prop.load(new ByteArrayInputStream(cnt.getInstructions()));
					result = Tools.toString(prop);
				} catch (IOException e) {
					logger.log(Level.SEVERE, result);
				}
			}
		}
		routingContext.response().setStatusCode(200).putHeader("content-type", "text/plain; charset=utf-8").end(result);
	}

}