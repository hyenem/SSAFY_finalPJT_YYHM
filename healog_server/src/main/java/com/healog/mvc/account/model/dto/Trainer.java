package com.healog.mvc.account.model.dto;

import org.springframework.stereotype.Component;

@Component
public class Trainer {
	private String id;
	private String name;
	private String email;
	private String password;
	private String salt;
	private String createdAtDatetime;
	private int gender;
	private String birthday;
	private String phonenumber;
	private int location;
	private int user_count;
	private String img;
	private String bio;
	
	public Trainer() {}
	
	
	public Trainer(String id, String name, String email, String password, String salt, String createdAtDatetime,
			int gender, String birthday, String phonenumber, int location, int user_count, String img, String bio) {
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
		this.location = location;
		this.user_count = user_count;
		this.img = img;
		this.bio = bio;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getUser_count() {
		return user_count;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", salt=" + salt
				+ ", createdAtDatetime=" + createdAtDatetime + ", gender=" + gender + ", birthday=" + birthday
				+ ", phonenumber=" + phonenumber + ", location=" + location + ", user_count=" + user_count + ", img="
				+ img + ", bio=" + bio + "]";
	}
	
	
}
