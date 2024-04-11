package com.springbootapplication.model;

import jakarta.persistence.*;

@Table(name="Managmenttable")
@Entity
public class Bookentryandreturn {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="emp",strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name="emp",sequenceName="sq",initialValue=1,allocationSize=1)
    private Integer id;
	@Column(name="bookid",length=10,nullable=false)
	private String bookid;
	@Column(name="studentid",length=10,nullable=false)
	private String studentid;
	@Column(name="studentname",nullable=false)
	private String studentname;
	@Column(name="bookname",nullable=false)
	private String bookname;
	@Column(name="takendate",nullable=false)
	private String takendate;
	@Column(name="returndate",nullable=false)
	private String returndate;
	@Column(name="status",nullable=false)
	private String status;
	
	
	
	public Bookentryandreturn(String bookid, String studentid, String studentname, String bookname, String takendate,
			String returndate, String status, Integer id) {
		this.bookid = bookid;
		this.studentid = studentid;
		this.studentname = studentname;
		this.bookname = bookname;
		this.takendate = takendate;
		this.returndate = returndate;
		this.status = status;
		this.id = id;
	}
	public Bookentryandreturn() {
		
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
	public String getTakendate() {
		return takendate;
	}
	public void setTakendate(String takendate) {
		this.takendate = takendate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Bookentryandreturn [bookid=" + bookid + ", studentid=" + studentid + ", studentname=" + studentname
				+ ", bookname=" + bookname + ", takendate=" + takendate + ", returndate=" + returndate + ", status="
				+ status + ", id=" + id + "]";
	}
	
	

	
	
}
