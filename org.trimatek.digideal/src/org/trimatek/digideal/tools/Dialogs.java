package org.trimatek.digideal.tools;

import static org.trimatek.digideal.tools.Dialogs.setHiperlink;

import java.util.Locale;
import java.util.ResourceBundle;

import org.trimatek.digideal.Context;
import org.trimatek.digideal.model.utils.Config;

public class Dialogs {

	public static String read(String key, String lang) {
		return ResourceBundle.getBundle(Context.MESSAGES_BUNDLE, Locale.forLanguageTag(lang)).getString(key);
	}

	public static String setTwitterColor(String text) {
		return "<font color=\"#1da1f2\">" + text + "</font>";
	}

	public static String setHiperlink(String text, int size, String url) {
		return "<a href=\"" + url + "\" style=\"text-decoration: underline; color: #1da1f2; font-size: " + size
				+ "px;\">" + text + "</a>";
	}

	public static String getSupportUrl(String contractId, String from) {
		return Config.getValue("UI_CONTACT_US_URL") + "?id=" + contractId + "&from=" + from;
	}

	public static String toUrl(String tx, String locale) {
		return Boolean.parseBoolean(Config.getValue("MAINNET"))
				? setHiperlink(tx, 16, Config.getValue("BTC_TX_TRACK_MAINNET") + tx)
				: setHiperlink(tx, 16, Config.getValue("BTC_TX_TRACK_TESTNET") + tx);
	}

}
