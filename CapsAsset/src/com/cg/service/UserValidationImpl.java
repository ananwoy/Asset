package com.cg.service;

import com.cg.Dao.UserValidationDao;
import com.cg.bean.User_Master;

public class UserValidationImpl implements UserValidation{

	@Override
	public User_Master validateUser(String usern, String userp) {
		UserValidationDao uvd=new UserValidationDao();
		return uvd.validateUser(usern, userp);
	}

}
