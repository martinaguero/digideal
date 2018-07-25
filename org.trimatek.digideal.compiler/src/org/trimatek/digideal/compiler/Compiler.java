package org.trimatek.digideal.compiler;

import org.trimatek.digideal.bitcoin.tools.ReadStream;



public class Compiler {

	public static void main(String[] args) throws Exception {

		
		Process proc = Runtime.getRuntime().exec("C:\\Program Files\\Java\\jdk1.8.0_60\\bin\\java -jar d:\\bin\\digideal\\coco.jar d:\\Temp\\coco\\quinto.cnt");
		// Then retrieve the process output
		ReadStream s1 = new ReadStream("stdin", proc.getInputStream());
		ReadStream s2 = new ReadStream("stderr", proc.getErrorStream());
		s1.start();
		s2.start();
		proc.waitFor();

		String in = s1.getStream();
		String err = s2.getStream();
		
		System.out.println(in);
		System.out.println(err);
		
	}

}
