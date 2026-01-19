package com.practice.test5.ui;

import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;

public class GameJFrame extends JFrame{
	private int[][] imageNumberArr = new int[4][4];
	private int[][] winArr = new int[4][4];
	private int indexX=0;//Blank square position
	private int indexY=0;
	private String path = "/images/animal/animal1/";
	private int inversions = 0;
	private int blankFromBottom = -1;
	private int step = 0;
	private Random r = new Random();
	
	private JMenuItem replayItem = new JMenuItem("Replay");
	//JMenuItem reLoginItem = new JMenuItem("Relogin");
	private JMenuItem closeItem = new JMenuItem("Close game");
	private JMenuItem aboutItem = new JMenuItem("About me");
	private JMenuItem changeAnimal = new JMenuItem("Animals");
	private JMenuItem changeGirl = new JMenuItem("Girls");
	private JMenuItem changeSport = new JMenuItem("Sports");
	
	public GameJFrame(){
		initJFrame();
		initJMenuBar();
		initImageNumber();
		initImage();
		this.setVisible(true);
	}
	
	private void initJFrame(){
		this.setSize(603,680);
		this.setTitle("Puzzle Game V1.2");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setLayout(null);
		this.addKeyListener(new KeyboardController());
	}
	
	private void initJMenuBar(){
		JMenuBar jmb = new JMenuBar();
		JMenu fuctionMenu = new JMenu("Menu");
		JMenu changeImage = new JMenu("Change image");
		JMenu aboutMenu = new JMenu("About");
		jmb.add(fuctionMenu);
		jmb.add(aboutMenu);
		
		fuctionMenu.add(changeImage);
		fuctionMenu.add(replayItem);
		//fuctionMenu.add(reLoginItem);
		fuctionMenu.add(closeItem);
		
		changeImage.add(changeAnimal);
		changeImage.add(changeGirl);
		changeImage.add(changeSport);
		
		aboutMenu.add(aboutItem);
		
		MenuController menuController = new MenuController();
		changeAnimal.addActionListener(menuController);changeAnimal.setActionCommand("changeAnimal");
		changeGirl.addActionListener(menuController);changeGirl.setActionCommand("changeGirl");
		changeSport.addActionListener(menuController);changeSport.setActionCommand("changeSport");
		replayItem.addActionListener(menuController);replayItem.setActionCommand("replay");
		closeItem.addActionListener(menuController);closeItem.setActionCommand("close");
		aboutItem.addActionListener(menuController);aboutItem.setActionCommand("about");
		
		this.setJMenuBar(jmb);
	}
	
	private void initImageNumber(){
		int[] tempArr = new int[16];
		for (int i=0;i<tempArr.length;i++){
			tempArr[i]=i;
			winArr[i / 4][i % 4] = i+1;
		}
		winArr[3][3] = 0;
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
				temp1 = r.nextInt(15);
				temp2 = temp1+1;
			} while (temp1 == temp2 || tempArr[temp1] == 0 || tempArr[temp2] == 0);
			int temp = tempArr[temp1];
			tempArr[temp1] = tempArr[temp2];
			tempArr[temp2] = temp;
		}
		
		for (int i=0;i<tempArr.length;i++){
			if(tempArr[i]==0){
				indexY=i/4;
				indexX=i%4;
			}
			imageNumberArr[i / 4][i % 4] = tempArr[i];
		}
	}
	
	private void initImage(){
		this.getContentPane().removeAll();
		JLabel stepCount = new JLabel("Steps:" + step);
		stepCount.setBounds(40,30,100,20);
		this.getContentPane().add(stepCount);
		JLabel tips = new JLabel("Press A to preview image.");
		tips.setBounds(400,30,200,20);
		this.getContentPane().add(tips);
		if(chechWin()){
			JLabel winJLable = new JLabel(new ImageIcon(getClass().getResource("/images/win.png")));
			winJLable.setBounds(203,283,197,73);
			this.getContentPane().add(winJLable);
		}
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++){	
			JLabel jlb;
			if(imageNumberArr[y][x]!=0){jlb = new JLabel(new ImageIcon(getClass().getResource(path+imageNumberArr[y][x]+".jpg")));}
			else{jlb = new JLabel();}
			jlb.setBounds(105*x+83,105*y+134,105,105);
			jlb.setBorder(new BevelBorder(1));
			this.getContentPane().add(jlb);
			}
		}
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/background.png")));
		background.setBounds(40,40,508,560);
		this.getContentPane().add(background);
		this.getContentPane().repaint();
	}
	
	private class MenuController implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			switch (command){
				case "replay" -> {step = 0;initImageNumber();initImage();}
				case "close"  -> {System.exit(0);}
				case "about"  -> {
					JDialog jdl = new JDialog();
					JLabel jlb = new JLabel(new ImageIcon(getClass().getResource("/images/about.png")));
					jlb.setBounds(0,0,250,250);
					jdl.getContentPane().add(jlb);
					jdl.setSize(344,344);
					jdl.setAlwaysOnTop(true);
					jdl.setLocationRelativeTo(null);
					jdl.setModal(true);
					jdl.setVisible(true);
				}
				case "changeAnimal" -> {
					path = "/images/animal/animal"+(r.nextInt(3)+1)+"/";
					step = 0;initImageNumber();initImage();
				}
				case "changeGirl" -> {
					path = "/images/girl/girl"+(r.nextInt(3)+1)+"/";
					step = 0;initImageNumber();initImage();
				}
				case "changeSport" -> {
					path = "/images/sport/sport"+(r.nextInt(3)+1)+"/";
					step = 0;initImageNumber();initImage();
				}
			}	
		}
	}
	
	private class KeyboardController implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e){
		
		}
	
		@Override
		public void keyPressed(KeyEvent e){
			if(chechWin()){
				return;
			}
			if(e.getKeyCode()==65){
				showAllImage();
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
					imageNumberArr[indexY][indexX]=imageNumberArr[indexY][indexX-1];imageNumberArr[indexY][indexX-1]=0;indexX--;step++;initImage();
					}
				case 38 -> {
					if (indexY == 0){
						return;
					}
					imageNumberArr[indexY][indexX]=imageNumberArr[indexY-1][indexX];imageNumberArr[indexY-1][indexX]=0;indexY--;step++;initImage();
					}
				case 39 -> {
					if (indexX == 3){
						return;
					}
					imageNumberArr[indexY][indexX]=imageNumberArr[indexY][indexX+1];imageNumberArr[indexY][indexX+1]=0;indexX++;step++;initImage();
					}
				case 40 -> {
					if (indexY == 3){
						return;
					}
					imageNumberArr[indexY][indexX]=imageNumberArr[indexY+1][indexX];imageNumberArr[indexY+1][indexX]=0;indexY++;step++;initImage();
					}
				case 65 -> {
					initImage();
				}
			}
		}

		private void showAllImage() {
			getContentPane().removeAll();
			JLabel all = new JLabel(new ImageIcon(getClass().getResource(path+"all.jpg")));
			all.setBounds(83,134,420,420);
			getContentPane().add(all);
			JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/background.png")));
			background.setBounds(40,40,508,560);
			getContentPane().add(background);
			getContentPane().repaint();
		}
	}
	
//	public class MouseController implements MouseListener, MouseMotionListener {
		
//	}

	private boolean chechWin(){
		for (int y=0;y<imageNumberArr.length;y++){
			for(int x=0;x<imageNumberArr[y].length;x++){
				if (imageNumberArr[y][x] != winArr[y][x]){
					return false;
				}
			}
		}
		return true;
	}
}