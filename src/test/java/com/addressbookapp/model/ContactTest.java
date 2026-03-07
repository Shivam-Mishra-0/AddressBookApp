package com.addressbookapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    @Test
    public void testContactEntity() {

        Contact contact = new Contact();
        
        contact.setFirstName("Shivam");
        contact.setLastName("M");
        contact.setAddress("Patel Nagar");
        contact.setCity("Bhopal");
        contact.setState("MP");
        contact.setZip("462022");
        contact.setPhoneNumber("919876543210");
        contact.setEmail("shivam@example.com");

        assertEquals("shivam", contact.getFirstName());
        assertEquals("Bhopal", contact.getCity());
        assertEquals("shivam@example.com", contact.getEmail());
    }
}