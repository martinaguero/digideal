package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Level;

import org.trimatek.digideal.model.Contract;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class RequestFunds extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		Email from = new Email(cnt.getValue("agent.email"));
		String subject = "[DD] Funds request";
		Email to = new Email(cnt.getValue("payer.email"));

		String html = "<html><body><p>Please send STS " + cnt.getValue("sts") + " to address: <br><b>"
				+ cnt.getMultisigAddress() + "</b><br> in order to proceed with the contract requirements."
				+ "</p></body></html>";

		Content content = new Content("text/html", html);

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());

		logger.log(Level.INFO, "Ready to send mail requesting contract funds");
		Response response = sg.api(request);
		if (String.valueOf(response.getStatusCode()).charAt(0) == '2') {
			logger.log(Level.INFO, "Message send successfully");
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.SEVERE, "Message could not be send");
		return null;
	}

}
