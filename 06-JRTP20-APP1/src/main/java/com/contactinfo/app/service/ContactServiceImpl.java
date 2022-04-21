package com.contactinfo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactinfo.app.entity.Contact;
import com.contactinfo.app.repository.ContactRepository;
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repo;
	@Override
	public String upsert(Contact contact) {
		repo.save(contact);
		return "SUCCESS";
	}

	@Override
	public List<Contact> getAllContacts() {
		
		return repo.findAll();
	}

	@Override
	public Contact getContact(int cid) {
		Optional<Contact> findById = repo.findById(cid);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			return null;
		}
	}

	@Override
	public String deleteContact(int cid) {
		repo.deleteById(cid);
		return "Deleted Sucess";
	}

}
