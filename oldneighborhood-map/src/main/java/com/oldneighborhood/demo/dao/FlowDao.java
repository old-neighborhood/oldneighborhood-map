package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oldneighborhood.demo.entity.Flow;

public interface FlowDao extends JpaRepository<Flow, String>{

}
