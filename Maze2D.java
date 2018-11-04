package code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze2D {
	public Tile[][] tiles;
	public Maze2D(String filename) throws IOException {
			tiles = new Tile[51][51];
			Scanner scan = new Scanner(new File(filename));
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				if(s.length() != 0) {
					String[] k = s.split(",");
					Tile t = new Tile(k);
					int x = Integer.parseInt(k[0]);
					int y = Integer.parseInt(k[1]);
					tiles[x][y] = t;
				}else {
			}
		}
	}
}
