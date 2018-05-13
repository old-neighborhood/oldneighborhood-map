package com.oldneighborhood.demo.service;

import com.oldneighborhood.demo.entity.Saler;

/**
 * @ClassName: SalerService
 * @Description: 商户注册
 * @author user005
 * @date 2018年3月23日
 *
 */
public interface SalerService {

	public boolean salerSignup(Saler saler);
	
	public String salerReset(String email, String password);

}
