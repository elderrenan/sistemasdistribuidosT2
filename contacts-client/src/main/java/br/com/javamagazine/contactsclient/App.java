package br.com.javamagazine.contactsclient;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import br.com.javamagazine.contacts.bean.Contact;
import br.com.javamagazine.contacts.bean.Email;
import br.com.javamagazine.contacts.bean.Phone;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {

		ContactsClient client = new ContactsClient(
				"http://localhost:8080/contacts");

		// pega
		Contact c = client.findById(1);
		System.out.println(c);

		// lista
		List<Contact> contacts = client.findAll();
		System.out.println(Arrays.toString(contacts.toArray()));

		// adiciona
		Contact contact1 = new Contact();
		contact1.setName("pcmnac ");
		Email email = new Email("casa", "pcmnac@gmail.com");
		contact1.getEmails().add(email);
		Phone phone = new Phone("tim", "8199921448");
		contact1.getPhones().add(phone);
		client.add(contact1);

		// lista
		contacts = client.findAll();
		System.out.println(Arrays.toString(contacts.toArray()));

		// edita
		Contact toUpdate = contacts.get(contacts.size() - 1);
		toUpdate.setName(toUpdate.getName() + "(updated)");
		client.update(toUpdate);

		// lista
		contacts = client.findAll();
		System.out.println(Arrays.toString(contacts.toArray()));

		// apaga
		Contact toRemove = contacts.get(contacts.size() - 1);
		client.delete(toRemove);

		// lista
		contacts = client.findAll();
		System.out.println(Arrays.toString(contacts.toArray()));

	}
}
