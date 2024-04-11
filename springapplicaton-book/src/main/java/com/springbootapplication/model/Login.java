package com.springbootapplication.model;

import jakarta.persistence.*;

@Entity
public class Login {
	
	@Id
	@Column(name="id",nullable=false)
	private String id;
	@Column(name="password",nullable=false)
	private String password;
	
	public Login() {

	}
	public Login(String id, String password) {
		this.id = id;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", password=" + password + "]";
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

}
