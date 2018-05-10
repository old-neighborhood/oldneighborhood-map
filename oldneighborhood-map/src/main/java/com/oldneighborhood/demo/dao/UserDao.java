package com.oldneighborhood.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import com.oldneighborhood.demo.entity.User;

/**
 * @ClassName: UserDao
 * @Description: 本接口继承了jpa提供的JpaRepository 接口  就会自动继承其方法
 * 				不用在这里添加任何注解  继承之后就自动被spring扫描了
 * 				此时spring 的IOC容器中已经有了UserDao类型的bean
 * @author user005
 * @date 2018年3月26日
 */
public interface UserDao extends JpaRepository<User, String>{
	/**
	 * 按照需要自行添加
	 */
//	@Modifying //update/delete操作需要
//	@Query(
//			value = "insert into user (u_id, u_name, u_password, u_image, u_score, u_tele, u_email, u_birthday, u_signature) values (?, ?, ?, ?, ?, ?, ?, ?, ? )", 
//			nativeQuery = true)
//	public int userSignup(String u_ID, String u_name, String u_password, String u_image, Integer u_score, String u_tele, String u_email, Date u_birthday, String u_signature);
}
