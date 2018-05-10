package com.oldneighborhood.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
* @ClassName: User  
* @Description: User注册用户的实体类
* @author user005  
* @date 2018年3月21日  
*
 */
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements Serializable{
	
	private static final long serialVersionUID = 3179473115848813375L;
	@Id
//	@GeneratedValue(generator = "jpa-uuid")
	@Column(columnDefinition="char(32) not null")
	private String u_ID;
	@NonNull
	@Column(columnDefinition="varchar(32) not null")
	private String u_name;
	@NonNull
	@Column(columnDefinition="varchar(32) not null")
	private String u_password;
	@NonNull
	@Column(columnDefinition="varchar(50) not null")
	private String u_email;
	
	@Column(columnDefinition="varchar(255) not null default '/image/icons-portrait.png' ")
	private String u_image;
	@Column(columnDefinition="int not null default 0")
	private Integer u_score;
	@Column(columnDefinition="char(11) null default null")
	private String u_tele;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private Date u_birthday;
//	private String address;
	@Column(columnDefinition="varchar(100) not null default '这个新手不太冷！'")
	private String u_signature;
	@Column(columnDefinition="timestamp not null default now()" , updatable=false)
	private Timestamp u_date;

}
