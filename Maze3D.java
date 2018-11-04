package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze3D {
	ArrayList<Maze2D> cube;
	Maze3D(List<String> list){
		cube = new ArrayList<Maze2D>();
		for(int i = 0; i < 50; i++) {
			try {
				Maze2D temp = new Maze2D(list.get(i));
				Tile[][] tiles = temp.tiles;
				for(int j = 0; j < 50; j++) {
					for(int k = 0; k < 50; k++) {
						tiles[j][k].z = i;
					}
				}
				cube.add(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
