package org.trimatek.digideal.ui.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Validators {
	
	private static String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static String NAME_REGEX = "^[^\\d]+$";
	
	public static boolean validateEmail(String target, String message) {
		if (target != null && !target.matches(EMAIL_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					message));
			return false;
		} 
		return true;
	}
	
	public static boolean validateName(String target, String message) {
		if (target == null || !target.matches(NAME_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					message));
			return false;
		} 
		return true;
	}
	
	public static String normalize(String source) {
		String result = source.replace(" ", "_");
		return result.contains("@") ? result : "@" + result;
	}

}
