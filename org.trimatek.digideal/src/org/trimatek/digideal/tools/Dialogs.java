package org.trimatek.digideal.tools;

import java.util.Locale;
import java.util.ResourceBundle;

import org.trimatek.digideal.Config;

public class Dialogs {

	public static String read(String key, String lang) {
		return ResourceBundle.getBundle(Config.MESSAGES_BUNDLE, Locale.forLanguageTag(lang)).getString(key);
	}

	public static String setTwitterColor(String text) {
		return "<font color=\"#1da1f2\">" + text + "</font>";
	}

	public static String setHiperlink(String text, int size, String url) {
		return "<a href=\"" + url + "\" style=\"text-decoration: underline; color: #1da1f2; font-size: " + size
				+ "px;\">" + text + "</a>";
	}

	public static String getSupportUrl(String contractId, String from) {
		return Config.CONTACT_US_URL + "?id=" + contractId + "&from=" + from;
	}

}
