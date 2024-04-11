package com.springbootapplication.Bookdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootapplication.model.Bookdetails;

@Repository
public interface Bookdao extends JpaRepository<Bookdetails,String>{

	public List<Bookdetails> findByBookid(String s);
	public List<Bookdetails> findByBookname(String s);
	public List<Bookdetails> findByStudentid(String s);
	
	@Modifying
	@Query(value="update Bookdetails set studentname=?2 where bookid=?1",nativeQuery=true)
	void updatestudentname(String bookid,String studentname);
	
	
	@Modifying
	@Query(value="update Bookdetails set studentid=?2 where bookid=?1",nativeQuery=true)
	void updatestudetid(String bookid,String studentid);
	
	@Modifying
	@Query(value="update Bookdetails set availability=?2 where bookid=?1",nativeQuery=true)
	void updateavailable(String bookid,String available);
	
	@Modifying
	@Query(value="update Bookdetails set manageid=?2 where bookid=?1",nativeQuery=true)
	void updatemangeid(String bookid,Integer id);
}
