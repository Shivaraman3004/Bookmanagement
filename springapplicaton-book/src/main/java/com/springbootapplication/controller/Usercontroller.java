package com.springbootapplication.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootapplication.Bookdao.Bookdao;
import com.springbootapplication.entryreturndao.Entryreturndao;
import com.springbootapplication.logindao.Logindao;
import com.springbootapplication.model.Bookdetails;
import com.springbootapplication.model.Bookentryandreturn;
import com.springbootapplication.model.Login;
import com.springbootapplication.model.Studentdetails;
import com.springbootapplication.studentdao.Studentdao;


@Controller
@RequestMapping("/libraryportal/")
public class Usercontroller {

	@Autowired
	private Studentdao sdao;
	@Autowired
	private Bookdao bdao;
	@Autowired
	private Entryreturndao erdao;
	@Autowired
	private Logindao ldao;
	@GetMapping("/home")
	public String home()
	{
		return "home.html";
	}
	@GetMapping("/admin/home")
	public String adminhome()
	{
		return "adminhome";
	}
	@GetMapping("/showbookid")
	public String bookidst(Model model)
	{
		List<Bookdetails> list=new ArrayList<>();
	    list=bdao.findAll();
	    String st=null;
	    for(Bookdetails s:list)
	    {
	    	if(s.getBookid().startsWith("LP"))
	         {
	        	   st=s.getBookid();
	         }
	    }
	   String str=st.substring(2,st.length());
	   int i=Integer.parseInt(str);
	   i++;
	   String res="";
	   if(i<=99)
	   {
		   res="LP0"+i;
	   }
	   else
	   {
		    res="LP"+i;
	   }
	 
	   model.addAttribute("str",res);		
	   return "showingbookidst";
	}

	
	@GetMapping("/admin/addstudent")
	public String  addstudent()
	{
		return "addstudent";
	}
	@PostMapping("/check")
	public String  check(Login obj,Model model)
	{
	   String id=obj.getId().toUpperCase();
	   String pass=obj.getPassword().toUpperCase();
	   Login obj1=ldao.findById(id).orElse(new Login());
	   String pass1;
	   pass1=(obj1.getPassword()!=null)?obj1.getPassword().toUpperCase():"";
	   if(pass1.equals(pass))
	   {
		   return "redirect:/libraryportal/admin/home";
	   }
	   else
	   {
		   String msg="Sorry Inavlid User Id and Password";
		   model.addAttribute("message",msg);
		   return "/invalidst";
	   }
		
	}
	@GetMapping("/invalidst")
	public String invalidst()
	{
		return "invalidst";
	}
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	@PostMapping("/admin/save")
	public String save(Studentdetails s,Model model)
	{
        String studentname=s.getStudentname().toUpperCase();
        String studentrollno=s.getStudentrollno().toUpperCase();
        String password=s.getPassword().toUpperCase();
        
        s.setPassword(password);
        s.setStudentrollno(studentrollno);
        s.setStudentname(studentname);
		sdao.save(s);
		String msg="Student added to database Successfully.";
		model.addAttribute("message",msg);
		return "invalid";
	}
	@GetMapping("/addbook")
	public String addbookst()
	{
		return "addbookst";
	}
	@GetMapping("/admin/addbook")
	public String addbook()
	{
		return "addbook";
	}
	@GetMapping("/savebookst")
	public String savebookst(Bookdetails book,String studentrollno,String password,Model model)
	{
		String rollno=studentrollno.toUpperCase();
		String pass=password.toUpperCase();
		 String bookid=book.getBookid().toUpperCase();
		Studentdetails obj=sdao.findById(rollno).orElse(new Studentdetails());
		String pass1=(obj.getPassword()!=null)?obj.getPassword():"";
		Bookdetails book1=bdao.findById(bookid).orElse(new Bookdetails());
		String s=(book1.getBookid()!=null)?book1.getBookid():"";
		if(s.equals("")&&pass1.equals(pass))
		{
	   
	    String bookname=book.getBookname().toUpperCase();
	    String bookauthor=book.getBookauthor().toUpperCase();
	     book.setBookid(bookid);
	     book.setBookname(bookname);
	     book.setBookauthor(bookauthor);
		bdao.save(book);
		String message="Book Registered Successfully";
		model.addAttribute("message",message);
		return "invalidst";
		}
		else if(s.length()!=0)
		{
			String message="Book is already registered.";
			model.addAttribute("message",message);
			return "invalidst";
		}
		else if(!pass1.equals(pass))
		{
			String message="Invalid password.";
			model.addAttribute("message",message);
			return "invalidst";
		}
		else
		{
			String message="Book is not registered in Database.";
			model.addAttribute("message",message);
			return "invalidst";
		}
	}
	@GetMapping("/admin/savebook")
	public String savebook(Bookdetails book,Model model)
	{
	    String bookid=book.getBookid().toUpperCase();
	    String  bookname=book.getBookname().toUpperCase();
	    String bookauthor=book.getBookauthor().toUpperCase();
	     book.setBookid(bookid);
	     book.setBookname(bookname);
	     book.setBookauthor(bookauthor);
		bdao.save(book);
		String msg="Book registered Successfully.";
		model.addAttribute("message",msg);
		return "invalid";
	
	}
	
	@GetMapping("/admin/searchstudent")
	public String searchstudent(@RequestParam String s,Model model)
	{
		String str=s.toUpperCase();
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
		else if(str.charAt(i)=='f'||str.charAt(i)=='F'||str.charAt(i)=='l'||str.charAt(i)=='L')
		{
			if(str.length()<2||str.length()<1){
				
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
			else if(str.length()>=2)
			{
				if(str.charAt(1)=='d'||str.charAt(1)=='D'||str.charAt(1)=='2'||str.charAt(1)=='2')
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
			}
				else if(str.length()>=3)
			{
				if(Character.isDigit(str.charAt(2)))
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
		if(studentdetails.size()>0)
		{
		model.addAttribute("studentdetails",studentdetails);
		return "showstudent";
		}
		else
		{
			model.addAttribute("message","No Record Found");
			return "showstudent";
		}
	}
	@GetMapping("/admin/searchbook")
	public String searchbook(@RequestParam String s,Model model)
	{
		String str=s.toUpperCase();
		int i=2,j=0,z=1;
		ArrayList<Bookdetails> bookdetails=new ArrayList<>();
		 if(str.charAt(j)=='f'||str.charAt(j)=='F'||str.charAt(j)=='l'||str.charAt(j)=='L')
		{
			if(Character.isDigit(str.charAt(z)))
			{
				List<Bookdetails> list=bdao.findAll();
				for(Bookdetails st:list)
				{
					String stname=st.getStudentid();
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
				String stname=st.getBookid();
				if(stname.indexOf(str)!=-1)
				{
					bookdetails.add(st);
				}
		}
			}
		}
		else if(Character.isDigit(str.charAt(i)))
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
		
		else if(Character.isDigit(str.charAt(j))&&!Character.isDigit(str.charAt(i))){
			List<Bookdetails> list=bdao.findAll();
			for(Bookdetails st:list)
			{
				String stname=st.getStudentid();
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
		 if(bookdetails.size()>0)
			{
			 model.addAttribute("bookdetails",bookdetails);
				return "showbook";
			}
			else
			{
				model.addAttribute("message","No Record Found");
				return "showbook";
			}
		
	}
	@GetMapping("/bookentry")
	public String bookentryst()
	{
		return "bookentryst.html";
	}
	@GetMapping("/admin/return")
	public String bookreturnst()
	{
		return "bookreturnst.html";
	}
	
	
	@Transactional
	@GetMapping("/entry")
	public String entryst(String bookid,String studentid,String password,Model model)
	{
		Bookentryandreturn entry=new Bookentryandreturn();
		String s=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String studentname="";
		String bookname="";
		String bookid1=bookid.toUpperCase();
		String studentid1=studentid.toUpperCase();
		String pass1=password.toUpperCase();
		Bookdetails books=bdao.findById(bookid1).orElse(new Bookdetails());
		bookname=(books.getBookname()!=null)?books.getBookname():"";
		Studentdetails student=sdao.findById(studentid1).orElse(new Studentdetails());
		studentname=(student.getStudentname()!=null)?student.getStudentname():"";
	    String available=(books.getAvailability()!=null)?books.getAvailability():"";
		int count=student.getBookcount();
		String status="BORROWED";
		String passq=(student.getPassword()!=null)?student.getPassword():"";
		if(available.equals("YES")&&!studentname.equals("")&&passq.equals(pass1))
		{
			entry.setBookid(bookid1);
			entry.setStudentid(studentid1);
			entry.setStudentname(studentname);
			entry.setBookname(bookname);
			entry.setTakendate(s);
			entry.setStatus(status);
			entry.setReturndate("-");
			sdao.updatecount(studentid, (++count));
			bdao.updatestudentname(bookid, studentname);
			bdao.updatestudetid(bookid, studentid1);
			bdao.updateavailable(bookid, "NO");
		    erdao.save(entry);
			String message="Book Registered Successfully. ";
		    model.addAttribute("message",message);
			return "/invalidst";
		}
		else if(available.equals(""))
		{
			String message="Book not in database";
			model.addAttribute("message",message);
			return "/invalidst";
		}
		else if(student.getPassword()!=pass1)
		{
			String message="Password is Incorrect";
			model.addAttribute("message",message);
			return "/invalidst";
		}
		
		else if(studentname.equals(""))
		{
			
			String message="Invaild in Student rollno";
			model.addAttribute("message",message);
			return "/invalidst";
		}	
		
		else
		{
			if(available.equals("NO"))
			{
				String message="The Book is Already Taken";
				model.addAttribute("message",message);
				return "/invalidst";
			}
			else
			{
			String message="please check Both rollno";
			model.addAttribute("message",message);
			return "/invalidst";

			}
		}
		
		
	}
		
	
	
	@Transactional
	@GetMapping("/admin/returnbook")
	public String returnbookst(String bookid,String password,Model model)
	{
		Bookentryandreturn es=new Bookentryandreturn();
		String s=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String bookid1=bookid.toUpperCase();
		String pass1=password.toUpperCase();
		String sta="BORROWED";
        es=erdao.findByBookidAndStatus(bookid1, sta);
        int id=(es.getId()!=0)?es.getId():0;
	    String status=(es.getStatus()!=null)?es.getStatus():"";
		String studentidret=(es.getStudentid()!=null)?es.getStudentid():"";
		Studentdetails student=sdao.findById(studentidret).orElse(new Studentdetails());
	    String pass=(student.getPassword()!=null)?student.getPassword():"";
	    String returnd=(es.getReturndate()!=null)?es.getReturndate():"";
		if(status.equals("BORROWED")&&pass1.equals("FD100")&&returnd.equals("-"))
	    {   
			erdao.updatereturndate(id, s);
			erdao.updatestatus(id, "RETURNED");
	    	bdao.updateavailable(bookid, "YES");
	    	bdao.updatestudentname(bookid, "Null");
	    	bdao.updatestudetid(bookid, "Null");
	    	int count=student.getBookcount();
	    	sdao.updatecount(studentidret, (--count));
	    	String message="Book returned Successfully.";
	    	model.addAttribute("message",message);
	    	return "/invalid";
	    } 
	    else if(!pass.equals(pass1))
	    {
	    	
	    	String message="Password is Incorrect.";
	    	model.addAttribute("message",message);
	    	return "/invalid";
	    }
	    else
	    {
	    	String message="Invalid please check Book number.";
	    	model.addAttribute("message",message);
	    	return "/invalid";
	    }	
	}
	@GetMapping("/admin/showallstudent")
	public String showallstudent(Model model)
	{
		List<Studentdetails> list=sdao.findAll();
		model.addAttribute("studentdetails", list);
        return "showstudent";
	}
	@GetMapping("/admin/showallbook")
	public String showallbook(Model model)
	{
		List<Bookdetails> list=bdao.findAll();
		model.addAttribute("bookdetails", list);
        return"showbook";
	}
	@GetMapping("/admin/showallmanagement")
	public String showallmanagementtable(Model model)
	{
		List<Bookentryandreturn> list=erdao.findAll();
		model.addAttribute("bookentryandreturn", list);
		return "managetable";
	}
	@GetMapping("/admin/delete/{studentrollno}")
	public String delete(@PathVariable String studentrollno,Model model)
	{
	   sdao.deleteById(studentrollno);
	   String msg="Deleted successfully";
	   model.addAttribute("message",msg);
	   return "invalid";
	}
	@GetMapping("/admin/view/{studentrollno}")
	public String view(@PathVariable String studentrollno,Model model)
	{
	   List<Bookentryandreturn>obj=erdao.findAllByStudentid(studentrollno);
	   if(obj.size()==0)
	   {
		   model.addAttribute("msg","No record found to return book");
	   }
	   else
	   {
	     model.addAttribute("bookentryandreturn",obj);
	   }
		return "showpending";
	}
	@GetMapping("/admin/update")
	public String update()
	{
		return "update";
	}

	@GetMapping("/admin/updatestudent")
	public String updatestudent(Studentdetails s,Model model)
	{
        String studentname=s.getStudentname().toUpperCase(); 
        String studentrollno=s.getStudentrollno().toUpperCase();
        String password=s.getPassword().toUpperCase();
        String rollno=s.getStudentrollno();
        Studentdetails obj=sdao.findById(rollno).orElse(new Studentdetails());
        String studenollno=(obj.getStudentrollno()!=null)?obj.getStudentrollno():"";
        if(!studenollno.equals(""))
        {
        s.setBookcount(obj.getBookcount());
        s.setPassword(password);
        s.setStudentrollno(studentrollno);
        s.setStudentname(studentname);
		sdao.save(s);
		String msg="Updated successfully ";
		model.addAttribute("message",msg);
		return "invalid";
        }
        else
        {
        	String msg="Invalid User Id and Password";
    		model.addAttribute("message",msg);
    		return "invalid";
        }
        	
	}
	@GetMapping("/admin/student")
	public String student(String s,Model model)
	{
		String str=s.toUpperCase();
		List<Bookentryandreturn> list=erdao.findByStudentid(str);

	   model.addAttribute("bookentryandreturn",list);
		return "showpending";
	}
	@GetMapping("/updates")
	public String updates()
	{
		return "updatest";
	}
	@Transactional
	@PostMapping("/updatest")
	public String updatest(String studentrollno,String expassword,String npassword,String cpassword,Model model)
	{
		String str="";
		studentrollno=studentrollno.toUpperCase();
		expassword=expassword.toUpperCase();
		npassword=npassword.toUpperCase();
		 cpassword=cpassword.toUpperCase();
		Studentdetails obj=sdao.findById(studentrollno).orElse(new Studentdetails());
		String pass=(obj.getPassword()!=null)?obj.getPassword():"";
		if((pass.equals(expassword))&&cpassword.equals(npassword))
		{
			 str="Password changed successfully.";
			model.addAttribute("message",str);
			sdao.updatepassword(studentrollno, npassword);
	    }
		else if(obj.getStudentrollno()==null)
		{
			str="Invalid StudentId";
			model.addAttribute("message",str);
		}
		else if(!cpassword.equals(npassword))
		{
			model.addAttribute("message",str);
		}
		else
		{
		str="Old Password is Invalid.Contact the Library Incharge";
		model.addAttribute("message",str);
		}
		return "invalidst";
	}
	@GetMapping("/showinfo")
    public String info()
    {
    	return "showinfo";
    }
	@GetMapping("/info")
    public String showinfo(String id,String password,Model model,Model ob)
    {
		String studentroll=id.toUpperCase();
		String studentpass=password.toUpperCase();
		Studentdetails obj=sdao.findById(studentroll).orElse(new Studentdetails());
		String pass=(obj.getPassword()!=null)?obj.getPassword():"";
		String username=(obj.getStudentname()!=null)?obj.getStudentname():"";
	   	if(pass.equals(studentpass))
		{ 
			model.addAttribute("str",username);
			ob.addAttribute("studentdetails",obj);
			List<Bookentryandreturn> obj1=erdao.findByStudentid(studentroll);
			
			if(obj1.size()==0)
			{
				model.addAttribute("message","No Record found to return the book");
				return "showinformation";
				
			}
			else
			{
				model.addAttribute("bookentryandreturn",obj1);
				return "showinformation";
			}
			}
		else
		{
			model.addAttribute("message","Invalid user or Password is incorrect");
			return "invalidst";
		}
	   	
    }
	   	@GetMapping("/searchinfo")
	   	public String showstudentbook(@RequestParam String name,Model model)
	   	{
	   		String str=name.toUpperCase();
	   		ArrayList<Bookdetails> bookdetails=new ArrayList<>();
	   		List<Bookdetails> list=bdao.findAll();
	   		for(Bookdetails s:list)
	   		{
	   			if(s.getBookname().indexOf(str)!=-1)
	   			{
	   				bookdetails.add(s);
	   			}
	   			if(s.getBookauthor().indexOf(str)!=-1)
	   			{
	   				if(!bookdetails.contains(s))
	   				{
	   					bookdetails.add(s);
	   				}
	   				
	   			}
	   		}
	   		if(bookdetails.size()>0)
	   		{
	   		
	   		model.addAttribute("bookdetails",bookdetails);
	   		return "studentshowbook";
	   		}
	   		else
	   		{
	   		  model.addAttribute("message","No record found");
	   		  return "studentshowbook";
	   		}
	   	}
	   	@Transactional
	   	@GetMapping("/admin/Pending")
	   	public String showpending(Model model)
	   	{
	   		List<Bookentryandreturn> list=erdao.findAllByStatus("BORROWED");
			model.addAttribute("bookentryandreturn", list);
			return "managetable";
	   		
	   	}
	   	
    	
    }
