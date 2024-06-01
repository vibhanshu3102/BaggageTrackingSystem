package com.gl.app.client;

import com.gl.app.exception.UserNotFoundException;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.UserServiceImpl;
import com.gl.app.service.BaggageService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.entity.Baggage;
import com.gl.app.util.BaggageUtil;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaggageTrackingApplication {
	private static final Pattern EMAIL_PATTERN = null;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		UserService userService = new UserServiceImpl();
		BaggageService baggageService = new BaggageServiceImpl();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Register User");
			System.out.println("2. Check-in Baggage");
			System.out.println("3. Get Baggage Info");
			System.out.println("4. Get Baggage Status");
			System.out.println("5. Get All Checked-in Baggage");
			System.out.println("6. Update Baggage Status");
			System.out.println("7. Update Baggage Location");
			System.out.println("8. Claim Baggage");
			System.out.println("9. Get Baggage Location");
			System.out.println("10. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				//user register
				// write the code to register user
				System.out.println("Enter a User Id: ");
				String userid = scanner.next();
				System.out.println("Enter First name: ");
				String firstname = scanner.next();
				System.out.println("Enter Last name: ");
				String lastname = scanner.next();
				System.out.println("Enter Email: ");
				String email = scanner.next();
				if(validateEmail(email) == false){
					System.out.println("please enter a valid email");
				}
				else{
					User user = new User(userid , firstname , lastname , email);
					userService.registerNewUser(user);
				}
				break;
			case 2:
				// Check-in Baggage
				// write the code to check in baggage
				try {
					System.out.println("Enter the user id: ");
					String uid = scanner.next();
					Baggage baggage = new Baggage(uid, "Checked in Area", "", uid);
					userService.checkInBaggage(baggage);
				}
				catch (UserNotFoundException u){
					System.out.println("Exception Caught...." + u);
				}
				break;
			case 3:
				// Get Baggage info
				// write the code to get baggage status
				System.out.println("Enter the claim id: ");
				String claimid = scanner.next();
				Baggage bag = userService.getBaggageInfo(claimid);
				System.out.println("Claim Id: " + bag.claimId());
				System.out.println("Location: " + bag.location());
				System.out.println("status  : " + bag.status());
				System.out.println("user ID : " + bag.userId());

				break;
			case 4:
				// Get Baggage Status
				try {
					System.out.println("enter Claim id: ");
					String cd = scanner.next();
					// write the code to get baggage status
					String value = baggageService.getBaggageStatus(cd);
					System.out.println("status: " + value);
				}
				catch (BaggageNotFoundException B){
					System.out.println("Exception Handled....");
				}
				break;
			case 5:
				// Get All Checked-in Baggage
				// write the code to get all checked-in baggage
				List<Baggage> list = baggageService.getAllCheckedInBaggage();
//				for(Baggage bags : list){
//					System.out.println("<====================================>");
//					System.out.println("Claim id: " + bags.claimId());
//					System.out.println("location: " + bags.location());
//					System.out.println("status  : " + bags.status());
//					System.out.println("user ID : " +bags.userId());
//					System.out.println("<=======================================>");

				List<String> list1 =  list.stream().map(b -> b.status() + "-" + b.claimId()).toList();
				System.out.println(list1);
				break;
			case 6:
				//update baggage status
				// Write your code to Update Baggage Status
				try {
					System.out.println("Enter your Claim ID: ");
					String string = scanner.next();
					System.out.println("Enter the updated status: ");
					String string1 = scanner.next();
					baggageService.updateBaggageStatus(string, string1);
				}
				catch (BaggageNotFoundException B){
					System.out.println("Exception Handled....");
				}
				break;
			case 7:
				// update Baggage Location
				// write the code to update baggage location
				try {
					System.out.println("Enter your Claim ID: ");
					String clai = scanner.next();
					System.out.println("Enter your Location");
					String loc = scanner.next();
					baggageService.updateBaggageStatus(clai, loc);
				}
				catch (BaggageNotFoundException B){
					System.out.println("Exception Handled....");
				}
				break;
			case 8:
				// Claim Baggage
				// write the code to claim baggage
				try {
					System.out.println("Enter the claim id: ");
					String claimd = scanner.next();
					baggageService.claimBaggage(claimd);
				}
				catch (BaggageNotFoundException B){
					System.out.println("Exception Handled....");
				}
				break;
			case 9:
				// Get Baggage Location
				// write the code to get baggage location
				try {
					System.out.println("Enter the Claim id: ");
					String cid = scanner.next();
					String answer = baggageService.getBaggageLocation(cid);
					System.out.println("location: " + answer);
				}
				catch (BaggageNotFoundException B){
					System.out.println("Exception Handled....");
				}
				break;

			case 10:
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 9.");
			}
		}
	}

	private static boolean validateEmail(String email) {

		if (email == null || email.isEmpty()) {
			return false;
		}

		int atIndex = email.indexOf('@');
		int dotIndex = email.lastIndexOf('.');

		// Ensure "@" appears before "."
		if (atIndex == -1 || dotIndex == -1 || atIndex > dotIndex) {
			return false;
		}

		// Ensure "@" and "." are not adjacent
		if (dotIndex - atIndex == 1) {
			return false;
		}

		// Ensure "." is not at the end of the email
		if (dotIndex == email.length() - 1) {
			return false;
		}

		return true;
	}
}

