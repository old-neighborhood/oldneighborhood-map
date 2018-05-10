package com.oldneighborhood.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="saler")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder

public class Saler implements Serializable{
	
	private static final long serialVersionUID = -1696099100838066036L;
	
	@Id
	@Column(columnDefinition="char(32) not null")
	private String s_ID;
	@NonNull
	@Column(columnDefinition="varchar(32) not null")
	private String s_name;
	@NonNull
	@Column(columnDefinition="varchar(32) not null")
	private String s_password;
	@NonNull
	@Column(columnDefinition="varchar(50) not null")
	private String s_email;
	@Column(columnDefinition="char(11) null default null")
	private String s_tele;
	
	@Column(columnDefinition="varchar(255) not null default '/image/icons-portrait.png' ")
	private String s_image;
	@Column(columnDefinition="int not null default 0")
	private Integer s_score;
	@Column(columnDefinition="varchar(50) null default null")
	private String s_address;
//	@DateTimeFormat(pattern="yyyy-MM-dd") //标注改属性只接收指定格式的日期  默认  yyyy/MM/dd
//	private Date s_birthday;
	@Column(columnDefinition="varchar(100) not null default '这个新手不太冷！'")
	private String s_signature;
	@Column(columnDefinition="timestamp not null default now()" , updatable=false)
	private Timestamp s_date;

}
