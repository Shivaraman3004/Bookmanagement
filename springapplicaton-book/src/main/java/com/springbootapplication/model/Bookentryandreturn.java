package com.springbootapplication.model;

import jakarta.persistence.*;

@Table(name="Managmenttable")
@Entity
public class Bookentryandreturn {

	@Id
	@Column(name="Bookid",length=10,nullable=false)
	private String bookid;
	@Column(name="Studentid",length=10,nullable=false)
	private String studentid;
	@Column(name="Studentname",nullable=false)
	private String studentname;
	@Column(name="Bookname",nullable=false)
	private String bookname;
	@Column(name="Date",nullable=false)
	private String date;
	@Column(name="status",nullable=false)
	private String status;


	public Bookentryandreturn(String bookid, String studentid, String studentname, String bookname, String date,
			String status) {
		super();
		this.bookid = bookid;
		this.studentid = studentid;
		this.studentname = studentname;
		this.bookname = bookname;
		this.date = date;
		this.status = status;
	}
	
	public Bookentryandreturn() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Bookentryandreturn [ bookid=" + bookid + ", studentid=" + studentid + ", studentname="
				+ studentname + ", bookname=" + bookname + ", date=" + date + "]";
	}
	
	
	
}
