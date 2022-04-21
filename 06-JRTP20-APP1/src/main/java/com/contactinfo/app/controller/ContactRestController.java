package com.contactinfo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contactinfo.app.entity.Contact;
import com.contactinfo.app.service.ContactServiceImpl;

@RestController
public class ContactRestController {

	@Autowired
	private ContactServiceImpl service;
	
	@PostMapping("/contact")
	public ResponseEntity<String> contact (@RequestBody Contact contact){
		String status = service.upsert(contact);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
	}

	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts( ){
		List<Contact> allContacts = service.getAllContacts();
		return ResponseEntity.ok().body(allContacts);
	}

	@GetMapping("/contact/{cid}")
	public ResponseEntity<Contact> getContact(@PathVariable int cid){
		Contact contact = service.getContact(cid);
		if(contact==null) {
			return ResponseEntity.notFound().build();
		}else {	
			return ResponseEntity.ok().body(contact);
		}
	}

	@DeleteMapping("/contact/{cid}")
	public ResponseEntity<String> deleteContact(@PathVariable int cid){
		String status = service.deleteContact(cid);
		return ResponseEntity.ok().body(status);
	}
}
