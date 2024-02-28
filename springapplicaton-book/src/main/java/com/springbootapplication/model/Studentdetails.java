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
	@Column(name="bookcount",nullable=false)
	private int bookcount;
	
	
	public Studentdetails( String studentname, String studentrollno, int bookcount) {
		super();
		this.studentname = studentname;
		this.studentrollno = studentrollno;
		this.bookcount = bookcount;
	}
	public Studentdetails() {
		super();
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
