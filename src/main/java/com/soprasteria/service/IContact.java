package com.soprasteria.service;

import java.util.List;
import java.util.Optional;

import com.soprasteria.model.Contact;

public interface IContact {
	
	public List<Contact> listAllContact();
	
	public Optional<Contact> getOneContact(Integer id);
	
	public Integer SaveContact(Contact contact);
	
	public Boolean UpdateContact(Integer id);
	
	public void DeleteContact(Integer id);
	
	
	

}
