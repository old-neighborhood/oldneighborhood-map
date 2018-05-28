package com.oldneighborhood.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
/**
 * @ClassName: Ticket  
 * @Description: 票务统计/日
 * @author user005  
 * @date 2018年4月4日  
+----------------+-------------+------+-----+---------+-------+
| Field          | Type        | Null | Key | Default | Extra |
+----------------+-------------+------+-----+---------+-------+
| ticket_ID      | char(32)    | NO   | PRI | NULL    |       |
| ticket_date    | date        | NO   |     | NULL    |       |
| ticket_amount  | int(11)     | NO   |     | NULL    |       |
| ticket_online  | int(11)     | NO   |     | NULL    |       |
| ticket_offline | int(11)     | NO   |     | NULL    |       |
| site_name      | varchar(32) | YES  |     | NULL    |       |
+----------------+-------------+------+-----+---------+-------+
 */
@Entity
@Table(name="ticket_data")
@Data
public class Ticket implements Serializable{
	private static final long serialVersionUID = -5606528162433653334L;
	
	private String ticket_ID;
	private String site_name;
	private Date ticket_date;
	private int ticket_online;
	private int ticket_offline;
	private int ticket_amount;
	//请求返回的数据
	public Ticket(String site_name, Date ticket_date, int ticket_online, int ticket_offline, int ticket_amount) {
		super();
		this.site_name = site_name;
		this.ticket_date = ticket_date;
		this.ticket_online = ticket_online;
		this.ticket_offline = ticket_offline;
		this.ticket_amount = ticket_amount;
	}
	
	
	
	
}
