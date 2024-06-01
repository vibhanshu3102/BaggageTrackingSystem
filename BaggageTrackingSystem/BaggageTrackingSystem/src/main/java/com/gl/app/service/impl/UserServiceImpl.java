package com.gl.app.service.impl;

import java.sql.SQLException;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.service.UserService;
import com.gl.app.dao.UserDAO;
import com.gl.app.dao.impl.UserDAOImpl;

public class UserServiceImpl implements UserService {
	UserDAO userDAO = new UserDAOImpl();

	@Override
	public void registerNewUser(User user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to register user
		userDAO.registerNewUser(user);

	}

	@Override
	public void checkInBaggage(Baggage baggage) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to check-in baggage
		userDAO.checkInBaggage(baggage);
	}

	@Override
	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to get baggage info
		Baggage baggage = userDAO.getBaggageInfo(claimTagId);
		return baggage;
	}

}
