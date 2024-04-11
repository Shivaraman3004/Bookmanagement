package com.springbootapplication.logindao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.model.Login;

public interface Logindao extends JpaRepository<Login,String> {

}
