package com.soprasteria.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
