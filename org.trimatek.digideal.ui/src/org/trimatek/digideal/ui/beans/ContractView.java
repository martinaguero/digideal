package org.trimatek.digideal.ui.beans;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.comm.SendSource;
import org.trimatek.digideal.ui.model.Address;
import org.trimatek.digideal.ui.model.Source;
import org.trimatek.digideal.ui.utils.Geocoder;
import org.trimatek.digideal.ui.utils.PDFBuilder;
import org.trimatek.digideal.ui.utils.SourceBuilder;
import org.trimatek.digideal.ui.utils.Tools;
import org.trimatek.digideal.ui.utils.Validators;

@ManagedBean
public class ContractView {

	protected final static Logger logger = Logger.getLogger(ContractView.class.getName());
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
	private String nameCollectorStyle;
	private String nickCollector;
	private String nickCollectorStyle;
	private String nickCollectorValid;
	private String emailCollector;
	private String emailCollectorStyle;
	private String addressCollectorTooltip;
	private String addressCollector;
	private String addressCollectorStyle;
	private Map<String, String> currencies;
	private String selectedCurrency;
	private String quantity;
	private String quantityStyle;
	private String btc;
	private String item;
	private String itemStyle;
	private String address;
	private String addressStyle;
	private double BTC_PER_DOLLAR = 0.00013828;
	private Source source;
	private boolean dataAuthentic;
	private StreamedContent file;

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
		nameCollectorStyle = Config.REQUIRED_FIELD;
		nickCollectorStyle = Config.REQUIRED_FIELD;
		emailCollectorStyle = Config.REQUIRED_FIELD;
		addressCollectorStyle = Config.REQUIRED_FIELD;
		quantityStyle = Config.REQUIRED_FIELD;
		addressStyle = Config.REQUIRED_FIELD;
		itemStyle = Config.REQUIRED_FIELD;
	}

	Runnable updateBtc = () -> {
		try {
			URL obj = new URL(Config.BTC_PRICE_URL);
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
			System.out.println("Error al consultar cotizaci�n");
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
		if (Validators.validateName(getNickPayer(), "apodo de <b>comprador</b> no v�lido", 1)) {
			nickPayerValid = Validators.normalize(getNickPayer());
			setNickPayer(nickPayerValid);
			nickPayerStyle = null;
		} else {
			nickPayerStyle = Config.REQUIRED_FIELD;
			nickPayerValid = null;
		}
	}

	public void handleCollectorNick() {
		if (Validators.validateName(getNickCollector(), "apodo de <b>vendedor</b> no v�lido", 1)) {
			nickCollectorValid = Validators.normalize(getNickCollector());
			setNickCollector(nickCollectorValid);
			nickCollectorStyle = null;
		} else {
			nickCollectorStyle = Config.REQUIRED_FIELD;
			nickCollectorValid = null;
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
			quantityStyle = null;
		} else {
			quantityStyle = Config.REQUIRED_FIELD;
		}
	}

	public void validatePayerEmail() {
		if (Validators.validateEmail(getEmailPayer(), Tools.msg.getString("error_payer_email"))) {
			emailPayerStyle = null;
			setEmailPayer(emailPayer.toLowerCase());
		} else {
			emailPayerStyle = Config.REQUIRED_FIELD;
		}
	}

	public void validatePayerName() {
		namePayerStyle = Validators.validateName(getNamePayer(), "nombre de <b>comprador</b> no v�lido", 2) ? null
				: Config.REQUIRED_FIELD;
	}

	public void validatePayerAddress() {
		if (Validators.validateAddress(getAddressPayer(), "direcci�n de <b>comprador</b> no v�lido")) {
			addressPayerTooltip = getAddressPayer();
			addressPayerStyle = null;
		} else {
			addressPayerStyle = Config.REQUIRED_FIELD;
			addressPayerTooltip = null;
		}
	}

	public void validateCollectorAddress() {
		if (Validators.validateAddress(getAddressCollector(), "direcci�n de <b>vendedor</b> no v�lido")) {
			addressCollectorTooltip = getAddressCollector();
			addressCollectorStyle = null;
		} else {
			addressCollectorStyle = Config.REQUIRED_FIELD;
			addressCollectorTooltip = null;
		}
	}

	public void validateCollectorEmail() {
		if (Validators.validateEmail(getEmailCollector(), Tools.msg.getString("error_collector_email"))) {
			emailCollectorStyle = null;
			setEmailCollector(emailCollector.toLowerCase());
		} else {
			emailCollectorStyle = Config.REQUIRED_FIELD;
		}
	}

	public void validateCollectorName() {
		nameCollectorStyle = Validators.validateName(getNameCollector(), "nombre de <b>vendedor</b> no v�lido", 2)
				? null
				: Config.REQUIRED_FIELD;
	}

	public void validateAddress() {
		addressStyle = Validators.validateSentenceLength(getAddress(), "direcci�n incompleta", 4) ? null
				: Config.REQUIRED_FIELD;
	}

	public void validateItem() {
		itemStyle = Validators.validateSentenceLength(getItem(), "descripci�n de art�culo o servicio incompleta", 4)
				? null
				: Config.REQUIRED_FIELD;
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

	public String getAddressCollector() {
		return addressCollector;
	}

	public void setAddressCollector(String addressCollector) {
		this.addressCollector = addressCollector;
	}

	public String getNameCollectorStyle() {
		return nameCollectorStyle;
	}

	public String getNickCollectorStyle() {
		return nickCollectorStyle;
	}

	public String getEmailCollectorStyle() {
		return emailCollectorStyle;
	}

	public String getAddressCollectorStyle() {
		return addressCollectorStyle;
	}

	public String getNickCollectorValid() {
		return nickCollectorValid;
	}

	public String getAddressCollectorTooltip() {
		return addressCollectorTooltip == null ? "" : addressCollectorTooltip;
	}

	public String getQuantityStyle() {
		return quantityStyle;
	}

	public String getAddressStyle() {
		return addressStyle;
	}

	public String getItemStyle() {
		return itemStyle;
	}

	public void previewAction() {
		Optional<Address> address = Geocoder.geocode(getAddress());
		if (address.isPresent()) {
			System.out.println(address.get().toString());
			String errors = Validators.validateAddress(address.get());
			if (errors.equals("")) {
				source = SourceBuilder.getSource(this);
			} else {
				source = new Source();
				source.setText(errors);
			}
		}
	}

	public void cancelDraftAction() {
		source = null;
		setDataAuthentic(false);
	}

	public void confirmDraftAction() {
		source.setPdf(PDFBuilder.getPdf(SourceBuilder.formatPDF(source), source.getName(), buildSignature()));		
		SendSource.exec(source);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, Config.NAVIGATION_RESULT);
	}

	public StreamedContent getFile() {
		file = new DefaultStreamedContent(new ByteArrayInputStream(source.getPdf()), "application/pdf",
				(Tools.msg.getString("contract_header") + source.getName() + ".pdf"));
		return file;
	}

	public void closeResultAction() {
		source = null;
		setDataAuthentic(false);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, Config.NAVIGATION_INDEX);
	}

	public String getTooltipPayer() {
		return getNickPayerValid() + " {" + getAddressPayer() + "," + getEmailPayer() + "}";
	}

	public String getTooltipCollector() {
		return getNickCollectorValid() + " {" + getAddressCollector() + "," + getEmailCollector() + "}";
	}

	public String getTooltipAgent() {
		return getAgentNick() + " {" + getAgentAddress() + "," + getAgentEmail() + "}";
	}

	public boolean getPreviewDisabled() {
		return (namePayerStyle == null && nickPayerStyle == null && emailPayerStyle == null && addressPayerStyle == null
				&& nameCollectorStyle == null && nickCollectorStyle == null && emailCollectorStyle == null
				&& addressCollectorStyle == null && quantityStyle == null && addressStyle == null && itemStyle == null
				&& isDataAuthentic() == true) ? false : true;
	}

	public String getDraft() {
		return source != null ? SourceBuilder.formatDraft(source) : "";
	}

	public boolean isDataAuthentic() {
		return dataAuthentic;
	}

	public void setDataAuthentic(boolean dataAuthentic) {
		this.dataAuthentic = dataAuthentic;
	}

	public String getRenderSignatures() {
		return source != null && source.getName() != null ? "true" : "false";
	}

	public String getRenderProgressbar() {
		return source == null ? "true" : "false";
	}

	public String getConfirmDraftDisabled() {
		return source != null && source.getName() != null ? "false" : "true";
	}

	private List<String> buildSignature() {
		List<String> signature = new ArrayList<String>();
		signature.add(getTooltipPayer());
		signature.add(getTooltipCollector());
		signature.add(getTooltipAgent());
		return signature;
	}

}
