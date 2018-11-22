package sys;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {

	private final static String DIGIDEAL_ADMIN_URL = "http://190.210.244.92:9090/sys";
	private static final String PROMPT_DEFAULT = "digideal> ";
	private final static String PROMPT_UPDATE = "update> ";
	private final static String PROMPT_RETRIEVE = "retrieve> ";
	private static String prompt = PROMPT_DEFAULT;
	private static String key;
	private final static String VERSION = "0.1.0";
	private static boolean online = true;

	public static void main(String[] args) throws Exception {
		key = args[0];
		Scanner scanner = new Scanner(System.in);
		System.out.println("DigiDeal Admin " + VERSION);
		while (online) {
			System.out.print(prompt);
			parseInput(scanner.nextLine());
		}
		scanner.close();
	}

	private static void parseInput(String input) throws Exception {
		long startTime = System.nanoTime();

		if (input.equals("update")) {
			prompt = PROMPT_UPDATE;
		} else if (prompt.equals(PROMPT_UPDATE) && input.startsWith("in")) {
			sendUpdate(input);
		} else if (prompt.equals(PROMPT_RETRIEVE) && input.startsWith("in")) {
			sendRetrieve(input);
		} else if (input.equals("help")) {
			System.out.println("Update mode type 'update'");
			System.out.println("\tUpdate syntax: in <DigiDeal#> set <new-value> to <key>");
			System.out.println("\tExample: in 72NZKQM set aguero.martin@gmail.com for payer.address");
			System.out.println("Retrieve mode type 'retrieve'");
			System.out.println("\tRetrieve syntax: in <DigiDeal#>");
			System.out.println("\tExample: in 72NZKQM");
		} else if (input.equals("retrieve")) {
			prompt = PROMPT_RETRIEVE;
		} else if (input.equals("exit")) {
			System.out.println("bye");
			online = false;
		} else {
			prompt = PROMPT_DEFAULT;
		}
	}

	private static void sendUpdate(String input) throws Exception {
		send("/update",input);
	}

	private static void sendRetrieve(String input) throws Exception {
		send("/retrieve",input);
	}
	
	private static void send(String command, String input) throws Exception {
		input = input + " key " + key;
		String commands[] = input.split(" ");
		Map<String, String> requestParams = new HashMap<>();
		for (int i = 0; i < commands.length; i = i + 2) {
			requestParams.put(commands[i], commands[i + 1]);
		}
		String encodedURL = requestParams.keySet().stream().map(key -> key + "=" + encodeValue(requestParams.get(key)))
				.collect(joining("&", DIGIDEAL_ADMIN_URL + command + "?", ""));

		URL url = new URL(encodedURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		int responseCode = con.getResponseCode();
		System.out.println("GET request: " + url);
		System.out.println("Response code: " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();
		while ((output = in.readLine()) != null) {
			response.append("\t" + output + System.lineSeparator());
		}
		System.out.println("Result: " + System.lineSeparator() + response.toString());
		in.close();
		con.disconnect();
	}

	private static String encodeValue(String value) {
		String encoded = null;
		try {
			encoded = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error encoding parameter {}" + e.getMessage());
		}
		return encoded;
	}

}
