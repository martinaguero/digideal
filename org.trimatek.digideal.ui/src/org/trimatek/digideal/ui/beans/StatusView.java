package org.trimatek.digideal.ui.beans;

import java.util.logging.Level;

import org.trimatek.digideal.ui.Context;
import org.trimatek.digideal.ui.comm.GetStatus;
import org.trimatek.digideal.ui.model.Status;

import com.captcha.botdetect.web.jsf.SimpleJsfCaptcha;

public class StatusView extends CommonView {

	private String id;
	private String idStyle;
	private Status status;
	private SimpleJsfCaptcha captcha;
	private String captchaCode;
	private String captchaCodeStyle;

	public void onLoad() {
		status = null;
	}

	public StatusView() {
		resetFields();
		status = null;
	}

	public void resetFields() {
		idStyle = Context.REQUIRED_FIELD;
		captchaCodeStyle = Context.REQUIRED_FIELD;
		setId("");
		setCaptchaCode("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdStyle() {
		return idStyle;
	}

	public void setIdStyle(String idStyle) {
		this.idStyle = idStyle;
	}

	public boolean getAcceptDisabled() {
		return idStyle == null ? false : true;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public void acceptAction() {
		if (captcha.validate(captchaCode)) {
			status = GetStatus.exec(getId());
			logger.log(Level.INFO, "Status received");
			if (status.getResult() == 200) {
				resetFields();
			}
		} else {
			status = new Status();
			status.setId(getId());
			status.setResult(-1);
			logger.log(Level.INFO, "Status captcha error");
		}
	}

	public void validateId() {
		if (getId().length() < Context.STATUS_SERIAL_MIN) {
			idStyle = Context.REQUIRED_FIELD;
		} else {
			idStyle = null;
		}
	}

	public void validateCaptchaCode() {
		if (getCaptchaCode() != null) {
			captchaCodeStyle = null;
		} else {
			captchaCodeStyle = Context.REQUIRED_FIELD;
		}
	}

	public String getRenderProgressbar() {
		return status == null ? "true" : "false";
	}

	public boolean getBtnRetryRendered() {
		return status != null && status.getResult() != 200 ? true : false;
	}

	public boolean getBtnContinueRendered() {
		return status != null && status.getResult() == 200 ? true : false;
	}

}
