package com.healog.mvc.account.model.dto;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String id;
	private String name;
	private String email;
	private String password;
	private String salt;
	private String createdAtDatetime;
	private int gender;
	private String birthday;
	private String phonenumber;
	private int trainerExist;
	private String type = "user";
	
	public User(){}
	
	
	public User(String id, String name, String email, String password, String salt, String createdAtDatetime, int gender,
			String birthday, String phonenumber, int trainerExist) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.createdAtDatetime = createdAtDatetime;
		this.gender = gender;
		this.birthday = birthday;
		this.phonenumber = phonenumber;
		this.trainerExist = trainerExist;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatedAtDatetime() {
		return createdAtDatetime;
	}
	public void setCreatedAtDatetime(String createdAtDatetime) {
		this.createdAtDatetime = createdAtDatetime;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getTrainerExist() {
		return trainerExist;
	}
	public void setTrainerExist(int trainerExist) {
		this.trainerExist = trainerExist;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createdAtDatetime=" + createdAtDatetime + ", gender=" + gender + ", birthday=" + birthday
				+ ", phonenumber=" + phonenumber + ", trainerExist=" + trainerExist + "]";
	}
	
	
}
