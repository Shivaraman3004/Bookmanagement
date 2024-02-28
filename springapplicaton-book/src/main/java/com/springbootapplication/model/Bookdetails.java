package com.springbootapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="Bookdetails")
public class Bookdetails {
	@Id
	@Column(name="bookid",length=20)
	private String bookid;
	@Column(name="bookname",nullable=false)
	private String bookname;
	@Column(name="bookauthor",nullable=false)
	private String bookauthor;
	@Column(name="studentid",length=20,nullable=false)
	private String studentid;
	@Column(name="studentname",length=200,nullable=false)
	private String studentname;
	@Column(name="availability")
	private String availability;
	
	
		public Bookdetails(String bookname, String bookid, String bookauthor, String studentid, String studentname,
		String availability) {
		super();
	
		this.bookname = bookname;
		this.bookid = bookid;
		this.bookauthor = bookauthor;
		this.studentid = studentid;
		this.studentname = studentname;
		this.availability = availability;
	}
	
	public Bookdetails() {
		super();
	}

	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	@Override
	public String toString() {
		return "Bookdetails [ bookname=" + bookname + ", bookid=" + bookid + ", bookauthor=" + bookauthor
				+ ", studentid=" + studentid + ", studentname=" + studentname + ", availability=" + availability + "]";
	}
	
	
	
	

	

}
