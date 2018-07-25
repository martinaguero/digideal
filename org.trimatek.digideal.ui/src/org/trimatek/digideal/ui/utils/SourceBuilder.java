package org.trimatek.digideal.ui.utils;

import static org.trimatek.digideal.ui.Config.DATE_FORMAT;

import java.util.Date;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.beans.ContractView;
import org.trimatek.digideal.ui.comm.GetSerial;
import org.trimatek.digideal.ui.model.Source;

public class SourceBuilder {
	
		static public Source getSource(ContractView view) {
		StringBuilder sb = new StringBuilder();
		Source source = new Source();
		source.setName(GetSerial.exec(null).trim());

		sb.append(Tools.msg.getString("contract_header") + source.getName() + " <br/>");
		sb.append(Tools.msg.getString("contract_intro") + " ");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNamePayer() + " ");
		sb.append(Tools.msg.getString("contract_identified_by_payer") + " " + view.getNickPayerValid() + " { \" "
				+ view.getAddressPayer() + " \" , \" " + view.getEmailPayer() + " \" } " + Tools.msg.getString("contract_and")
				+ " ");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNameCollector() + " ");
		sb.append(Tools.msg.getString("contract_identified_by_collector") + " " + view.getNickCollectorValid() + " { \" "
				+ view.getAddressCollector() + " \" , \" " + view.getEmailCollector() + " \" } ");
		sb.append(Tools.msg.getString("contract_establishes") + " " + view.getNickPayerValid() + " "
				+ Tools.msg.getString("contract_will_pay") + " " + view.getNickCollectorValid() + " ");
		sb.append(Tools.msg.getString("contract_the_sum") + " \"" + view.getSelectedCurrency() + " " + view.getQuantity()
				+ "\" " + Tools.msg.getString("contract_with") + " BTC \"" + view.getBtc() + "\" ");
		sb.append(Tools.msg.getString("contract_if") + " " + view.getNickCollectorValid() + " "
				+ Tools.msg.getString("contract_delivers") + " <i> { \" " + view.getAddress() + " \" } </i> ");
		sb.append(Tools.msg.getString("contract_this") + " <i> \"" + view.getItem() + "\" · </i>");
		sb.append(Tools.msg.getString("contract_supervised") + " " + view.getAgentNick() + " · { \" ");
		sb.append(view.getAgentAddress() + " \" , \" " + view.getAgentEmail() + " \" }");
		sb.append(Tools.msg.getString("contract_date") + " \"" + getToday() + "\" · ");

		source.setText(sb.toString());
		return source;
	}

	public static String formatDraft(Source source) {
		String[] tokens = source.getText().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replaceAll(Config.EMAIL_REGEX, "");
			tokens[i] = tokens[i].replaceAll(Config.BTC_ADDRESS_REGEX, "");
			tokens[i] = tokens[i].replaceAll("[{},]", "");
			tokens[i] = tokens[i].replaceAll("\"", "");
			tokens[i] = tokens[i].replaceAll("¡", ",");
			tokens[i] = tokens[i].replaceAll("·", ".");
		}
		StringBuilder sb = build(tokens);
		return sb.toString();
	}

	public static Source formatToGo(Source source) {
		String[] tokens = source.getText().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replace("<br/>", "");
			tokens[i] = tokens[i].replace("<i>", "");
			tokens[i] = tokens[i].replace("</i>", "");
			tokens[i] = tokens[i].replace("¡", ",");
			tokens[i] = tokens[i].replace("·", "");
		}
		StringBuilder sb = build(tokens);
		source.setText(sb.toString());
		return source;
	}

	private static StringBuilder build(String[] tokens) {
		StringBuilder sb = new StringBuilder();
		for (String s : tokens) {
			if (!s.equals("")) {
				if (s.equals(".") || s.equals(",")) {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append(s + " ");
			}
		}
		return sb;
	}
	
	private static String getToday() {
		return DATE_FORMAT.format(new Date());
	}

}
