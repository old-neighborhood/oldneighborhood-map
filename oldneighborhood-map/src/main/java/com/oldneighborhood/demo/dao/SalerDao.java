package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oldneighborhood.demo.entity.Saler;

public interface SalerDao extends JpaRepository<Saler, String>{
	/**
	 * 按照需要自行添加需要的
	 */
//	@Query(value = "insert into User() value(?1,?2)", nativeQuery = true)
//	public boolean salerSignup(Saler saler);
}