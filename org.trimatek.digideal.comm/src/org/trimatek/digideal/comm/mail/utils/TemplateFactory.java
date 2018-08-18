package org.trimatek.digideal.comm.mail.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.trimatek.digideal.comm.mail.Template;

public class TemplateFactory {

	private static TemplateFactory INSTANCE;
	private String emailTemplate;
	private String buttonTemplate;

	private TemplateFactory() {
	}

	private static TemplateFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TemplateFactory();
		}
		return INSTANCE;
	}

	public static Template getEmailTemplate() {
		if (getInstance().emailTemplate == null) {
			INSTANCE.emailTemplate = loadTemplate("email-inlined.html");
		}
		return new Template(INSTANCE.emailTemplate);
	}

//	public static Template getButtonTemplate() {
//		if (getInstance().buttonTemplate == null) {
//			INSTANCE.buttonTemplate = loadTemplate("button.html");
//		}
//		return INSTANCE.buttonTemplate;
//	}

	private static String loadTemplate(String fileName) {
		String template;
		try (Scanner scanner = new Scanner(TemplateFactory.class.getResourceAsStream(fileName),
				StandardCharsets.UTF_8.name())) {
			template = scanner.useDelimiter("\\A").next();
		}
		return template;
	}

	public static void main(String args[]) throws IOException, URISyntaxException {

		InputStream is = TemplateFactory.class.getResourceAsStream("email-inlined.html");
		String text = null;
		try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
			text = scanner.useDelimiter("\\A").next();
		}

		text = text.replace("\"#{hi}\"", "HOLA");
		text = text.replace("\"#{button}\"", "");
		System.out.println(text);

	}

}
