package com.contactinfo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactinfo.app.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
