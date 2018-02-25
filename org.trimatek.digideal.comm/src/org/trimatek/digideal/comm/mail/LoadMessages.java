package org.trimatek.digideal.comm.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class LoadMessages extends GMailResource {

	/**
	 * @param params
	 *            query
	 * @return List<Message>
	 * @throws Exception 
	 */

	public static Object exec(String... params) throws Exception {
		return listMessagesMatchingQuery(Setup.getGmailService(), Setup.USER, params[0]);
	}

	/**
	 * List all Messages of the user's mailbox matching the query.
	 *
	 * @param service
	 *            Authorized Gmail API instance.
	 * @param userId
	 *            User's email address. The special value "me" can be used to
	 *            indicate the authenticated user.
	 * @param query
	 *            String used to filter the Messages listed.
	 * @throws IOException
	 */
	public static List<Message> listMessagesMatchingQuery(Gmail service, String userId, String query)
			throws IOException {
		ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();
		List<Message> messages = new ArrayList<Message>();
		while (response.getMessages() != null) {
			messages.addAll(response.getMessages());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().messages().list(userId).setQ(query).setPageToken(pageToken).execute();
			} else {
				break;
			}
		}
		return messages;
	}

}
