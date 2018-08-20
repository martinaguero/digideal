package org.trimatek.digideal.tools;

import java.util.ResourceBundle;

import org.trimatek.digideal.Config;

public class Dialogs {

	public final static ResourceBundle msg = ResourceBundle.getBundle(Config.MESSAGES_BUNDLE);

	public static String setTwitterColor(String text) {
		return "<font color=\"#1da1f2\">" + text + "</font>";
	}

	public static String setHiperlink(String text, int size, String url) {
		return "<a href=\"" + url + "\" style=\"text-decoration: underline; color: #1da1f2; font-size: " + size
				+ "px;\">" + text + "</a>";
	}

}
