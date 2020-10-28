package com.soprasteria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soprasteria.dao.ContactRepository;
import com.soprasteria.model.Contact;
import com.soprasteria.service.IContact;

@Component
public class ContactService implements IContact {

	@Autowired
	private ContactRepository repo;
	
	@Override
	public List<Contact> listAllContact() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Contact> getOneContact(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Integer SaveContact(Contact contact) {
		// TODO Auto-generated method stub
		return repo.save(contact).getId();
	}

	@Override
	public Boolean UpdateContact(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public void DeleteContact(Integer id) {
		// TODO Auto-generated method stub
			repo.deleteById(id);
	}

}
