package org.trimatek.digideal.ui.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.trimatek.digideal.ui.Config;

public class Tools {

	public final static ResourceBundle msg = ResourceBundle.getBundle(Config.MESSAGES_BUNDLE);

	public static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields)
			fieldNames.add(field.getName());
		return fieldNames;
	}

	public static String formatPreview(String preview) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = preview.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replaceAll(Config.EMAIL_REGEX, "");
			tokens[i] = tokens[i].replaceAll(Config.BTC_ADDRESS_REGEX, "");
			tokens[i] = tokens[i].replaceAll("[{},]", "");
		}
		for (String s : tokens) {
			if (!s.equals("")) {
				sb.append(s + " ");
			}
		}
		return sb.toString();
	}

}
