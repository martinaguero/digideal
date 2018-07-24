package org.trimatek.digidata;

import java.time.Instant;

public class Serializer {

	private static Serializer INSTANCE;
	private Long lastEpoch;
	private int counter;

	private Serializer() {
	}

	public static Serializer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Serializer();
		}
		return INSTANCE;
	}

	public String newSerial() {
		String serial = null;
		Long nowEpoch = Instant.now().toEpochMilli() / 1000L;		
		if (nowEpoch.equals(lastEpoch)) {
			counter++;
		} else {
			counter = 0;
		}
		lastEpoch = nowEpoch;
		serial = lastEpoch + "" + counter;
		return Long.toString(Long.valueOf(serial), 36).toUpperCase();
	}

}
