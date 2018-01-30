package org.trimatek.digideal.bitcoin.tools;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

public class Mail {

	protected final static Logger logger = Logger.getLogger(Mail.class.getName());

	public static int sendMail(String subject, String body, String from, String... to) throws IOException {

		Personalization p1 = new Personalization();	
		for (String rec : to) {
			p1.addTo(new Email(rec));
		}
		com.sendgrid.Mail mail = new com.sendgrid.Mail();
		mail.setSubject(subject);
		mail.addContent(new Content("text/html", body));
		mail.setFrom(new Email(from));
		mail.addPersonalization(p1);
		
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());

		logger.log(Level.INFO, "Sending mail: " + subject);
		
		return sg.api(request).getStatusCode();
	}

}
