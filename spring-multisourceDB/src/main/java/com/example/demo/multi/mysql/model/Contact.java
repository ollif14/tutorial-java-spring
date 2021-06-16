package com.example.demo.multi.mysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	
	private long contactId;
	private String contactName;
	private String contactEmail;
	
	public Contact() {
		
	}

	public Contact(long contactId, String contactName, String contactEmail) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	@Column(name = "contact_name", nullable = false)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "contact_email", nullable = false)
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Contact [id=" + contactId + ", Name=" + contactName + ", email =" + contactEmail + "]";
	}
	
	
	
}
