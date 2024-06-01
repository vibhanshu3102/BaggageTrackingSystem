package com.gl.app.service;

import java.sql.SQLException;
import java.util.List;

import com.gl.app.entity.Baggage;

public interface BaggageService {
	public String getBaggageStatus(String claimTagId) throws SQLException, ClassNotFoundException;

	public void updateBaggageStatus(String claimTagId, String status) throws SQLException, ClassNotFoundException;

	public String getBaggageLocation(String claimTagId) throws SQLException, ClassNotFoundException;

	public void updateBaggageLocation(String claimTagId, String location) throws SQLException, ClassNotFoundException;

	public void claimBaggage(String claimTagId) throws SQLException, ClassNotFoundException;

	public List<Baggage> getAllCheckedInBaggage() throws SQLException, ClassNotFoundException;

}
