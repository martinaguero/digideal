package org.trimatek.digideal.tools;

import java.util.Locale;
import java.util.ResourceBundle;

import org.trimatek.digideal.Context;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
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

	public static String toUrl(String text, String tx, String locale) {
		return Boolean.parseBoolean(Config.getValue("MAINNET"))
				? setHiperlink(text, 16, Config.getValue("BTC_TX_TRACK_MAINNET") + tx)
				: setHiperlink(text, 16, Config.getValue("BTC_TX_TRACK_TESTNET") + tx);
	}
	
	public static String getUnspentTxUrls(Contract cnt) {
		StringBuffer sb = new StringBuffer();
		int left = cnt.getUnspentTransactions().size();
		for (Transaction tx : cnt.getUnspentTransactions()) {
			sb.append(Dialogs.toUrl(tx.getValue().toString(),tx.getTxId(), cnt.getSource().getLocale()));
			left--;
			if (left > 0) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public static String getSymbol(String locale) {
		return Boolean.parseBoolean(Config.getValue("MAINNET"))?
				Dialogs.read("email_btc", locale):"t"+Dialogs.read("email_btc", locale);
	}

}
