package org.trimatek.digideal.comm.mail;

import java.io.IOException;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

public class GetMessage extends GMailResource {
	
	/**
	 * @param params
	 *            id
	 * @return Message
	 * @throws Exception 
	 */

	public static Object exec(String... params) throws Exception {
		return getMessage(Setup.getGmailService(), Setup.USER, params[0]);
	}
	
	 /**
	   * Get Message with given ID.
	   *
	   * @param service Authorized Gmail API instance.
	   * @param userId User's email address. The special value "me"
	   * can be used to indicate the authenticated user.
	   * @param messageId ID of Message to retrieve.
	   * @return Message Retrieved Message.
	   * @throws IOException
	   */
	  public static Message getMessage(Gmail service, String userId, String messageId)
	      throws IOException {
	    return service.users().messages().get(userId, messageId).execute();
	  }


}
