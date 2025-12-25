package com.practice.test3;
import java.util.Scanner;
import java.util.ArrayList;
public class App{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ArrayList<User> userList = new ArrayList<>();
		while (true){
			System.out.println("--------Welecome to Student Management System--------");
			System.out.println("1.Login\n2.Singup\n3.Forgot Password\n4.Exit\nPlease Input Your Option Number");
			String choose = sc.next(); 
			switch(choose){
				//case "1" -> login(userList);
				case "2" -> singup(userList);
				//case "3" -> resetPassword(userList);
				case "4" -> {System.out.println("Thanks for using,goodbye!");System.exit(0);}
				default -> System.out.println("Wrong Option!");
			}
		}	
	}
	
	private static void login(ArrayList<User> userList){
		
	}

	private static void singup(ArrayList<User> userList){
		String username,password,pinCode,phoneNumber;
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Please enter a new username:(8-16 characters,letter+digit)");
			username = sc.next();
			if(!checkUsername(username)){
				System.out.println("Username format incorrect!");
				continue;
			}
			if(contains(userList,username)){
				System.out.println("Username exist!Please reinput.");
			}else{break;}
		}
		while (true){//password format no check.Take a break~
			System.out.println("Please enter password:");
			password = sc.next();
			System.out.println("Please re-enter password:(+86 chinese format)");
			String againPassword = sc.next();
			if(!password.equals(againPassword)){
				System.out.println("Password does not match!Please reinput.");
			}else{break;}
		}
		while (true){//actually,security question
			System.out.println("Please enter password reset pin:(8 digits)");
			pinCode = sc.next();
			if(!checkPinCode(pinCode)){
				System.out.println("pinCode format incorrect!Please reinput.");
			}else{break;}
		}
		while (true){
			System.out.println("Please enter phoneNumber:");
			phoneNumber = sc.next();
			if(!checkPhoneNumber(phoneNumber)){
				System.out.println("Phone number format incorrect!Please reinput.");
			}else{break;}
		}
		userList.add(new User(username,password,pinCode,phoneNumber));
		System.out.println("Registration successful!");
	}

	private static void resetPassword(ArrayList<User> userList){
		
	}
	
	private static boolean checkUsername(String username){
		int len = username.length();
		if (len < 8 || len > 15){
			return false;
		}
		for (int i=0;i <len;i++){
			char c = username.charAt(i);
			if(!((c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9'))){
				return false;}
		}
		boolean letter = false;
		for (int i=0;i <len;i++){
			char c = username.charAt(i);
			if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
				letter = true;break;}
		}
		return letter;
	}
	
	private static boolean contains(ArrayList<User> userList,String username){
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equals(username)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkPinCode(String pinCode){
		if (pinCode.length() != 8){
			return false;
		}
		for (int i=0;i < pinCode.length();i++){
			char c = pinCode.charAt(i);
			if(!(c>='0'&&c<='9')){
				return false;}
		}
		return true;
	}

	private static boolean checkPhoneNumber(String phoneNumber){
		if (phoneNumber.length() != 11){
			return false;
		}
		if(!phoneNumber.startsWith("1")){
			return false;
		}
		for (int i=0;i < phoneNumber.length();i++){
			char c = phoneNumber.charAt(i);
			if(!(c>='0'&&c<='9')){
				return false;}
		}
		return true;
	}

}