package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oldneighborhood.demo.entity.Saler;

public interface SalerDao extends JpaRepository<Saler, String>{
	/**
	 * 按照需要自行添加需要的
	 */
	@Modifying //update/delete操作需要
	@Query(value = "update saler set s_password=? where s_email=?", nativeQuery = true)
	public int salerReset(String password, String email);
	
//	@Query(value = "insert into User() value(?1,?2)", nativeQuery = true)
//	public boolean salerSignup(Saler saler);
}