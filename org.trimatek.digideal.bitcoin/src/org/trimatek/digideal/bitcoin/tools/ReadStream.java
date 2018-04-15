package org.trimatek.digideal.bitcoin.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadStream implements Runnable {
	String name;
	InputStream is;
	Thread thread;
	boolean read = false;
	StringBuilder sb = new StringBuilder();

	public ReadStream(String name, InputStream is) {
		this.name = name;
		this.is = is;
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				sb.append(s);
			}
			is.close();
			read = true;
		} catch (Exception ex) {
			System.out.println("Problem reading stream " + name + "... :" + ex);
			ex.printStackTrace();
		}
	}

	public String getStream() {
		return read ? sb.toString() : null;
	}
}
