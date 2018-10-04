package org.trimatek.digideal.ui.beans;

import java.time.Instant;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.trimatek.digideal.ui.Context;
import org.trimatek.digideal.ui.comm.SendTicket;
import org.trimatek.digideal.ui.model.Ticket;
import org.trimatek.digideal.ui.utils.Tools;
import org.trimatek.digideal.ui.utils.Validators;

public class SupportView extends CommonView {

	private String message;
	private String messageStyle;
	private String id;
	private String from;
	private String fromStyle;

	public SupportView() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = params.get("id");
		from = params.get("from");
		fromStyle = Context.REQUIRED_FIELD;
		messageStyle = Context.REQUIRED_FIELD;
	}

	public String getId() {
		return id;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageStyle() {
		return messageStyle;
	}

	public void setMessageStyle(String messageStyle) {
		this.messageStyle = messageStyle;
	}

	public String getFromStyle() {
		return fromStyle;
	}

	public void setFromStyle(String fromStyle) {
		this.fromStyle = fromStyle;
	}

	public void validateEmail() {
		if (Validators.validateEmail(getFrom(), Tools.read("error_email", getLocale().toString()),
				Tools.read("error_invalid", getLocale().toString()))) {
			fromStyle = null;
		} else {
			fromStyle = Context.REQUIRED_FIELD;
		}
	}

	public void validateMessage() {
		if (getMessage().length() >= Context.CONTACT_MESSAGE_MIN
				&& getMessage().length() <= Context.CONTACT_MESSAGE_MAX) {
			messageStyle = null;
		} else {
			messageStyle = Context.REQUIRED_FIELD;
		}
	}

	public void sendAction() {
		Ticket t = new Ticket();
		t.setLocale(getLocale().toString());
		t.setDatetime(Instant.now().toEpochMilli() / 1000L + "");
		t.setFrom(getFrom());
		t.setDealId(getId());
		t.setText(getMessage());
		SendTicket.exec(t);
	}

	public boolean getSendDisabled() {
		return fromStyle == null && messageStyle == null ? false : true;
	}

}
