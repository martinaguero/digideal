package org.trimatek.digideal.ui.beans;

import java.util.logging.Level;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.Context;
import org.trimatek.digideal.ui.utils.Tools;
import org.trimatek.digideal.ui.utils.Validators;

import com.captcha.botdetect.web.jsf.SimpleJsfCaptcha;

public class NavigationView extends CommonView {

	private String message;
	private String messageStyle;
	private String from;
	private String fromStyle;
	private SimpleJsfCaptcha captcha;
	private String captchaCode;
	private String captchaCodeStyle;
	private String result;
	private boolean btnContinueRendered;
	private boolean btnRetryRendered;

	public NavigationView() {
		resetFields();
	}

	public void onLoad() {
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageStyle() {
		return messageStyle;
	}

	public void setMessageStyle(String messageStyle) {
		this.messageStyle = messageStyle;
	}

	public String getFromStyle() {
		return fromStyle;
	}

	public void setFromStyle(String fromStyle) {
		this.fromStyle = fromStyle;
	}

	public SimpleJsfCaptcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(SimpleJsfCaptcha captcha) {
		this.captcha = captcha;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getCaptchaCodeStyle() {
		return captchaCodeStyle;
	}

	public void setCaptchaCodeStyle(String captchaCodeStyle) {
		this.captchaCodeStyle = captchaCodeStyle;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean getBtnContinueRendered() {
		return btnContinueRendered;
	}

	public boolean getBtnRetryRendered() {
		return btnRetryRendered;
	}

	private void resetFields() {
		fromStyle = Context.REQUIRED_FIELD;
		setFrom("");
		messageStyle = Context.REQUIRED_FIELD;
		setMessage("");
		captchaCodeStyle = Context.REQUIRED_FIELD;
		setCaptchaCode("");
		btnContinueRendered = Boolean.FALSE;
		btnRetryRendered = Boolean.FALSE;
	}

	public void validateEmail() {
		if (Validators.validateEmail(getFrom(), Tools.read("error_email", getLocale().toString()),
				Tools.read("error_invalid", getLocale().toString()))) {
			fromStyle = null;
		} else {
			fromStyle = Context.REQUIRED_FIELD;
		}
	}

	public void validateMessage() {
		if (getMessage().length() >= Context.CONTACT_MESSAGE_MIN
				&& getMessage().length() <= Context.CONTACT_MESSAGE_MAX) {
			messageStyle = null;
		} else {
			messageStyle = Context.REQUIRED_FIELD;
		}
	}

	public void validateCaptchaCode() {
		if (getCaptchaCode() != null) {
			captchaCodeStyle = null;
		} else {
			captchaCodeStyle = Context.REQUIRED_FIELD;
		}
	}

	public void sendAction() {
		boolean isHuman = captcha.validate(captchaCode);
		if (isHuman) {
			setResult(Tools.read("contact_send_success", getLocale().toString()));
			resetFields();
			btnContinueRendered = Boolean.TRUE;
			logger.log(Level.INFO, "New contact message sent");
			// Ticket t = new Ticket();
			// t.setLocale(getLocale().toString());
			// t.setDatetime(Instant.now().toEpochMilli() / 1000L + "");
			// t.setFrom(getFrom());
			// t.setText(getMessage());
			// SendTicket.exec(t);			
		} else {
			setResult(Tools.read("contact_send_fail", getLocale().toString()));
			btnContinueRendered = Boolean.FALSE;
			btnRetryRendered = Boolean.TRUE;
			setCaptchaCode("");
			logger.log(Level.INFO, "Captcha error");
		}
	}

	public boolean getSendDisabled() {
		return fromStyle == null && messageStyle == null ? false : true;
	}

	public String getNavigationIndex() {
		return Config.getValue("NAVIGATION_INDEX");
	}

}
