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

}
