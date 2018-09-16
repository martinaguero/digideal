package org.trimatek.digideal.comm.mail;

public class Template {

	private String html;

	public Template(String html) {
		this.html = html;
	}

	public Template setHtml(String html) {
		this.html = html;
		return this;
	}

	public String toHtml() {
		html = html.replace("\"#{button}\"", "");
		return html;
	}

	public Template setSalutation(String salutation) {
		html = html.replace("\"#{salutation}\"", salutation);
		return this;
	}

	public Template setHi(String hi) {
		html = html.replace("\"#{hi}\"", hi);
		return this;
	}

	public Template setContent1(String content1) {
		html = html.replace("\"#{content1}\"", content1);
		return this;
	}

	public Template setContent2(String content2) {
		html = html.replace("\"#{content2}\"", content2);
		return this;
	}

	public Template setPreview(String preview) {
		html = html.replace("\"#{preview}\"", preview);
		return this;
	}

	public Template setSupport(String needSupport, String contactUs, String supportUrl) {
		html = html.replace("\"#{need_support}\"", needSupport);
		html = html.replace("\"#{contact_us}\"", contactUs);
		html = html.replace("\"#{support_url}\"", supportUrl);
		return this;
	}

}
