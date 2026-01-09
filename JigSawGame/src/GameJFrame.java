package com.practice.test5.ui;

import java.util.Random;
import javax.swing.*;

public class GameJFrame extends JFrame{
	int[][] imageNumberArr = new int[4][4];
	
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
		}
		Random r = new Random();
		for (int i=0;i<tempArr.length;i++){
			int index = r.nextInt(tempArr.length);
			int temp = tempArr[index];
			tempArr[index] = tempArr[i];
			tempArr[i] = temp;
		}
		for (int i=0;i<tempArr.length;i++){
			imageNumberArr[i / 4][i % 4] = tempArr[i];
		}
	}
	
	private void initImage(){
	for (int y=0;y<4;y++){
		for (int x=0;x<4;x++){
		JLabel jlb = new JLabel(new ImageIcon("./images/animal/animal1/"+imageNumberArr[y][x]+".jpg"));
		jlb.setBounds(105*x,105*y,105,105);
		this.getContentPane().add(jlb);
			}
		}
	}
}