package com.practice.test3;
public class User{
	private String username;
	private String password;
	private String pinCode;
	private String phoneNumber;
	
	public User(){}
	public User(String username,String password,String pinCode,String phoneNumber){
		this.username = username;
		this.password = password;
		this.pinCode = pinCode;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPinCode(){
		return pinCode;
	}
	public void setPinCode(String pinCode){
		this.pinCode = pinCode;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
}