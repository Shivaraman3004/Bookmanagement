package com.springbootapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="studentdetails")
public class Studentdetails {
	
	@Id
	@Column(name="studentrollno",length=10,nullable=false)
	private String studentrollno;
	@Column(name="studentname",length=50,nullable=false)
	private String studentname;
	@Column(name="password",nullable=false)
	private String password;
	@Column(name="bookcount",nullable=false)
	private int bookcount;
	
	
	public Studentdetails(String studentrollno, String studentname, String password, int bookcount) {
		this.studentrollno = studentrollno;
		this.studentname = studentname;
		this.password = password;
		this.bookcount = bookcount;
	}
	
	public Studentdetails() {
		
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentrollno() {
		return studentrollno;
	}
	public void setStudentrollno(String studentrollno) {
		this.studentrollno = studentrollno;
	}
	public int getBookcount() {
		return bookcount;
	}
	public void setBookcount(int bookcount) {
		this.bookcount = bookcount;
	}
	@Override
	public String toString() {
		return "Studentdetails [ studentname=" + studentname + ", studentrollno=" + studentrollno
				+ ", bookcount=" + bookcount + "]";
	}
	
	
	
	

	
	
	
}
