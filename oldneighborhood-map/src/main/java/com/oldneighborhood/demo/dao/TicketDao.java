package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oldneighborhood.demo.entity.Ticket;

public interface TicketDao extends JpaRepository<Ticket, String>{

}
