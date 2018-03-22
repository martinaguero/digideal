package org.trimatek.digideal.comm.mail;

import java.io.IOException;
import java.util.List;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;

public class ModifyMessageLabel extends GMailResource {
	
	/**
	 * @param params
	 *            id
	 *            label-to-add
	 *            label-to-remove
	 * @return Message           
	 * @throws Exception 
	 */

	public static Object exec(String... params) throws Exception {
		return modifyMessage(Setup.getGmailService(), Setup.USER, params[0], List.of(params[1]), List.of(params[2]));		
	}
	
	/**
	   * Modify the labels a message is associated with.
	   *
	   * @param service Authorized Gmail API instance.
	   * @param userId User's email address. The special value "me"
	   * can be used to indicate the authenticated user.
	   * @param messageId ID of Message to Modify.
	   * @param labelsToAdd List of label ids to add.
	   * @param labelsToRemove List of label ids to remove.
	   * @throws IOException
	   */
	  public static Message modifyMessage(Gmail service, String userId, String messageId,
	      List<String> labelsToAdd, List<String> labelsToRemove) throws IOException {
	    ModifyMessageRequest mods = new ModifyMessageRequest().setAddLabelIds(labelsToAdd)
	        .setRemoveLabelIds(labelsToRemove);
	    return service.users().messages().modify(userId, messageId, mods).execute();
	  }



}
