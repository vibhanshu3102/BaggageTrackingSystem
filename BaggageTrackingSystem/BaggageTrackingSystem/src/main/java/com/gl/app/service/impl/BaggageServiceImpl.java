package com.gl.app.service.impl;

import java.sql.SQLException;
import java.util.List;
import com.gl.app.entity.Baggage;
import com.gl.app.service.BaggageService;
import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;

public class BaggageServiceImpl implements BaggageService {

	BaggageDAO baggageDAO = new BaggageDAOImpl();

	@Override
	public String getBaggageStatus(String claimTagId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to get baggage status
		String ans = baggageDAO.getBaggageStatus(claimTagId);
		return ans;
	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to update baggage status
		baggageDAO.updateBaggageStatus(claimTagId , status);
	}

	@Override
	public String getBaggageLocation(String claimTagId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to get baggage location
		String result = baggageDAO.getBaggageLocation(claimTagId);
		return result;
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to update baggage location
		baggageDAO.updateBaggageStatus(claimTagId , location);
	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// write the code to claim baggage
		baggageDAO.claimBaggage(claimTagId);

	}

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Baggage> list = baggageDAO.getAllCheckedInBaggage();
		return list;
	}
}
