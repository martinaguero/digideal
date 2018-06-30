package org.trimatek.digideal.ui.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.model.Source;

public class Tools {

	public final static ResourceBundle msg = ResourceBundle.getBundle(Config.MESSAGES_BUNDLE);

	public static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields)
			fieldNames.add(field.getName());
		return fieldNames;
	}

	public static StreamedContent getPdf(Source source) {

		StreamedContent file = null;
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			// PDFont font = PDType1Font.HELVETICA_BOLD;
			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.beginText();
			contentStream.showText(SourceBuilder.formatDraft(source));
			contentStream.endText();
			contentStream.close();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			document.save(out);

			file = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf",
					"contract.pdf");

			document.close();
		} catch (IOException e) {

		}

		return file;
	}

}
