package com.practice.test2;
import java.util.Random;
public class Role{
	private String name;
	private int blood;
	private String attackMove;
	
	String[] attackSmallMovings={"擒龙功","打狗棒法","排云掌","龙爪手","火焰刀","沾花指","多罗叶指","无相劫指"};
	String[] attackBigMovings={"降龙十八掌","少林七十二绝技"};
	
	public Role(){}
	public Role(String name,int blood){
		this.name = name;
		this.blood=blood;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getBlood(){
		return blood;
	}
	public void setBlood(int blood){
		this.blood = blood;
	}
	
	public void attack(Role role){
		Random r = new Random();
		int hurt=r.nextInt(20)+1;
		if(hurt>=15){
			attackMove=attackBigMovings[r.nextInt(attackBigMovings.length)];
		}else {attackMove=attackSmallMovings[r.nextInt(attackSmallMovings.length)];}
		int remainBlood=role.getBlood()-hurt;
		remainBlood=remainBlood<0?0:remainBlood;
		role.setBlood(remainBlood);
		System.out.printf(this.getName()+"使出了【%s】攻击"+role.getName()+"造成了%d点伤害，"+role.getName()+"还剩下%d点血量%n",attackMove,hurt,remainBlood);
	}
	
}