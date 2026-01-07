package com.practice.test4;

public class PingPongSporter extends Sporter implements English{
	public PingPongSporter(){}
	
	public PingPongSporter(String name,int age){
		super(name,age);
	}

	@Override
	public void study (){
		System.out.println("A Ping-Pong sporter is learning to play table tennis.");
	}
	
	@Override
	public void speakEnglish (){
		System.out.println("A Ping-Pong sporter is speaking English.");
	}
}