package com.springbootapplication.studentdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootapplication.model.Studentdetails;
@Repository
public interface Studentdao extends JpaRepository<Studentdetails,String>{
	
	
	List<Studentdetails> findByStudentrollno(String s);
	List<Studentdetails> findByStudentname(String s);
    
	@Modifying
	@Query(value="update studentdetails set bookcount=?2 where studentrollno=?1",nativeQuery=true)
	void updatecount(String rollno,int count);
	
	@Modifying
	@Query(value="update studentdetails set password=?2 where studentrollno=?1",nativeQuery=true)
	void updatepassword(String rollno,String pass);

}
