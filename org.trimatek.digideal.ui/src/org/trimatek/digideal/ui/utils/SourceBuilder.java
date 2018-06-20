package org.trimatek.digideal.ui.utils;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.beans.ContractView;

public class SourceBuilder {

	static public String getDraft(ContractView view) {
		StringBuilder sb = new StringBuilder();
		sb.append(Config.COMMA_SYMBOL + Tools.msg.getString("contract_header") + "<br/>");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNamePayer());
		sb.append(Tools.msg.getString("contract_identified_by_payer") + Config.COMMA_SYMBOL + " " + view.getNickPayer()
				+ " { " + view.getAddressPayer() + " , " + view.getEmailPayer() + " } "
				+ Tools.msg.getString("contract_and") + "<br/>");
		sb.append(Tools.msg.getString("contract_mr_mrs") + " " + view.getNameCollector());
		sb.append(Tools.msg.getString("contract_identified_by_collector") + Config.COMMA_SYMBOL + " "
				+ view.getNickCollector() + " { " + view.getAddressCollector() + " , " + view.getEmailCollector()
				+ " } " + "<br/>");
		sb.append(Tools.msg.getString("contract_establishes") + " " + view.getNickPayer() + " "
				+ Tools.msg.getString("contract_will_pay") + " " + view.getNickCollector() + " ");
		sb.append(Tools.msg.getString("contract_the_sum") + " " + view.getSelectedCurrency() + " " + view.getQuantity()
				+ " " + Tools.msg.getString("contract_with") + " BTC " + view.getBtc() + " <br/>");
		sb.append(Tools.msg.getString("contract_if") + " " + view.getNickCollector() + " "
				+ Tools.msg.getString("contract_delivers") + " " + view.getAddress() + " ");
		sb.append(Tools.msg.getString("contract_this") + " " + view.getItem() + " <br/>");

		return Tools.formatPreview(sb.toString());
	}

}
