package br.com.javamagazine.contacts.bean;

public class Email {

	private String type;

	private String address;

	public Email() {
	}

	public Email(String type, String address) {
		this.type = type;
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		StringBuilder email = new StringBuilder();
		email.append("{ ");
		email.append(getType() + ":" + getAddress());
		email.append(" }");

		return email.toString();
	}

}
