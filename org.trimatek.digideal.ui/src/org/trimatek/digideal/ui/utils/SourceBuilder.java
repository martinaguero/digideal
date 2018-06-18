package org.trimatek.digideal.ui.utils;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.beans.ContractView;

public class SourceBuilder {

	static public String getDraft(ContractView view) {
		StringBuilder sb = new StringBuilder();
		sb.append(Config.COMMA_SYMBOL + Tools.msg.getString("contract_header") + " ");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNamePayer());
		sb.append(Tools.msg.getString("contract_identified_by_payer")+ Config.COMMA_SYMBOL  + " " + view.getNickPayer() + " {"
				+ view.getAddressPayer() + ", " + view.getEmailPayer() + "}");
		sb.append(" " + Tools.msg.getString("contract_and") + " " + Tools.msg.getString("contract_mr_mrs") + " "
				+ view.getNameCollector());
		return sb.toString();
	}

	public static void main(String[] args) {

	}

}
