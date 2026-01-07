package com.practice.test4;

public class BasketballCoach extends Coach{
	public BasketballCoach(){}
	
	public BasketballCoach(String name,int age){
		super(name,age);
	}

	@Override
	public void teach (){
		System.out.println("A Basketball sporter is teaching how to play Basketball.");
	}
}