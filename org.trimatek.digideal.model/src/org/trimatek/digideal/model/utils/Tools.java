package org.trimatek.digideal.model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tools {

	public static byte[] readBytes(String path) throws IOException {
		Path source = Paths.get(path);
		return Files.readAllBytes(source);
	}

}
