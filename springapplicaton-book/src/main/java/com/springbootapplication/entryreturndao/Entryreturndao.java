package com.springbootapplication.entryreturndao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootapplication.model.Bookentryandreturn;

@Repository
public interface Entryreturndao extends JpaRepository<Bookentryandreturn ,Integer> {



	List<Bookentryandreturn> findAllByStudentid(String s);
	List<Bookentryandreturn> findAllByBookid(String s);
	List<Bookentryandreturn> findByStudentid(String s);
	List<Bookentryandreturn> findByBookid(String s);
	Bookentryandreturn findByBookidAndStatus(String bookid,String status);
	@Modifying
	@Query(value="update Managmenttable set returndate=?2 where id=?1",nativeQuery=true)
	void updatereturndate(Integer id,String s);
	@Modifying
	@Query(value="update Managmenttable set status=?2 where id=?1",nativeQuery=true)
	void updatestatus(Integer id,String s);
	List<Bookentryandreturn> findAllByStatus(String s);
}
