package com.soprasteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.soprasteria.model.Contact;
import com.soprasteria.service.impl.ContactService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest")
public class ContactController {

	@Autowired
	private ContactService service;
	
	@RequestMapping(value = "/show")
	public ModelAndView show()
	{
		ModelAndView model=new ModelAndView();
		List<Contact> list=service.listAllContact();
		model.addObject("list", list);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping("/new")
	public ModelAndView NewContact()
	{
		ModelAndView model=new ModelAndView();
		Contact contact=new Contact();
		model.addObject("contact", contact);
		model.setViewName("new_Contact");
		return model;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editPage(@PathVariable(name="id") Integer id)
	{
		ModelAndView model=new ModelAndView();
		Optional<Contact> contact=service.getOneContact(id);
		model.addObject("contact", contact);
		
		model.setViewName("edit_Contact2");
		return model;
	}
	
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public ResponseEntity<?> getAllContact()
	{
		ResponseEntity<?> resp=null;
		try {
			List<Contact> list=service.listAllContact();
			
			if(list!=null && !list.isEmpty())
			{
				resp=new ResponseEntity<List<Contact>>(list,HttpStatus.OK);
			}
			else
			{
				resp=new ResponseEntity<String>("No Data found",HttpStatus.OK);
			}
		}catch (Exception e) {
			// TODO: handle exception
			resp=new ResponseEntity<String>("Unable to found data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value = "/one/{id}",method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity<?> getOne(@PathVariable(name = "id")Integer id)
	{
		ResponseEntity<?> resp=null;
		try {
			//Optional<Contact> contact=service.getOneContact(id);
			Optional<Contact> opt=service.getOneContact(id);
			//System.out.println(opt.get().getId());
			if(opt.isPresent())
				resp=new ResponseEntity<Contact>(opt.get(),HttpStatus.OK);
			
			else
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			// TODO: handle exception
			resp=new ResponseEntity<String>("Unable to found Data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseEntity<String> UpdateContact(@RequestBody Contact contact) {
		
		ResponseEntity<String> resp=null;
		try {
			Boolean exist=service.UpdateContact(contact.getId());
			if(exist) {
			service.SaveContact(contact);
			resp=new ResponseEntity<String>("updated..",HttpStatus.OK);
			}
			else
			{
				resp=new ResponseEntity<String>("Not Exist",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			// TODO: handle exception
			resp=new ResponseEntity<String>("Unable to Update",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;	
	}
	
	@RequestMapping(value = "/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET})
	public ResponseEntity<String> DeleteOneContact(@PathVariable Integer id){
		ResponseEntity<String> resp=null;
		try {
			Boolean exist=service.UpdateContact(id);
			if(exist) {
				service.DeleteContact(id);
				resp=new ResponseEntity<String>("Deleted..",HttpStatus.OK);
			}
			else
			{
				resp=new ResponseEntity<String>("Not Exist..",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			// TODO: handle exception
			resp=new ResponseEntity<String>("Unable to Delete..",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> SaveContact(@ModelAttribute Contact contact){
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.SaveContact(contact);
			resp=new ResponseEntity<String>("Saved successful",HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			resp=new ResponseEntity<String>("Unable to save..",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
}
