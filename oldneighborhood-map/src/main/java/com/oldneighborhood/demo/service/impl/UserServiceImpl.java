package com.oldneighborhood.demo.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldneighborhood.demo.dao.UserDao;
import com.oldneighborhood.demo.entity.User;
import com.oldneighborhood.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 注册功能分为普通用户与商户
	 */
	@Override
	@Transactional // 在进行update/delete操作时需要加上，也可以自行修改管理
	public boolean userSignup(User user) {
		LegalCheckImpl lci = new LegalCheckImpl();
		if (!lci.isUserLegal(user)) {
			System.out.println("注册信息非法！");
			return false;
		}
		/**
		 * UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。
		 * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上
		 * 生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位
		 * 的数字组成,表现出来的形式。由以下几部分的组合：当前日期和时间(UUID的
		 * 第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID
		 * ，则第一个部分不同，其余相同)，时钟序列，全局唯一的IEEE机器识别号（如
		 * 果有网卡，从网卡获得，没有网卡以其他方式获得），UUID的唯一缺陷在于生成
		 * 的结果串会比较长。
		 */
		user.setU_ID(UUID.randomUUID().toString().replace("-", ""));
		user.setU_image("/home/ubuntu/image/554c8d43389748f4a73bae0cad5cb4d7.png");
		user.setU_score(0);
//		user.setU_signature("这个新手不太冷！");
		System.out.println(user.getU_ID());
		User getuser = userDao.save(user);
		System.out.println(getuser);
		if (getuser!=null) {
			return true;
		}else {
			return false;
		}
	}

}
