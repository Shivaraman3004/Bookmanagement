package com.springbootapplication.entryreturndao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.model.Bookentryandreturn;

@Repository
public interface Entryreturndao extends JpaRepository<Bookentryandreturn,String> {

	
}
