package org.trimatek.digideal.ui.beans;

import java.time.Instant;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.trimatek.digideal.ui.comm.SendTicket;
import org.trimatek.digideal.ui.model.Ticket;

public class SupportView {

	private String request;
	private String id;
	private String from;

	public SupportView() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = params.get("id");
		from = params.get("from");
	}

	public String getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void sendAction() {
		Ticket t = new Ticket();
		t.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale().toString());
		t.setDatetime(Instant.now().toEpochMilli() / 1000L + "");
		t.setFrom(getFrom());
		t.setDealId(getId());
		t.setText(getRequest());
		SendTicket.exec(t);
	}

}
