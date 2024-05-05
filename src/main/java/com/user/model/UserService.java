package com.user.model;

import java.sql.SQLException;
import java.util.List;

public class UserService {

	private UserDAO_interface dao;

	public UserService() {
		dao = new UserJNDIDAO();
	}

	public UserVO addUser(UserVO user) throws SQLException {
		dao.insert(user);

		return user;
	}
	
	public List<UserVO> getAll(){
		return dao.getAll();
	}
	
}
