package com.gl.app.dao;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;

public interface UserDAO {
	public void registerNewUser(User user) throws SQLException, ClassNotFoundException;

	public void checkInBaggage(Baggage baggage) throws SQLException, ClassNotFoundException;

	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException;

}
