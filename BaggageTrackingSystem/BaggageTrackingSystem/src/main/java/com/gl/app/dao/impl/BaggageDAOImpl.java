package com.gl.app.dao.impl;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaggageDAOImpl implements BaggageDAO {
	private Connection connection;
	private BaggageUtil baggageUtils;

	public BaggageDAOImpl() {

	}

	@Override
	public String getBaggageStatus(String claimTagId) throws SQLException, ClassNotFoundException {
		// write the code to get baggage status
		Connection connection2 = BaggageUtil.getConnection();
		String result = "no status to display";
		String query = "SELECT status FROM baggage WHERE claimid = ?";
		PreparedStatement statement = connection2.prepareStatement(query);
		statement.setString(1 , claimTagId);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			result = resultSet.getString("status");
		}
		resultSet.close();
		statement.close();
		connection2.close();
		return result;
	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException, ClassNotFoundException {
		// write the code to update baggage status
		Connection connection3 = BaggageUtil.getConnection();
		String query = "UPDATE baggage SET status = ? WHERE claimid = ?";
		PreparedStatement statement = connection3.prepareStatement(query);
		statement.setString(1,status);
		statement.setString(2,claimTagId);
		int res = statement.executeUpdate();
		if(res>0){
			System.out.println("<================== status updated successfully ====================>");
			statement.close();
			connection3.close();
		}
		else{
			throw new BaggageNotFoundException("<------------------ No Such Baggage Availaible -------------->");
		}
	}

	@Override
	public String getBaggageLocation(String claimTagId) throws SQLException, ClassNotFoundException {
		// write the code here
		Connection connection2 = BaggageUtil.getConnection();
		String result = "checked in area";
		String query = "SELECT location FROM baggage WHERE claimid = ?";
		PreparedStatement statement = connection2.prepareStatement(query);
		statement.setString(1 , claimTagId);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			result = resultSet.getString("location");
		}
		resultSet.close();
		statement.close();
		connection2.close();
		return result;
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException, ClassNotFoundException {
		// write the code here
		Connection connection3 = BaggageUtil.getConnection();
		String query = "UPDATE baggage SET location = ? WHERE claimid = ?";
		PreparedStatement statement = connection3.prepareStatement(query);
		statement.setString(1,location);
		statement.setString(2,claimTagId);
		int res = statement.executeUpdate();
		if(res>0){
			System.out.println("<================== location updated successfully ====================>");
			statement.close();
			connection3.close();
		}
		else {
			throw new BaggageNotFoundException("<-------------NO SUCH BAGGAGE FOUND--------------->");
		}
	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException, ClassNotFoundException {
		// write the code to claim baggage
		Connection connection1 = BaggageUtil.getConnection();
		String query = "DELETE FROM baggage WHERE claimid = ?";
		PreparedStatement statement = connection1.prepareStatement(query);
		statement.setString(1 , claimTagId);
		int res = statement.executeUpdate();
		if(res>0){
			System.out.println("Baggage claimed successfully");
			statement.close();
			connection1.close();
		}
		else {
			throw new BaggageNotFoundException("<----------- No Baggage Available ------------->");
		}
	}

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws SQLException, ClassNotFoundException {
		// write the code to get all checked-in baggage
		Connection connection1 = BaggageUtil.getConnection();
		List<Baggage> list = new ArrayList<>();
		String query = "SELECT * FROM baggage  WHERE status = 'checked in'";
		PreparedStatement statement = connection1.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			Baggage baggage = new Baggage(resultSet.getString("claimid"),
					resultSet.getString("location") , resultSet.getString("status"),
					resultSet.getString("userid"));
			list.add(baggage);

			//return  mappedList;

		}



//
//		List<String> mappedList = list.stream()
//				.map(baggage1 -> baggage1.status()+ " - " + baggage1.claimId())
//				.collect(Collectors.toList());



		resultSet.close();
		statement.close();
		connection1.close();
		return list;
	}
}
