package org.trimatek.digideal.ui.utils;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.beans.ContractView;
import org.trimatek.digideal.ui.model.Source;

public class SourceBuilder {

	static public Source getSource(ContractView view) {
		StringBuilder sb = new StringBuilder();
		Source source = new Source();
		source.setName(System.currentTimeMillis() + "");
		
		sb.append(Tools.msg.getString("contract_header") + source.getName() + "<br/> ");
		sb.append(Tools.msg.getString("contract_intro") + " ");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNamePayer() + " ");
		sb.append(Tools.msg.getString("contract_identified_by_payer") + " " + view.getNickPayerValid()
				+ " { " + view.getAddressPayer() + " , " + view.getEmailPayer() + " } "
				+ Tools.msg.getString("contract_and") + " ");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNameCollector() + " ");
		sb.append(Tools.msg.getString("contract_identified_by_collector") + " "
				+ view.getNickCollectorValid() + " { " + view.getAddressCollector() + " , " + view.getEmailCollector()
				+ " } " + ", ");
		sb.append(Tools.msg.getString("contract_establishes") + " " + view.getNickPayerValid() + " "
				+ Tools.msg.getString("contract_will_pay") + " " + view.getNickCollectorValid() + " ");
		sb.append(Tools.msg.getString("contract_the_sum") + " " + view.getSelectedCurrency() + " " + view.getQuantity()
				+ " " + Tools.msg.getString("contract_with") + " BTC " + view.getBtc() + " ");
		sb.append(Tools.msg.getString("contract_if") + " " + view.getNickCollectorValid() + " "
				+ Tools.msg.getString("contract_delivers") + " <i> " + view.getAddress() + " </i> ");
		sb.append(Tools.msg.getString("contract_this") + " <i> " + view.getItem() + " </i> .");

		source.setText(sb.toString());
		return source;
	}
	
	public static String formatDraft(Source source) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = source.getText().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replaceAll(Config.EMAIL_REGEX, "");
			tokens[i] = tokens[i].replaceAll(Config.BTC_ADDRESS_REGEX, "");
			tokens[i] = tokens[i].replaceAll("[{},]", "");			
			tokens[i] = tokens[i].replaceAll("\"", "");
			tokens[i] = tokens[i].replaceAll("¡", ",");
		}
		for (String s : tokens) {
			if (!s.equals("")) {
				sb.append(s + " ");
			}
		}
		return sb.toString();
	}
	
	public static Source formatToGo(Source source) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = source.getText().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replace("<br/>", "");			
			tokens[i] = tokens[i].replace("<i>", "");
			tokens[i] = tokens[i].replace("</i>", "");
			tokens[i] = tokens[i].replace("¡", ",");
		}
		for (String s : tokens) {
			if (!s.equals("")) {
				sb.append(s + " ");
			}
		}
		source.setText(sb.toString());
		return source;
	}

}
