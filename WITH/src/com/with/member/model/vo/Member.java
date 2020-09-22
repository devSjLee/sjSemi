package com.with.member.model.vo;

import java.sql.Date;

public class Member {
	private String id;
	private String password;
	private String nickname;
	private String grade;
	private String phone;
	private String email;
	private String address;
	private Date enrollDate;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String password, String nickname, String grade, String phone, String email, String address,
			Date enrollDate) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.grade = grade;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", nickname=" + nickname + ", grade=" + grade
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", enrollDate=" + enrollDate + "]";
	}

	
	

	

	

	

	
	
	
}
