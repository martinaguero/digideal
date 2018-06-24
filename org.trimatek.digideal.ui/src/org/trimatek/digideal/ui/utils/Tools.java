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

	

}
