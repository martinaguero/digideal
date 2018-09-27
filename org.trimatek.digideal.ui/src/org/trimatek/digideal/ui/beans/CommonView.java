package org.trimatek.digideal.ui.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.trimatek.digideal.ui.Config;

public class CommonView {
	
	protected static Logger logger;
	static {
		InputStream inputStream = CommonView.class.getResourceAsStream("/logging.properties");
		if (null != inputStream) {
			try {
				LogManager.getLogManager().readConfiguration(inputStream);

			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, "init logging system", e);
			}
		}
	}
	
	public CommonView() {
		logger = Logger.getLogger(CommonView.class.getCanonicalName());
		logger.log(Level.INFO, "Ready logging");
	}
	
	public String getNavigationIndex() {
		return Config.NAVIGATION_INDEX + ".xhtml";
	}

}
