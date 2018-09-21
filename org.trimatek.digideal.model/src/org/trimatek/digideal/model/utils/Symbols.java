package org.trimatek.digideal.model.utils;

public enum Symbols {
	QUOTE;

	public String toString() {
		return Tools.isUxHost() ? "\\\"" : "\\\\";
	}

}
