package code;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements KeyListener{
	MyPanel panel;
	boolean gameOver;
	Clip music;
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}
	MyFrame(){
		panel = new MyPanel(1000,1000);
		this.addKeyListener(this);
		this.setSize(1550,980);
		try {
			music = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/Users/Acer/Downloads/Music.wav"));
			music.open(ais);
			music.loop(music.LOOP_CONTINUOUSLY);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panel);
		gameOver = false;
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(!gameOver) {
			char x = e.getKeyChar();
			int currentX = panel.currentX;
			int currentY = panel.currentY;
			int currentZ = panel.currentZ;
			int currentW = panel.currentW;
			if(x == 'w') {
				if(currentY <= 49) {
					boolean temp = panel.maze2D.tiles[currentX][currentY].canMoveFrom(x);
					boolean temp2 = panel.maze2D.tiles[currentX][currentY].canMoveTo(panel.maze2D.tiles[currentX][currentY-1], x);
					if(temp && temp2) {
						panel.maze2D.tiles[currentX][currentY].rValue = 255;
						panel.maze2D.tiles[currentX][currentY].traversed = true;
						panel.player.y = currentY-1;
						panel.currentY--;
						panel.numMoves++;
						panel.repaint();
					}
				}
			}
			if(x == 's') {
				if(currentY >= 1) {
					boolean temp = panel.maze2D.tiles[currentX][currentY].canMoveFrom(x);
					boolean temp2 = panel.maze2D.tiles[currentX][currentY].canMoveTo(panel.maze2D.tiles[currentX][currentY+1], x);
					if(temp && temp2) {
						panel.maze2D.tiles[currentX][currentY].rValue = 255;
						panel.maze2D.tiles[currentX][currentY].traversed = true;
						panel.player.y = currentY+1;
						panel.currentY++;
						panel.numMoves++;
						panel.repaint();
					}
				}
			}
			if(x == 'm') {
				if(panel.mapMode!=3) {
					panel.mapMode++;
				}else {
					panel.mapMode = 0;
				}
				panel.repaint();
			}
			if(x == 'a') {
				if(currentX >= 1) {
					boolean temp = panel.maze2D.tiles[currentX][currentY].canMoveFrom(x);
					boolean temp2 = panel.maze2D.tiles[currentX][currentY].canMoveTo(panel.maze2D.tiles[currentX-1][currentY], x);
					if(temp && temp2) {
						panel.maze2D.tiles[currentX][currentY].rValue = 255;
						panel.maze2D.tiles[currentX][currentY].traversed = true;
						panel.player.x = currentX-1;
						panel.currentX--;
						panel.numMoves++;
						panel.repaint();
					}
				}
			}
			if(x == 'd') {
				if(currentX <= 48) {
					boolean temp = panel.maze2D.tiles[currentX][currentY].canMoveFrom(x);
					boolean temp2 = panel.maze2D.tiles[currentX][currentY].canMoveTo(panel.maze2D.tiles[currentX+1][currentY], x);
					if(temp && temp2) {
						panel.maze2D.tiles[currentX][currentY].rValue = 255;
						panel.maze2D.tiles[currentX][currentY].traversed = true;
						panel.player.x = currentX+1;
						panel.currentX++;
						panel.numMoves++;
						panel.repaint();
					}
				}
			}
			if(x == 'i') {
				boolean cantTraverseUp = panel.maze2D.tiles[currentX][currentY].zPerms.equals("3") || panel.maze2D.tiles[currentX][currentY].zPerms.equals("4"); ;
				if(currentZ+1 < 50 && !(cantTraverseUp)) {
					panel.currentZ++;
					panel.maze2D = panel.maze.hypercube.get(currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}else if(currentZ+1 >= 50 && !(cantTraverseUp)){
					panel.currentZ = 0;
					panel.maze2D = panel.maze.hypercube.get(currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}
			}
			if(x == 'k') {
				boolean cantGoDown = panel.maze2D.tiles[currentX][currentY].zPerms.equals("6") || panel.maze2D.tiles[currentX][currentY].zPerms.equals("7");
				if(currentZ >= 1 && !(cantGoDown)) {
					panel.currentZ--;
					panel.maze2D = panel.maze.hypercube.get(currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}else if(currentZ == 0 && !(cantGoDown)){
					panel.currentZ = 49;
					panel.maze2D = panel.maze.hypercube.get(currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}
			}
			if(x == 'j') {
				boolean cantDimensionHop = panel.maze2D.tiles[currentX][currentY].zPerms.equals("0") || panel.maze2D.tiles[currentX][currentY].wPerms.equals("5");
				if(currentW >= 1 && !(cantDimensionHop)) {
					panel.currentW--;
					panel.maze2D = panel.maze.hypercube.get(panel.currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}else if(currentW == 0 && !(cantDimensionHop)) {
					panel.currentW = 19;
					panel.maze2D = panel.maze.hypercube.get(panel.currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}
			}
			if(x == 'l') {
				boolean cantDimensionHop = panel.maze2D.tiles[currentX][currentY].zPerms.equals("2") || panel.maze2D.tiles[currentX][currentY].wPerms.equals("8");
				if(currentW <= 18 && !(cantDimensionHop)) {
					panel.currentW++;
					panel.maze2D = panel.maze.hypercube.get(panel.currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}else if(currentW == 19 && !(cantDimensionHop)) {
					panel.currentW = 0;
					panel.maze2D = panel.maze.hypercube.get(panel.currentW).cube.get(panel.currentZ);
					panel.numMoves++;
					panel.maze2D.tiles[currentX][currentY].rValue = 255;
					panel.maze2D.tiles[currentX][currentY].traversed = true;
					panel.repaint();
				}
			}	
			if(x == 'z') {
				panel.currentW = 0;
				panel.currentX = 1;
				panel.currentZ = 1;
				panel.currentY = 1;
				panel.player.x = 1;
				panel.player.y = 1;
				panel.maze2D = panel.maze.hypercube.get(0).cube.get(0);
				panel.maze2D.tiles[currentX][currentY].rValue = 255;
				panel.maze2D.tiles[currentX][currentY].traversed = true;
				repaint();
			}
			if(panel.currentW == 0 && panel.currentX == 1 && panel.currentY == 1 && panel.currentZ == 0) {
				gameOver = true;
				keyTyped(e);
			}
		}else {
			JTextField won = new JTextField();
			won.setFont(new Font("SansSerif", Font.BOLD,20));
			won.setLocation(400,450);
			won.setText("YOU COMPLETED THE MAZE IN:" + panel.numMoves + " STEPS!!!");
			won.setSize(500,100);
			won.setHorizontalAlignment(JTextField.CENTER);
			this.getContentPane().add(won);
		}
	}

}
