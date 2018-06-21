package org.trimatek.digideal.ui.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.model.Address;

public class Validators {

	public static boolean validateEmail(String target, String message) {
		if (target != null && !target.matches(Config.EMAIL_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static boolean validateName(String target, String message, int minWords) {
		if (target == null || !target.matches(Config.NAME_REGEX) || target.split(" ").length < minWords) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static boolean validateAddress(String target, String message) {
		if (target == null || !target.matches(Config.BTC_ADDRESS_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
			return false;
		}
		return true;
	}

	public static String validateAddress(Address address) {
		StringBuilder sb = new StringBuilder();
		if (address == null) {
			sb.append("<br/>");
			sb.append(Tools.msg.getString("error_address_not_parseable"));
		} else {
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
		String result = source.replace(" ", "_").toLowerCase();
		return result.contains("@") ? result : "@" + result;
	}

}
