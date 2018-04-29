package org.trimatek.digideal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Receipt {

	@Id
	@GeneratedValue
	private long id;
	private boolean valid;
	private byte[] image;
	private String code;
	
	public Receipt(String code) {
		this.code = code;
		this.setValid(Boolean.FALSE);
	}
	
	public long getId() {
		return id;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean exists() {
		return image != null;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
