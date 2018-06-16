package org.trimatek.digideal.ui.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.trimatek.digideal.ui.model.Address;
import org.trimatek.digideal.ui.utils.Tools;

public class Validators {

	private static String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static String NAME_REGEX = "^[^\\d]+$";
	private static String BTC_ADDRESS_REGEX = "^[123mn][1-9A-HJ-NP-Za-km-z]{26,35}";

	public static boolean validateEmail(String target, String message) {
		if (target != null && !target.matches(EMAIL_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static boolean validateName(String target, String message, int minWords) {
		if (target == null || !target.matches(NAME_REGEX) || target.split(" ").length < minWords) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static boolean validateAddress(String target, String message) {
		if (target == null || !target.matches(BTC_ADDRESS_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static String validateAddress(Address address) {
		StringBuilder sb = new StringBuilder();
		if (address.getROUTE() == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_street_not_found"));
		}
		if (address.getSTREET_NUMBER() == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_street_number_not_found"));
		}
		if (address.getPOSTAL_CODE() == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_postal_code_not_found"));
		}
		if (address.getADMINISTRATIVE_AREA_LEVEL_1() == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_city_not_found"));
		}
		if (address.getCOUNTRY() == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_country_not_found"));
		}
		return sb.length() > 0 ? Tools.msg.getString("error_address") + sb.toString() : "";
	}

	public static boolean validateSentenceLength(String target, String message, int minWords) {
		if (target == null || target.split(" ").length < minWords) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static String normalize(String source) {
		String result = source.replace(" ", "_");
		return result.contains("@") ? result : "@" + result;
	}

}
