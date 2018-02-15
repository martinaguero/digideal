package org.trimatek.digideal;

import org.trimatek.digideal.tools.Generators;

public class Prueba {

	private static final String QR_CODE_IMAGE_PATH = "c:\\Temp\\";

	public static void main(String[] args) throws Exception {
		String code = Generators.genNewDeliveryCode();
		Generators.genQRCodeImage(code, 350, 350, QR_CODE_IMAGE_PATH);
	}

}
