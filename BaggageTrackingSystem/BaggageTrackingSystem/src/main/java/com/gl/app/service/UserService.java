package com.gl.app.service;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;

public interface UserService {
	public void registerNewUser(User user) throws SQLException, ClassNotFoundException;

	public void checkInBaggage(Baggage baggage) throws SQLException, ClassNotFoundException;

	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException;

}
