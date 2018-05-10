package com.oldneighborhood.demo.service;

import com.oldneighborhood.demo.entity.User;

/**
 * @ClassName: RegisterService
 * @Description: 注册服务接口-普通用戶
 * @author user005
 * @date 2018年3月22日
 *
 */
public interface UserService {

	public boolean userSignup(User user);

}
