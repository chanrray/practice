package com.practice.test5.ui;

import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;

public class GameJFrame extends JFrame implements KeyListener{
	int[][] imageNumberArr = new int[4][4];
	int[][] winArr = new int[4][4];
	int indexX=0;//Blank square position
	int indexY=0;
	String path = "./images/animal/animal1/";
	int inversions = 0;
	int blankFromBottom = -1;
	
	public GameJFrame(){
		initJFrame();
		initJFrameBar();
		initImageNumber();
		initImage();
		this.setVisible(true);
	}
	
	private void initJFrame(){
		this.setSize(603,680);
		this.setTitle("Puzzle Game V1.0");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setLayout(null);
		this.addKeyListener(this);
	}
	
	private void initJFrameBar(){
		JMenuBar jmb = new JMenuBar();
		JMenu fuctionMenu = new JMenu("Menu");
		JMenu aboutMenu = new JMenu("About");
		
		JMenuItem replayItem = new JMenuItem("Replay");
		JMenuItem reLoginItem = new JMenuItem("Relogin");
		JMenuItem closeItem = new JMenuItem("Close game");
		
		JMenuItem aboutItem = new JMenuItem("About me");
		
		jmb.add(fuctionMenu);
		jmb.add(aboutMenu);
		
		fuctionMenu.add(replayItem);
		fuctionMenu.add(reLoginItem);
		fuctionMenu.add(closeItem);
		
		aboutMenu.add(aboutItem);
		
		this.setJMenuBar(jmb);
	}
	
	private void initImageNumber(){
		int[] tempArr = new int[16];
		for (int i=0;i<tempArr.length;i++){
			tempArr[i]=i;
			winArr[i / 4][i % 4] = i+1;
		}
		winArr[3][3] = 0;
		Random r = new Random();
		for (int i=0;i<tempArr.length;i++){
			int index = r.nextInt(tempArr.length);
			int temp = tempArr[index];
			tempArr[index] = tempArr[i];
			tempArr[i] = temp;
		}
		
		for (int i = 0; i < tempArr.length; i++) {
			if (tempArr[i] == 0) {
				blankFromBottom = 4-(i/4);
			} else {
				for (int j = i + 1; j < tempArr.length; j++) {
					if (tempArr[j] != 0 && tempArr[i] > tempArr[j]) {
                    inversions++;
					}
				}
			}
		}
		
		if ((blankFromBottom % 2)==(inversions % 2)){//Unsolvable situation
			int temp1,temp2;
			do {
				temp1 = r.nextInt(16);
				temp2 = r.nextInt(16);
			} while (temp1 == temp2 || tempArr[temp1] == 0 || tempArr[temp2] == 0);
			int temp = tempArr[temp1];
			tempArr[temp1] = tempArr[temp2];
			tempArr[temp2] = temp;
		}
		
		for (int i=0;i<tempArr.length;i++){
			if(tempArr[i]==0){
				indexY=i/4;
				indexX=i%4;
			}else{
				imageNumberArr[i / 4][i % 4] = tempArr[i];
			}
		}

	}
	
	private void initImage(){
		this.getContentPane().removeAll();
		if(chechWin()){
			JLabel winJLable = new JLabel(new ImageIcon("./images/win.png"));
			winJLable.setBounds(203,283,197,73);
			this.getContentPane().add(winJLable);
		}
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++){
			JLabel jlb = new JLabel(new ImageIcon(path+imageNumberArr[y][x]+".jpg"));
			jlb.setBounds(105*x+83,105*y+134,105,105);
			jlb.setBorder(new BevelBorder(1));
			this.getContentPane().add(jlb);
			}
		}
		JLabel background = new JLabel(new ImageIcon("./images/background.png"));
		background.setBounds(40,40,508,560);
		this.getContentPane().add(background);
		this.getContentPane().repaint();
	}
	
	public boolean chechWin(){
		for (int y=0;y<imageNumberArr.length;y++){
			for(int x=0;x<imageNumberArr[y].length;x++){
				if (imageNumberArr[y][x] != winArr[y][x]){
					return false;
				}
			}
		}
		return true;
	}
	
 	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if(chechWin()){
			return;
		}
		if(e.getKeyCode()==65){
			this.getContentPane().removeAll();
			JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
			all.setBounds(83,134,420,420);
			this.getContentPane().add(all);
			JLabel background = new JLabel(new ImageIcon("./images/background.png"));
			background.setBounds(40,40,508,560);
			this.getContentPane().add(background);
			this.getContentPane().repaint();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		if(chechWin()){
			return;
		}
		switch (e.getKeyCode()){//left:37 up:38 right:39 down:40
			case 37 -> {
				if (indexX == 0){
					return;
				}
				imageNumberArr[indexY][indexX]=imageNumberArr[indexY][indexX-1];imageNumberArr[indexY][indexX-1]=0;indexX--;initImage();
				}
			case 38 -> {
				if (indexY == 0){
					return;
				}
				imageNumberArr[indexY][indexX]=imageNumberArr[indexY-1][indexX];imageNumberArr[indexY-1][indexX]=0;indexY--;initImage();
				}
			case 39 -> {
				if (indexX == 3){
					return;
				}
				imageNumberArr[indexY][indexX]=imageNumberArr[indexY][indexX+1];imageNumberArr[indexY][indexX+1]=0;indexX++;initImage();
				}
			case 40 -> {
				if (indexY == 3){
					return;
				}
				imageNumberArr[indexY][indexX]=imageNumberArr[indexY+1][indexX];imageNumberArr[indexY+1][indexX]=0;indexY++;initImage();
				}
			case 65 -> {
				initImage();
			}
		}
	}
}