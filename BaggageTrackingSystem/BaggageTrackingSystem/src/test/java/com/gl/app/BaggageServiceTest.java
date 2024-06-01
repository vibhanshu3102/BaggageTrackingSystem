package com.gl.app;

import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;
import com.gl.app.util.BaggageUtil;
import org.junit.jupiter.api.Test;

import com.gl.app.entity.Baggage;
import com.gl.app.service.BaggageService;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

public class BaggageServiceTest {

	// write the code for the intialization of the object
	BaggageDAO baggageDAO = new BaggageDAOImpl();
	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testGetBaggageStatus() throws SQLException, ClassNotFoundException {
		// Write your code here
//		BaggageDAO baggageDAO = new BaggageDAOImpl();
		String expected1 =
				"checked in";
		String actual = baggageDAO.getBaggageStatus(String.valueOf(3100));
		assertEquals(expected1 , actual);

	}
}

























