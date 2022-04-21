package com.contactinfo.app.service;

import java.util.List;
import java.util.Optional;

import com.contactinfo.app.entity.Contact;

public interface ContactService {

	String upsert (Contact contact);  

	List<Contact> getAllContacts( );

	Contact getContact (int cid);

	String deleteContact (int cid);
}
