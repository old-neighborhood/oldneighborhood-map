package com.oldneighborhood.demo.service.impl;

import com.oldneighborhood.demo.entity.Saler;
import com.oldneighborhood.demo.entity.User;

public class LegalCheckImpl {

	public boolean isUserLegal(User user) {
		if (validateName(user.getU_name()) && validatePassword(user.getU_password())
				&& validateEmail(user.getU_email())) {
			return true;
		}
		return false;
	}

	public boolean isSalerLegal(Saler saler) {
		if (validateName(saler.getS_name()) && validatePassword(saler.getS_password())
				&& validateEmail(saler.getS_email())) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: validateName
	 * @Description: 验证name
	 */
	public boolean validateName(String name) {
		// 只含有汉字、数字、字母、下划线，下划线位置不限
		String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
		if (name.length() >= 5 && name.length() <= 100) {
			if (name.matches(regex)) {
				return true;
			}
		}
		System.out.println("非法用户名！");
		return false;
	}

	/**
	 * @Title: validateEmail
	 * @Description: 验证邮箱
	 */
	public boolean validateEmail(String email) {
		String regex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
//		String regex = "^[/w-]+(/.[/w-]+)*@[/w-]+(/.[/w-]+)+$";
		if (email.matches(regex)) {
			return true;
		}
		System.out.println("非法邮箱！");
		return false;
	}
	
	/**
	 * @Title: validatePassword
	 * @Description: 验证密码
	 */
	public boolean validatePassword(String password) {
		String regex = "^[0-9a-zA-Z_]{1,}$";
		if (password.length() >= 5 && password.length() <= 100) {
			if (password.matches(regex)) {
				return true;
			}
		}
		System.out.println("非法密码！");
		return false;
	}
	
	/**
	 * @Title: validateTele
	 * @Description: 验证手机号码，11位
	 */
	public boolean validateTele(String tele) {
		String regex = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
		if (tele.matches(regex)) {
			return true;
		}
		System.out.println("非法手机号码！");
		return false;
	}

}
