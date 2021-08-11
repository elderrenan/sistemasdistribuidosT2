package br.com.javamagazine.contacts.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact {

	private int id;

	private String name;

	private List<Phone> phones = new ArrayList<Phone>();

	private List<Email> emails = new ArrayList<Email>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Contact) {
			Contact c = (Contact) obj;
			result = c.getId() == this.getId();
		}

		return result;
	}

	@Override
	public int hashCode() {

		return getId() ^ 7;
	}

	@Override
	public String toString() {
		StringBuilder contact = new StringBuilder();
		contact.append("{ ");
		contact.append(getId());
		contact.append(", ");
		contact.append(getName());
		contact.append(", ");
		contact.append(Arrays.toString(getEmails().toArray()));
		contact.append(", ");
		contact.append(Arrays.toString(getPhones().toArray()));
		contact.append(" }");

		return contact.toString();
	}

}
