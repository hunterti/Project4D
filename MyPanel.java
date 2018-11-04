package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextField;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	int mapMode = 0;
	Maze4D maze;
	Maze2D maze2D;
	int currentX, currentY, currentZ = 10, currentW = 5;
	int numMoves;
	int length = 18;
	int width = 3;
	player player;
	ArrayList<String> filenames;
	
	public MyPanel(int x, int y) {
		numMoves = 0;
		this.setSize(x,y);
		this.setLocation(0,0);
		this.setVisible(true);
		filenames = new ArrayList<String>();
		for(int i = 0; i < 2500; i++) {
			filenames.add("/Users/Acer/PycharmProjects/Hackathon2018/" + String.valueOf(i) + ".txt");
		}
		Collections.shuffle(filenames);
		maze = new Maze4D(filenames);
	
		player = new player(35,35);
		currentX = 35;
		currentY = 35;
		maze2D = maze.hypercube.get(5).cube.get(10);
		maze2D.tiles[currentX][currentY].traversed = true;
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1600, 1000);
		if(mapMode == 0) {
			for(int i = 0; i < 50; i++) {
				for(int j = 0; j < 50; j++) {
					Tile t = maze2D.tiles[i][j]; //DRAW THE WALLS
					drawCase(g, t.TileType, t);
					if(t.traversed && t.rValue > 0) {
						g.setColor(new Color(t.rValue,0,0));
						g.fillRect(Integer.parseInt(t.x) * length+4, Integer.parseInt(t.y) * length+3, length-4, length-4);
						t.rValue-=15;
					}else {
						t.traversed = false;
						t.rValue = 255;
					}
				}
			}
			g.setColor(Color.WHITE);
			g.fillOval(player.x * length + width + 2, player.y * length + width + 1, 12, 12);
			g.setColor(Color.DARK_GRAY);
			for(int x = 1; x < 50; x ++) {
				g.fillRect(x*length, length, length, width);
				g.fillRect(x*length, length * 50, length, width);
			}
			for(int y = 1; y < 50; y ++) {
				g.fillRect(length, y*length, width, length);
				g.fillRect(length*50, y*length, width, length);
			}
		}else if(mapMode == 1){
			for(int i = 0; i < 50; i++) {
				for(int j = 0; j < 50; j++) {
					Tile t = maze2D.tiles[i][j]; //DRAW HeatMap
					drawCase2(g, t.TileType, t);
				}
			}
		}else if(mapMode == 2) {
			for(int i = 0; i < 50; i++) {
				for(int j = 0; j < 50; j++) {
					Tile t = maze2D.tiles[i][j]; //DRAW HeatMap
					drawCase3(g, t.TileType, t);
				}
			}
		}else if(mapMode == 3) {
			for(int i = 0; i < 50; i++) {
				for(int j = 0; j < 50; j++) {
					Tile t=  maze2D.tiles[i][j];
					drawCase4(g, t.TileType, t);
				}
			}
		}
		int x1 = 1000,x2 = 1100,x3 = 1200,x4 = 1100;
		int y1 = 300,y2 = 400,y3 = 300,y4 = 200;
		for(int i = 0; i < 50; i++) {
			g.setColor(Color.DARK_GRAY);
			
			if(i == currentZ) {
				g.setColor(Color.ORANGE);
				g.drawLine(x1-2*i, y1-4*i, x2-2*i, y2-4*i);
				g.drawLine(x1-2*i, y1-4*i, x4-2*i, y4-4*i);
				g.drawLine(x3-2*i, y3-4*i, x4-2*i, y4-4*i);
				g.drawLine(x2-2*i, y2-4*i, x3-2*i, y3-4*i);
			}else {
				g.drawLine(x1-2*i, y1-4*i, x2-2*i, y2-4*i);
				g.drawLine(x1-2*i, y1-4*i, x4-2*i, y4-4*i);
				g.drawLine(x3-2*i, y3-4*i, x4-2*i, y4-4*i);
				g.drawLine(x2-2*i, y2-4*i, x3-2*i, y3-4*i);		
			}
			//DRAW THE FLOORS
		} 
		//DRAW THE W
		x1 = 1300;x3 = 1200;x2 = 1300;x4 = 1200;
		y1 = 300;y3 = 450;y2 = 500;y4 = 250;
		for(int i = 0; i < 20; i++) {
			if(i == currentW) {
				g.setColor(Color.ORANGE);
				g.drawLine(x1+6*i, y1-2*i, x2+6*i, y2-2*i);
				g.drawLine(x1+6*i, y1-2*i, x4+6*i, y4-2*i);
				g.drawLine(x3+6*i, y3-2*i, x4+6*i, y4-2*i);
				g.drawLine(x2+6*i, y2-2*i, x3+6*i, y3-2*i);
			}else {
				g.setColor(Color.DARK_GRAY);
				g.drawLine(x1+6*i, y1-2*i, x2+6*i, y2-2*i);
				g.drawLine(x1+6*i, y1-2*i, x4+6*i, y4-2*i);
				g.drawLine(x3+6*i, y3-2*i, x4+6*i, y4-2*i);
				g.drawLine(x2+6*i, y2-2*i, x3+6*i, y3-2*i);		
			}
		}
		boolean cantTraverseUp = maze2D.tiles[currentX][currentY].zPerms.equals("3") || maze2D.tiles[currentX][currentY].zPerms.equals("4"); ;
		boolean cantTraverseDown = maze2D.tiles[currentX][currentY].zPerms.equals("6") || maze2D.tiles[currentX][currentY].zPerms.equals("7");
		boolean cantDimensionHop1 = maze2D.tiles[currentX][currentY].zPerms.equals("0") || maze2D.tiles[currentX][currentY].wPerms.equals("5");
		boolean cantDimensionHop2 = maze2D.tiles[currentX][currentY].zPerms.equals("2") || maze2D.tiles[currentX][currentY].wPerms.equals("8");
		boolean canGoLeft = maze2D.tiles[currentX][currentY].canMoveFrom('a') && maze2D.tiles[currentX][currentY].canMoveTo(maze2D.tiles[currentX-1][currentY], 'a');
		boolean canGoRight = maze2D.tiles[currentX][currentY].canMoveFrom('d') && maze2D.tiles[currentX][currentY].canMoveTo(maze2D.tiles[currentX+1][currentY], 'd');
		boolean canGoUp = maze2D.tiles[currentX][currentY].canMoveFrom('w') && maze2D.tiles[currentX][currentY].canMoveTo(maze2D.tiles[currentX][currentY-1], 'w');
		boolean canGoDown = maze2D.tiles[currentX][currentY].canMoveFrom('s') && maze2D.tiles[currentX][currentY].canMoveTo(maze2D.tiles[currentX][currentY+1], 's');
		x1 = 1100; x2 = 1000; y1 = 600; y2 = 675;
		if(canGoUp) {
			g.setColor(Color.GREEN);
			g.fillRect(x1, y1, 25, 100);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1, y1, 25, 100);
		}
		if(canGoDown){
			g.setColor(Color.GREEN);
			g.fillRect(x1, y1 + 100, 25, 100);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1, y1 + 100, 25, 100);
		}
		if(canGoLeft) {
			g.setColor(Color.GREEN);
			g.fillRect(x1 - 100, y2, 100, 25);	
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1 - 100, y2, 100, 25);
		
		}if(canGoRight) {
			g.setColor(Color.GREEN);
			g.fillRect(x1+25, y2, 100, 25);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1+25, y2, 100, 25);	
		}
		x1 = x1 + 250; x2 = x2 + 250;
		if(!(cantTraverseUp)) {
			g.setColor(Color.GREEN);
			g.fillRect(x1, y1, 25, 100);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1, y1, 25, 100);
		}
		if(!(cantTraverseDown)){
			g.setColor(Color.GREEN);
			g.fillRect(x1, y1 + 100, 25, 100);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1, y1 + 100, 25, 100);
		}
		if(!(cantDimensionHop1)) {
			g.setColor(Color.GREEN);
			g.fillRect(x1 - 100, y2, 100, 25);	
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1 - 100, y2, 100, 25);
		
		}if(!(cantDimensionHop2)) {
			g.setColor(Color.GREEN);
			g.fillRect(x1 + 25, y2, 100, 25);
		}else {
			g.setColor(Color.RED);
			g.fillRect(x1 + 25, y2, 100, 25);	
		}
		g.setColor(Color.WHITE);
		String tempX = String.valueOf(currentX);
		String tempY = String.valueOf(currentY);
		String tempZ = String.valueOf(currentZ);
		String tempW = String.valueOf(currentW);
		String tempNum = String.valueOf(numMoves);
		char[] arr = new char[30];
		char[] arr2 = new char[30];
		int ticker = 0;
		arr[0] = 'X';ticker++; arr[1] = ':';ticker++; 
		for(int i = 0; i < tempX.length(); i++) { arr[ticker] = tempX.charAt(i); ticker++; }
		arr[ticker] = ' '; ticker++; arr[ticker] = 'Y'; ticker++; arr[ticker] = ':'; ticker++;
		for(int i = 0; i < tempY.length(); i++) { arr[ticker] = tempY.charAt(i); ticker++; }
		arr[ticker] = ' '; ticker++; arr[ticker] = 'Z'; ticker++; arr[ticker] = ':'; ticker++;
		for(int i = 0; i < tempZ.length(); i++) { arr[ticker] = tempZ.charAt(i); ticker++; }
		arr[ticker] = ' '; ticker++; arr[ticker] = 'W'; ticker++; arr[ticker] = ':'; ticker++;
		for(int i = 0; i < tempW.length(); i++) { arr[ticker] = tempW.charAt(i); ticker++; }
		g.drawChars(arr, 0, 30, 950, 490);
		ticker = 0;
		arr2[ticker] = 'N'; ticker++; arr2[ticker] = 'u'; ticker++; arr2[ticker] = 'm'; ticker++; arr2[ticker] = 'b'; ticker++; arr2[ticker] = 'e'; ticker++; arr2[ticker] = 'r'; ticker++; arr2[ticker] = ' '; ticker++;
		arr2[ticker] = 'O'; ticker++; arr2[ticker] = 'f'; ticker++; arr2[ticker] = ' '; ticker++; arr2[ticker] = 'M'; ticker++; arr2[ticker] = 'o'; ticker++; arr2[ticker] = 'v'; ticker++; arr2[ticker] = 'e'; ticker++;
		arr2[ticker] = 's'; ticker++; arr2[ticker] = ':'; ticker++;
		String s = "The mininmum number of moves possible is: " + maze.minDistance;
		for(int i = 0; i < tempNum.length(); i++) { arr2[ticker] = tempNum.charAt(i); ticker++; }
		g.drawChars(arr2, 0, 30, 950, 515);
		arr2 = new char[s.length()+1];
		for(int i = 0; i < s.length(); i++) { arr2[i] = s.charAt(i); }
		g.drawChars(arr2, 0, s.length()+1, 950, 535);
	}
	
	public void drawCase(Graphics g, String s, Tile t) {
		g.setColor(Color.DARK_GRAY);
		if(s.equals("1")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length +length, length+2, width); //width
		}if(s.equals("2")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length +length, length + 2, width); //width
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
		}if(s.equals("3")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
		}if(s.equals("4")) {
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1	
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length +length, length + 2, width); //width	
		}if(s.equals("5")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length+length, length+2, width); //width
		}if(s.equals("6")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
		}if(s.equals("7")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
		}if(s.equals("8")) {
			g.fillRect(Integer.parseInt(t.x)*length + length, Integer.parseInt(t.y)*length, width, length); //2
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length +length, length+2, width); //width	
		}if(s.equals("9")) {
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length +length, length+2, width); //width	
			g.fillRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
		}if(s.equals("0")) {
			
		}if(s.equals("11")) {
//			g.drawRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //4
		}if(s.equals("12")) {
//			g.drawRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, width, length); //2
		}if(s.equals("13")) {
//			g.drawRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //width
		}if(s.equals("14")) {
//			g.drawRect(Integer.parseInt(t.x)*length, Integer.parseInt(t.y)*length, length, width); //1
		}
		
	}
	void drawCase2(Graphics g, String s, Tile t) {
		if(t.x.equals("0") || t.y.equals("0")) {}
		else{
			boolean cantTraverseUp = t.zPerms.equals("3") || t.zPerms.equals("4");
			boolean cantGoDown = t.zPerms.equals("6") || t.zPerms.equals("7");
			if(!cantTraverseUp && !cantGoDown) {
				g.setColor(Color.GREEN);
			}else if(!cantTraverseUp && cantGoDown) {
				g.setColor(Color.ORANGE);
			}else if(cantTraverseUp && !cantGoDown) {
				g.setColor(Color.ORANGE);
			}else {
				g.setColor(Color.RED);
			}
			int x = Integer.parseInt(t.x);
			int y = Integer.parseInt(t.y);

			g.fillRect(x*length, y*length, length, length);
		}
	}
	void drawCase3(Graphics g, String s, Tile t) {
		if(t.x.equals("0") || t.y.equals("0")) {}
		else{
			boolean cantDimensionHop1 = t.zPerms.equals("0") || t.wPerms.equals("5");
			boolean cantDimensionHop2 = t.zPerms.equals("2") || t.wPerms.equals("8");
			if(!cantDimensionHop1 && !cantDimensionHop2) {
				g.setColor(Color.GREEN);
			}else if(!cantDimensionHop1 && cantDimensionHop2) {
				g.setColor(Color.ORANGE);
			}else if(cantDimensionHop1 && !cantDimensionHop2) {
				g.setColor(Color.ORANGE);
			}else {
				g.setColor(Color.RED);
			}
			int x = Integer.parseInt(t.x);
			int y = Integer.parseInt(t.y);

			g.fillRect(x*length, y*length, length, length);
		}
	}
	void drawCase4(Graphics g, String s, Tile t) {
		if(t.isGoodSpot){
			g.setColor(Color.CYAN);
		}else {
			g.setColor(Color.RED);
		}
		int x = Integer.parseInt(t.x);
		int y = Integer.parseInt(t.y);

		g.fillRect(x*length, y*length, length, length);
	}
}
