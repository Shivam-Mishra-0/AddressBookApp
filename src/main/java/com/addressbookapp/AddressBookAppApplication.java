
package com.addressbookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressbookapp.model.Contact;

@SpringBootApplication
public class AddressBookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookAppApplication.class, args);

// You can temporarily test(feature/uc-02-add-contact-console) it inside AddressBookApplication:
//
//		Contact contact = new Contact(
//		        "Shivam",
//		        "Mishra",
//		        "ABC Street",
//		        "Bhopal",
//		        "MP",
//		        "462002",
//		        "9999999999",
//		        "shivam@gmail.com"
//		);
//
//		System.out.println(contact);
		
	}

}
