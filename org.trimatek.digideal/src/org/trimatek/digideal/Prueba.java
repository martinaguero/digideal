package org.trimatek.digideal;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.trimatek.digideal.tools.Generators;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Prueba {

	private static final String QR_CODE_IMAGE_PATH = "D:\\Temp\\";

	public static void main(String[] args) throws Exception {
		String code = Generators.genNewDeliveryCode();
		//genQRCodeImage("https://api.whatsapp.com/send?phone=+541157607730&text=" + code, 350, 350, QR_CODE_IMAGE_PATH);		
		//genQRCodeImage("mailto:aguero.martin@gmail.com?Subject=Recibi el envio&body=" + code, 350, 350, QR_CODE_IMAGE_PATH);
		genQRCodeImage("googlegmail:///co?subject=DIGIDEAL&to=aguero.martin@gmail.com&body=" + code, 350, 350, QR_CODE_IMAGE_PATH);
	}  
	
	public static void genQRCodeImage(String text, int width, int height, String filePath) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath("d:\\Temp\\nuevoqr.png");
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

}
