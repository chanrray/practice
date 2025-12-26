package com.practice.test3;
import java.util.Random;
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
				case "1" -> login(userList);
				case "2" -> singup(userList);
				case "3" -> resetPassword(userList);
				case "4" -> {System.out.println("Thanks for using,goodbye!");System.exit(0);}
				default -> System.out.println("Wrong Option!");
			}
		}	
	}
	
	private static void login(ArrayList<User> userList){
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<3;i++){
			System.out.println("Please enter your username:");
			String username = sc.next();
			if(!contains(userList,username)){
				System.out.println("User doesn't exist. Check the username or register a new one.\n");
				return;
			}
			int index = getIndex(userList,username);
			if(userList.get(index).getAttemptsNum()==3){
				System.out.println("Account locked!Please reset your password.\n");
				break;
			}
			System.out.println("Please enter your password:");
			String password = sc.next();
			while(true){
				String rightVerificationCode = getVerificationCode();
				System.out.println("Please enter this verification code:"+rightVerificationCode);
				String code = sc.next();
				if(!code.equalsIgnoreCase(rightVerificationCode)){
					System.out.println("Wrong verification code!");
					continue;
				}
				break;
			}
			if(password.equals(userList.get(index).getPassword())){
				userList.get(index).setAttemptsNum(0);
				System.out.println("Login successful!\n");
				break;
			}else{
				int attemps = userList.get(index).getAttemptsNum()+1;
				userList.get(index).setAttemptsNum(attemps);
				System.out.println("Incorrect username or password!");
				if(attemps == 3){
					System.out.println(
					"Account locked!Please reset your password.\n");
					break;
					}else{System.out.println("There are "+(3-attemps)+" chances left.");}
				}
		}
	}

	private static void singup(ArrayList<User> userList){
		String username,password,pinCode,phoneNumber;
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Please enter a new username:(8-16 characters,letter+digit(optional))");
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
			System.out.println("Please re-enter password:");
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
			System.out.println("Please enter phoneNumber:(+86 chinese format(11 digits))");
			phoneNumber = sc.next();
			if(!checkPhoneNumber(phoneNumber)){
				System.out.println("Phone number format incorrect!Please reinput.");
			}else{break;}
		}
		userList.add(new User(username,password,pinCode,phoneNumber));
		System.out.println("Registration successful!\n");
	}

	private static void resetPassword(ArrayList<User> userList){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username:");
		String username = sc.next();
		if(!contains(userList,username)){
			System.out.println("User doesn't exist. Check the username or register a new one.\n");
			return;
		}
		int index = getIndex(userList,username);
		System.out.println("Please enter your pinCode:");
		String pinCode = sc.next();
		System.out.println("Please enter your phoneNumber:");
		String phoneNumber = sc.next();
		if(!(pinCode.equals(userList.get(index).getPinCode()) && phoneNumber.equals(userList.get(index).getPhoneNumber()))){
			System.out.println("User information doesn't match.\n");
			return;
		}
		
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
		return getIndex(userList,username) >= 0;
	}
	
	public static int getIndex(ArrayList<User> userList,String username){
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equals(username)){
				return i;
			}
		}
		return -1;
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
	
	private static String getVerificationCode(){
		ArrayList<Character> list = new ArrayList<>();
		for (int i=0;i<26;i++){
			list.add((char)('a'+i));
			list.add((char)('A'+i));
		}
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<4;i++){
			char c = list.get(r.nextInt(list.size()));
			sb.append(c);
		}
		sb.append(r.nextInt(10));
		char[] arr = sb.toString().toCharArray();
		int randomIndex = r.nextInt(arr.length);
		char temp = arr[randomIndex];
		arr[randomIndex] = arr[arr.length-1];
		arr[arr.length-1] = temp;
		return new String(arr);
	}
}