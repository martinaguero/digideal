package org.trimatek.digideal.bitcoin.entities;

import java.math.MathContext;
import java.math.RoundingMode;

public class Context {

	public static final String PATH_TO_CLI = "D:\\bin\\bitcoin-0.15.1\\bin\\bitcoin-cli.exe";
	public static final int STS_PER_BYTE = 60;
	public static final MathContext MATH_CONTEXT = new MathContext(0, RoundingMode.UNNECESSARY);
	public static final int DEFAULT_SCALE = 4;
	public static final int SMALLEST_UNIT_EXPONENT = 8;
	public static final double COIN_VALUE = Math.pow(10, SMALLEST_UNIT_EXPONENT);
	public static final String AGENT_PUBLIC_KEY = "0314977363f96e1d7fed75205f22d933d5cf38997e19fc50ba65115ef00a91e68f";
	public static final String AGENT_PRIVATE_KEY = "cSJ3ZcLCT1AfUiFwCTW1fUK5EdoJkikMAJmj35MLn9qRGBxtcj7P";

}
