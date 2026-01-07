package com.practice.test4;

public class BasketballSporter extends Sporter{
	public BasketballSporter(){}
	
	public BasketballSporter(String name,int age){
		super(name,age);
	}

	@Override
	public void study (){
		System.out.println("A Basketball sporter is learning to play Basketball.");
	}
}