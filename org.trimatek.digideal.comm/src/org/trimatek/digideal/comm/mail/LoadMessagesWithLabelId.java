package org.trimatek.digideal.comm.mail;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

public class LoadMessagesWithLabelId extends Resource {

	public static String exec(Object ... params) throws Exception {
		Gmail service = Setup.getGmailService();

		List<String> l = new ArrayList<String>();
		l.add((String)params[0]);

		ListThreadsResponse response = service.users().threads().list(Setup.USER).execute();
		List<Thread> threads = new ArrayList<Thread>();
		while (response.getThreads() != null) {
			threads.addAll(response.getThreads());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().threads().list(Setup.USER).setLabelIds(l).setPageToken(pageToken).execute();
			} else {
				break;
			}
		}

		for (Thread thread : threads) {
			System.out.println(thread.toPrettyString());
		}
		
		return null;
	}

}
