package com.oldneighborhood.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
/**
 * @ClassName: ParkingLot  
 * @Description: 停车位实时统计
 * @author user005  
 * @date 2018年4月4日  
+--------------+-------------+------+-----+-------------------+-----------------------------+
| Field        | Type        | Null | Key | Default           | Extra                       |
+--------------+-------------+------+-----+-------------------+-----------------------------+
| pl_ID        | char(32)    | NO   | PRI | NULL              |                             |
| pl_now       | timestamp   | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| pl_amount    | int(11)     | NO   |     | NULL              |                             |
| pl_available | int(11)     | NO   |     | NULL              |                             |
| site_name    | varchar(32) | NO   |     | NULL              |                             |
+--------------+-------------+------+-----+-------------------+-----------------------------+
其实无须持久化
 */
@Entity
@Table(name="parkinglot_data")
@Data
public class ParkingLot implements Serializable{
	private static final long serialVersionUID = 833945599575420110L;
	
	private String pl_ID;
	private String site_name;
	private Timestamp pl_now;
	private int pl_amount;
	private int pl_available;
	
	public ParkingLot(String site_name, Timestamp pl_now, int pl_amount, int pl_available) {
		super();
		this.pl_now = pl_now;
		this.site_name = site_name;
		this.pl_now = pl_now;
		this.pl_amount = pl_amount;
		this.pl_available = pl_available;
	}
	
	
}
