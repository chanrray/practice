package com.practice.test4;

public class Test{
	public static void main(String[] args){
		PingPongSporter pps = new PingPongSporter("Liu Shiwen",23);
		System.out.println(pps.getName() + "," + pps.getAge());
		pps.study();
		pps.speakEnglish();
	}
}