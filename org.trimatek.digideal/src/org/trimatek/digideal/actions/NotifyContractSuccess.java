package org.trimatek.digideal.actions;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;

public class NotifyContractSuccess extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {
		
		logger.log(Level.INFO, "Ready to notify contract success");
		SendMessage.exec(setupPayerMail(contract));
		SendMessage.exec(setupCollectorMail(contract));
		logger.log(Level.INFO, "Messages sent successfully");
		done = Boolean.TRUE;
		
		return contract;
	}
	
	private static MimeMessage setupPayerMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Config.MAIL_SUBJECT_HEADER + " Contract payed");
		email.setText("Contract #" + cnt.getValue("id") + " payed with txid: " + cnt.getSpentTxId() );
		return email;	
	}
	
	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Config.MAIL_SUBJECT_HEADER + " Contract payed");
		email.setText("Contract #" + cnt.getValue("id") + " funds received with txid: " + cnt.getSpentTxId());
		return email;	
	}

}
