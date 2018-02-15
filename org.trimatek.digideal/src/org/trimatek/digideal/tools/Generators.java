package org.trimatek.digideal.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Generators {

	public static String genNewDeliveryCode() {
		String random = getRandomNumberInts(100, 999) + "." + generateRandomString(new Random(), 3);
		String hash = String.valueOf(random.hashCode());
		hash = hash.substring(hash.length() - 3);
		return random + "." + hash;
	}

	public static int getRandomNumberInts(int min, int max) {
		return new Random().ints(min, max).findFirst().getAsInt();
	}

	private static String generateRandomString(Random random, int length) {
		return random.ints(48, 122).filter(i -> (i > 65) && (i < 90)).mapToObj(i -> (char) i).limit(length)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	public static void genQRCodeImage(String text, int width, int height, String filePath) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath(filePath + text.replace(" ", "") + ".png");
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

	public static byte[] genQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
	}

}
