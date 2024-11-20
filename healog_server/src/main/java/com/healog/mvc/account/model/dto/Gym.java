package com.healog.mvc.account.model.dto;

import org.springframework.stereotype.Component;

@Component
public class Gym {
	private int id;
	private String name;
	private String address;
	private String phonenumber;
	
	public Gym() {}
	
	public Gym(int id, String name, String address, String phonenumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	@Override
	public String toString() {
		return "Gym [id=" + id + ", name=" + name + ", address=" + address + ", phonenumber=" + phonenumber + "]";
	}
	
}
