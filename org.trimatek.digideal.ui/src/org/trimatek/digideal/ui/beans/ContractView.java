package org.trimatek.digideal.ui.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ContractView {

	private String namePayer;
	private String namePayerStyle;
	private String nickPayer;
	private String nickPayerStyle;
	private String nickPayerValid;
	private String emailPayer;
	private String emailPayerStyle;
	private String addressPayer;
	private String addressPayerStyle;
	private String addressPayerTooltip;
	private String nameCollector;
	private String nickCollector;
	private String emailCollector;
	private Map<String, String> currencies;
	private String selectedCurrency;
	private String quantity;
	private String btc;
	private String item;
	private String address;
	private final String BTC_PRICE_URL = "https://blockchain.info/tobtc?currency=USD&value=1";
	private double BTC_PER_DOLLAR = 0.00013828;

	public ContractView() {
		currencies = new HashMap<String, String>();
		currencies.put(CurrenciesEnum.BTC.name(), CurrenciesEnum.BTC.name());
		currencies.put(CurrenciesEnum.USD.name(), CurrenciesEnum.USD.name());
		currencies.put(CurrenciesEnum.BRL.name(), CurrenciesEnum.BRL.name());
		updateBtc();
		namePayerStyle = Config.REQUIRED_FIELD;
		nickPayerStyle = Config.REQUIRED_FIELD;
		emailPayerStyle = Config.REQUIRED_FIELD;
		addressPayerStyle = Config.REQUIRED_FIELD;
	}

	Runnable updateBtc = () -> {
		try {
			URL obj = new URL(BTC_PRICE_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				BTC_PER_DOLLAR = Double.parseDouble(response.toString());
				System.out.println("Precio de BTC actualizado");
			}
		} catch (Exception e) {
			System.out.println("Error al consultar cotización");
		}
	};

	public String getNamePayer() {
		return namePayer;
	}

	public void setNamePayer(String namePayer) {
		this.namePayer = namePayer;
	}

	public String getNickPayer() {
		return nickPayer;
	}

	public void setNickPayer(String nickPayer) {
		this.nickPayer = nickPayer;
	}

	public String getEmailPayer() {
		return emailPayer;
	}

	public void setEmailPayer(String emailPayer) {
		this.emailPayer = emailPayer;
	}

	public String getNameCollector() {
		return nameCollector;
	}

	public void setNameCollector(String nameCollector) {
		this.nameCollector = nameCollector;
	}

	public String getNickCollector() {
		return nickCollector;
	}

	public void setNickCollector(String nickCollector) {
		this.nickCollector = nickCollector;
	}

	public void handlePayerNick() {
		if (Validators.validateName(getNickPayer(), "apodo de <b>comprador</b> no válido")) {
			nickPayerValid = Validators.normalize(getNickPayer());
			setNickPayer(nickPayerValid);
			nickPayerStyle = null;
		} else {
			nickPayerStyle = Config.REQUIRED_FIELD;
			nickPayerValid = null;
		}
	}

	public void handleCollector() {
		if (Validators.validateName(getNickCollector(), "apodo de <b>vendedor</b> no válido")) {
			setNickCollector(Validators.normalize(getNickCollector()));
		}
	}

	public Map<String, String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, String> currencies) {
		this.currencies = currencies;
	}

	public String getSelectedCurrency() {
		return selectedCurrency;
	}

	public void setSelectedCurrency(String selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
	}

	public void onCurrencyChange() {
		handleQuantity();
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void handleQuantity() {
		if (getQuantity() != null) {
			if (CurrenciesEnum.BTC.name().equals(getSelectedCurrency()) || getSelectedCurrency() == null) {
				setBtc(getQuantity());
			} else {
				BigDecimal result = new BigDecimal(Double.parseDouble(getQuantity()) * BTC_PER_DOLLAR);
				setBtc(result.setScale(8, BigDecimal.ROUND_HALF_EVEN).toPlainString());
			}
		}
	}

	public void validatePayerEmail() {
		emailPayerStyle = Validators.validateEmail(getEmailPayer(), "email de <b>comprador</b> no válido") ? null
				: Config.REQUIRED_FIELD;
	}

	public void validatePayerName() {
		namePayerStyle = Validators.validateName(getNamePayer(), "nombre de <b>comprador</b> no válido") ? null
				: Config.REQUIRED_FIELD;
	}

	public void validatePayerAddress() {
		if (Validators.validateAddress(getAddressPayer(), "dirección de <b>comprador</b> no válido")) {
			addressPayerTooltip = getAddressPayer();
			addressPayerStyle = null;
		} else {
			addressPayerStyle = Config.REQUIRED_FIELD;
			addressPayerTooltip = null;
		}
	}

	/*
	 * public void validatePayerAddress() { addressPayerStyle =
	 * Validators.validateAddress(getAddressPayer(),
	 * "dirección de <b>comprador</b> no válido")? addressPayerTooltip =
	 * getAddressPayer():Config.REQUIRED_FIELD; }
	 */
	public void validateCollectorEmail() {
		Validators.validateEmail(getEmailCollector(), "email de <b>vendedor</b> no válido");
	}

	public void validateCollectorName() {
		Validators.validateName(getNameCollector(), "nombre de <b>vendedor</b> no válido");
	}

	public String getBtc() {
		return btc;
	}

	public void setBtc(String btc) {
		this.btc = btc;
	}

	public void updateBtc() {
		new Thread(updateBtc).start();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailCollector() {
		return emailCollector;
	}

	public void setEmailCollector(String emailCollector) {
		this.emailCollector = emailCollector;
	}

	public String getNamePayerStyle() {
		return namePayerStyle;
	}

	public String getNickPayerStyle() {
		return nickPayerStyle;
	}

	public String getNickPayerValid() {
		return nickPayerValid;
	}

	public String getEmailPayerStyle() {
		return emailPayerStyle;
	}

	public String getAddressPayer() {
		return addressPayer;
	}

	public void setAddressPayer(String addressPayer) {
		this.addressPayer = addressPayer;
	}

	public String getAddressPayerStyle() {
		return addressPayerStyle;
	}

	public String getAgentAddress() {
		return Config.AGENT_ADDRESS;
	}

	public String getAgentNick() {
		return Config.AGENT_NICK;
	}

	public String getAgentEmail() {
		return Config.AGENT_EMAIL;
	}

	public String getAddressPayerTooltip() {
		return addressPayerTooltip == null ? "" : addressPayerTooltip;
	}

}
