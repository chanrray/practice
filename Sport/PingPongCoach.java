package com.practice.test4;

public class PingPongCoach extends Coach implements English{
	public PingPongCoach(){}
	
	public PingPongCoach(String name,int age){
		super(name,age);
	}

	@Override
	public void teach (){
		System.out.println("A Ping-Pong coach is teaching how to play table tennis.");
	}
	
	@Override
	public void speakEnglish (){
		System.out.println("A Ping-Pong coach is speaking English.");
	}
}