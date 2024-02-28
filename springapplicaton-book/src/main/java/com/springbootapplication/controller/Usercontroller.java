package com.springbootapplication.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.springbootapplication.Bookdao.Bookdao;
import com.springbootapplication.entryreturndao.Entryreturndao;
import com.springbootapplication.model.Bookdetails;
import com.springbootapplication.model.Bookentryandreturn;
import com.springbootapplication.model.Studentdetails;
import com.springbootapplication.studentdao.Studentdao;

@Controller
public class Usercontroller {

	@Autowired
	private Studentdao sdao;
	@Autowired
	private Bookdao bdao;
	@Autowired
	private Entryreturndao erdao;
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	@GetMapping("/addstudent")
	public String  addstudent()
	{
		return "addstudent";
	}
	@PostMapping("/save")
	public String save(Studentdetails s)
	{
        String studentna=s.getStudentname();
        String studentname="";
        String studentid=s.getStudentrollno();
        String studentrollno="";
        for(int i=0;i<studentna.length();i++)
		{
			char ch;
			if(Character.isAlphabetic(studentna.charAt(i)))
			{
				ch=Character.toUpperCase(studentna.charAt(i));
				studentname	=studentname+ch;
			}
			else
			{
				studentname=studentname+studentna.charAt(i);
			}
		}
        
        for(int i=0;i<studentid.length();i++)
    		{
    			char ch;
    			if(Character.isAlphabetic(studentid.charAt(i)))
    			{
    				ch=Character.toUpperCase(studentid.charAt(i));
    				studentrollno=studentrollno+ch;
    			}
    			else
    			{
    				studentrollno=studentrollno+studentid.charAt(i);
    			}
    		}
        s.setStudentrollno(studentrollno);
        s.setStudentname(studentname);
		sdao.save(s);
		return "redirect:/addstudent";
	}
	@GetMapping("/addbook")
	public String addbook()
	{
		return "addbook";
	}
	@PostMapping("/savebook")
	public String savebook(Bookdetails book)
	{
		 
	    String bookiddemo=book.getBookid();
	    String booknamedemo=book.getBookname();
	    String bookauthordemo=book.getBookauthor();
	    String bookid="";
	    String bookname="";
	    String bookauthor="";
	    for(int i=0;i<bookiddemo.length();i++)
	    {
	    	char ch;
	    	if(Character.isAlphabetic(bookiddemo.charAt(i)))
	    	{
	    		ch=Character.toUpperCase(bookiddemo.charAt(i));
	    		bookid=bookid+ch;
	    	}
	    	else {
	       bookid=bookid+bookiddemo.charAt(i);
	    	}
	    }
	    for(int i=0;i<booknamedemo.length();i++)
	    {
	    	char ch;
	    	if(Character.isAlphabetic(booknamedemo.charAt(i)))
	    	{
	    		ch=Character.toUpperCase(booknamedemo.charAt(i));
	    		bookname=bookname+ch;
	    	}
	    	else
	    	{
	       bookname=bookname+booknamedemo.charAt(i);
	    	}
	    }
	    for(int i=0;i<bookauthordemo.length();i++)
	    {
	    	char ch;
	    	if(Character.isAlphabetic(bookauthordemo.charAt(i)))
	    	{
	    		ch=Character.toUpperCase(bookauthordemo.charAt(i));
	    		bookauthor=bookauthor+ch;
	    	}
	    	else
	    	{
	       bookauthor=bookauthor+bookauthordemo.charAt(i);
	    	}
	    }
	    
	     book.setBookid(bookid);
	     book.setBookname(bookname);
	     book.setBookauthor(bookauthor);
		bdao.save(book);
		return "redirect:/addbook";
	}
	
	@GetMapping("/searchstudent")
	public ModelAndView searchstudent(@RequestParam String s)
	{
		String str="";
		for(int j=0;j<s.length();j++)
		{
			char ch ;
			if(Character.isAlphabetic(s.charAt(j)))
			{
               ch=Character.toUpperCase(s.charAt(j));	
               str=str+ch;
            
			}
			else
			{
				str=str+s.charAt(j);
			}
			
		}
		ModelAndView mav=new ModelAndView();
		int i=0;
		ArrayList<Studentdetails> studentdetails =new ArrayList<>();
		if(Character.isDigit(str.charAt(i)))
		{
			List<Studentdetails> list=sdao.findAll();
			for(Studentdetails st:list)
			{
				String strollno=st.getStudentrollno();
				if(strollno.indexOf(str)!=-1)
				{
					studentdetails.add(st);
				}
			}
		}
		else
		{
			
			List<Studentdetails> list=sdao.findAll();
			for(Studentdetails st:list)
			{
				String stname=st.getStudentname();
				if(stname.indexOf(str)!=-1)
				{
					studentdetails.add(st);
				}
			}
		}
		
		mav.addObject("studentdetails",studentdetails);
		mav.setViewName("showstudent");
		return mav;
	}
	@GetMapping("/searchbook")
	public ModelAndView searchbook(@RequestParam String s)
	{
		String str="";
		for(int j=0;j<s.length();j++)
		{
			char ch ;
			if(Character.isAlphabetic(s.charAt(j)))
			{
               ch=Character.toUpperCase(s.charAt(j));	
               str=str+ch;
            
			}
			else
			{
				str=str+s.charAt(j);
			}
			
		}
		ModelAndView mav=new ModelAndView();
		int i=2;
		ArrayList<Bookdetails> bookdetails=new ArrayList<>();
		if(Character.isDigit(str.charAt(i)))
		{
			List<Bookdetails> list=bdao.findAll();
			for(Bookdetails st:list)
			{
				String stname=st.getBookid();
				if(stname.indexOf(str)!=-1)
				{
					bookdetails.add(st);
				}
			}
		}
		else
		{
			List<Bookdetails> list=bdao.findAll();
			for(Bookdetails st:list)
			{
				String stname=st.getBookname();
				if(stname.indexOf(str)!=-1)
				{
					bookdetails.add(st);
				}
			}
		}
		mav.addObject("bookdetails",bookdetails);
		mav.setViewName("showbook");
		return mav;
	}
	@GetMapping("/bookentry")
	public String bookentry()
	{
		return "bookentry";
	}
	@GetMapping("/bookreturn")
	public String bookreturn()
	{
		return "bookreturn";
	}
	
	@Transactional
	@GetMapping("/entry")
	public ModelAndView entry(Bookentryandreturn entry)
	{
		ModelAndView mav=new ModelAndView();
		String s=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		entry.setDate(s);
		String studentname="";
		String bookname="";
		String booid=entry.getBookid();
		String bookid="";
		String studenid=entry.getStudentid();
		String studentid="";
		for(int i=0;i<booid.length();i++)
		{
			char ch;
			if(Character.isAlphabetic(booid.charAt(i)))
			{
				ch=Character.toUpperCase(booid.charAt(i));
				bookid=bookid+ch;
			}
			else
			{
				bookid=bookid+booid.charAt(i);
			}
		}
		for(int i=0;i<studenid.length();i++)
		{
			char ch;
			if(Character.isAlphabetic(studenid.charAt(i)))
			{
				ch=Character.toUpperCase(studenid.charAt(i));
				studentid=studentid+ch;
			}
			else
			{
				studentid=studentid+studenid.charAt(i);
			}
		}
		
		Bookdetails books=bdao.findById(bookid).orElse(new Bookdetails());
		bookname=books.getBookname();
		Studentdetails student=sdao.findById(studentid).orElse(new Studentdetails());
		studentname=(student.getStudentname()!=null)?student.getStudentname():"";
	    String available=(books.getAvailability()!=null)?books.getAvailability():"";
		int count=student.getBookcount();
		String status="BORROWED";
		if(available.equals("YES")&&!studentname.equals(""))
		{
			entry.setBookid(bookid);
			entry.setStudentid(studentid);
			entry.setStudentname(studentname);
			entry.setBookname(bookname);
			sdao.updatecount(studentid, (++count));
			bdao.updatestudentname(bookid, studentname);
			bdao.updatestudetid(bookid, studentid);
			bdao.updateavailable(bookid, "NO");
		    entry.setStatus(status);
			erdao.save(entry);
			mav.setViewName("bookentry");
			return mav;
		}
		else if(available.equals(""))
		{
			String message="please check Book rollno";
			mav.setViewName("invalid");
			mav.addObject("message", message);
			return mav;
		}
		else if(studentname.equals(""))
		{
			
			String message="Invaild in Student rollno";
			mav.setViewName("invalid");
			mav.addObject("message",message);
			return mav;
		}	
		
		else
		{
			if(available.equals("NO"))
			{
				String message="The Book is Already Taken";
				mav.setViewName("invalid");
				mav.addObject("message", message);
				return mav;
			}
			else
			{
			String message="please check Both rollno";
			mav.setViewName("invalid");
			mav.addObject("message", message);
			return mav;

			}
		}
		
		
	}
	@Transactional
	@GetMapping("returnbook")
	public ModelAndView returnbook(Bookentryandreturn entry)
	{
		ModelAndView mav=new ModelAndView();
		String s=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		entry.setDate(s);
		String booid=entry.getBookid();
		String bookid="";
		for(int i=0;i<booid.length();i++)
		{
			char ch;
			if(Character.isAlphabetic(booid.charAt(i)))
			{
				ch=Character.toUpperCase(booid.charAt(i));
				bookid=bookid+ch;
			}
			else
			{
				bookid=bookid+booid.charAt(i);
			}
		}
		Bookentryandreturn books=erdao.findById(bookid).orElse(new Bookentryandreturn());
	    String status=(books.getStatus()!=null)?books.getStatus():"";
	    System.out.println(bookid);
	    if(status.equals("BORROWED"))
	    {
	    	entry.setBookid(bookid);
	    	entry.setStatus("RETURNED");
	    	String bookname=books.getBookname();
	    	entry.setBookname(bookname);
	    	String studentidret=books.getStudentid();
	    	entry.setStudentid(studentidret);
	    	String studentnameret=books.getStudentname();
	    	entry.setStudentname(studentnameret);
	    	erdao.save(entry);
	    	bdao.updateavailable(bookid, "YES");
	    	bdao.updatestudentname(bookid, "");
	    	bdao.updatestudetid(bookid, "");
	    	Studentdetails student=sdao.findById(studentidret).orElse(new Studentdetails());
	    	int count=student.getBookcount();
	    	sdao.updatecount(studentidret, (--count));
	    	mav.setViewName("home");
	    	return mav;
	    }
	    else if(status.equals("RETURNED"))
	    {
	    	mav.setViewName("invalid");
	    	String message="The Book is already Returned";
	    	mav.addObject("message", message);
	    	return mav;
	    }
	    else
	    {
	    	mav.setViewName("invalid");
	    	String message="The Book is Not Taken";
	    	mav.addObject("message", message);
	    	return mav;
	    }	
	}
	@GetMapping("showallstudent")
	public ModelAndView showallstudent()
	{
		List<Studentdetails> list=sdao.findAll();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("showstudent");
		mav.addObject("studentdetails", list);
        return mav;
	}
	@GetMapping("showallbook")
	public ModelAndView showallbook()
	{
		List<Bookdetails> list=bdao.findAll();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("showbook");
		mav.addObject("bookdetails", list);
        return mav;
	}
	@GetMapping("showallmanagement")
	public ModelAndView showallmanagementtable()
	{
		List<Bookentryandreturn> list=erdao.findAll();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("managetable");
		mav.addObject("bookentryandreturn", list);
		return mav;
	}
}
