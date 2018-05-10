package com.oldneighborhood.demo.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldneighborhood.demo.dao.SalerDao;
import com.oldneighborhood.demo.entity.Saler;
import com.oldneighborhood.demo.service.SalerService;

@Service
public class SalerServiceImpl implements SalerService{
	@Autowired
	private SalerDao salerDao;
	
	@Override
	@Transactional
	public boolean salerSignup(Saler saler) {
		LegalCheckImpl lci = new LegalCheckImpl();
		if (!lci.isSalerLegal(saler)) {
			System.out.println("注册字符不合法");
			return false;
		}
		saler.setS_ID(UUID.randomUUID().toString().replace("-", ""));
		saler.setS_image("/home/ubuntu/image/554c8d43389748f4a73bae0cad5cb4d7.png");
		saler.setS_score(0);
//		saler.setS_signature("这个新手不太冷！");
		Saler getsaler = salerDao.saveAndFlush(saler);
		System.out.println(getsaler);
		if (getsaler!=null) {
			return true;
		}else {
			return false;
		}
	}
}
