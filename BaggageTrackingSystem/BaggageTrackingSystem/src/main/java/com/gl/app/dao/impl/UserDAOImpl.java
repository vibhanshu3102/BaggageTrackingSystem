package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gl.app.dao.UserDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.exception.UserNotFoundException;
import com.gl.app.util.BaggageUtil;

public class UserDAOImpl implements UserDAO {
	BaggageUtil baggageUtil = new BaggageUtil();

	@Override
	public void registerNewUser(User user) throws SQLException, ClassNotFoundException {
		// write the code to register user
		Connection connection = BaggageUtil.getConnection();
		String query = "INSERT INTO users (userid , firstname , lastname , email) VALUES (? , ? , ? , ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1 , user.userId());
		statement.setString(2, user.firstName());
		statement.setString(3, user.lastName());
		statement.setString(4, user.email());
		int res = statement.executeUpdate();
		if(res > 0){
			System.out.println("User Registered Successfully");
		}
		statement.close();
		connection.close();
	}

	public void checkInBaggage(Baggage baggage) throws SQLException, ClassNotFoundException {
		// write the code to check-in baggage
		Connection connection = BaggageUtil.getConnection();
			String query = "INSERT INTO baggage (claimid , location , status , userid) VALUES (? , 'checked in area' , 'checked in' , ?)";
			String usercheck = "SELECT userid FROM baggage WHERE userid = ?";
			PreparedStatement statement2 = connection.prepareStatement(usercheck);
			statement2.setString(1, baggage.userId());
			ResultSet resultSet = statement2.executeQuery();
			if(resultSet.wasNull()==false){
				PreparedStatement statement = connection.prepareStatement(query);
				int count = baggageUtil.generateUniqueId("userid", "baggage", 0);
				String value = baggage.userId() + count;

				statement.setString(1, value);
				statement.setString(2, baggage.userId());

				int res = statement.executeUpdate();
				if (res > 0) {
					System.out.println("======================  user Checked in Successfully  =======================");
					statement.close();
					connection.close();
				}
			}
			else {
				throw new UserNotFoundException("Check the user id");
			}

	}

	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException {
		// write the code to get baggage info

		Connection connection = BaggageUtil.getConnection();

		String query = "SELECT * FROM baggage WHERE claimid = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,claimTagId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			Baggage baggage = new Baggage(resultSet.getString("claimid") , resultSet.getString("location"),
					resultSet.getString("status") , resultSet.getString("userid"));
				return baggage;
		}
		resultSet.close();
		statement.close();
		connection.close();
		return null;


	}

}
