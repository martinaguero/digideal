package org.trimatek.digideal.tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

import javax.imageio.ImageIO;

import org.trimatek.digideal.model.Contract;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.mail.util.BASE64DecoderStream;

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

	public static String readQRCode(Object content)
			throws IOException, NotFoundException, ChecksumException, FormatException {
		BASE64DecoderStream stream = (BASE64DecoderStream) content;
		InputStream is = new ByteArrayInputStream(stream.readAllBytes());
		BufferedImage image = ImageIO.read(is);
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		Result result = reader.decode(bitmap);
		return result.getText();
	}

	public static String genQRSendTo(Contract cnt, BigDecimal amount) throws IOException {
		String btc = amount != null ? amount.toString() : cnt.getValue("btc");
		return "bitcoin:" + cnt.getMultisigAddress() + "?amount=" + btc;
	}

}
