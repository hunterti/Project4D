package code;

import java.util.ArrayList;

public class Maze4D {
	public ArrayList<Maze3D> hypercube;
	public int[][][][] arr;
	int minDistance;
	public Maze4D(ArrayList<String> filenames) {
		minDistance = 0;
		while(minDistance == 0) {
			hypercube = new ArrayList<Maze3D>();

		for(int i = 0; i < 20; i++) {
			System.out.println((i+1)*50);
			Maze3D temp = new Maze3D(filenames.subList(i*50, (i + 1)*50));
			for(int j = 0; j < temp.cube.size(); j++) {
				Maze2D temp2D = temp.cube.get(j);
				Tile[][] temp2DArr = temp2D.tiles;
				for(int k = 0; k < 50; k++) {
					for(int y = 0; y < 50; y++) {
						temp2DArr[k][y].w = i;
					}
				}
			}
			hypercube.add(temp);
		}
		findMinDistance();
		}
		filterGoodValues();

	}
	public void filterGoodValues() {
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 50; y++) {
				for(int z = 0; z < 50; z++) {
					for(int w = 0; w < 20; w++) {
						if(arr[x][y][z][w] == minDistance) {
							hypercube.get(w).cube.get(z).tiles[x][y].isGoodSpot = true;
						}
					}
				}
			}
		}
	}
	
	public void findMinDistance() {
		arr = new int[50][50][50][20];
		arr[35][35][10][5] = 0;
		ArrayList<Tile> toDo = new ArrayList<Tile>();
		toDo.add(hypercube.get(5).cube.get(10).tiles[35][35]);
		boolean solutionNotFound = true;
		while(!(toDo.isEmpty())) {
//			System.out.println("Ran the while loop");
			Tile t = toDo.remove(0);
			int x = Integer.parseInt(t.x);
			int y = Integer.parseInt(t.y);
			int w = t.w;
			int z = t.z;
			if(x == 1 && y == 1 && z == 0 && w == 0) {
				solutionNotFound = false;
				System.out.println("THE MINIMUM NUMBER OF MOVES WAS" + arr[1][1][0][0]);
			}
			if(y+1 < 50) {
				Tile upwardY = hypercube.get(w).cube.get(z).tiles[x][y-1];
				if(t.canMoveFrom('w') && t.canMoveTo(upwardY, 'w') && arr[x][y-1][z][w] == 0) { // Can increment y upward
					arr[x][y-1][z][w] = arr[x][y][z][w] + 1;
					toDo.add(upwardY);
				}
			}
			if(y-1 >= 0) {
				Tile downwardY = hypercube.get(w).cube.get(z).tiles[x][y+1];
				if(t.canMoveFrom('s') && t.canMoveTo(downwardY, 's') && arr[x][y+1][z][w] == 0) {
					arr[x][y+1][z][w] = arr[x][y][z][w] + 1;
					toDo.add(downwardY);
				}
			}
			if(x-1 >= 0) {
				Tile rightX = hypercube.get(w).cube.get(z).tiles[x+1][y];
				if(t.canMoveFrom('d') && t.canMoveTo(rightX, 'd') && arr[x+1][y][z][w] == 0) {
					arr[x+1][y][z][w] = arr[x][y][z][w] + 1;
					toDo.add(rightX);
				}
			}
			if(x+1 < 50) {
				Tile leftX = hypercube.get(w).cube.get(z).tiles[x-1][y];
				if(t.canMoveFrom('a') && t.canMoveTo(leftX, 'a') && arr[x-1][y][z][w] == 0) {
					arr[x-1][y][z][w] = arr[x][y][z][w] + 1;
					toDo.add(leftX);
				}
			}
			boolean cantTraverseUp = t.zPerms.equals("3") || t.zPerms.equals("4"); ;
			boolean cantGoDown = t.zPerms.equals("6") || t.zPerms.equals("7");
			boolean cantDimensionHop1 = t.zPerms.equals("0") || t.wPerms.equals("5");
			boolean cantDimensionHop2 = t.zPerms.equals("2") || t.wPerms.equals("8");
			if(z+1 < 50 && !(cantTraverseUp) && arr[x][y][z+1][w] == 0) { // Up z
				arr[x][y][z+1][w] = arr[x][y][z][w] + 1;
				toDo.add(hypercube.get(w).cube.get(z+1).tiles[x][y]);
			}
			if(z-1 >= 0 && !(cantGoDown) && arr[x][y][z-1][w] == 0) {
				arr[x][y][z-1][w] = arr[x][y][z][w] + 1;
				toDo.add(hypercube.get(w).cube.get(z-1).tiles[x][y]);				
			}
			if(w-1 >= 0 && !(cantDimensionHop1) && arr[x][y][z][w-1] == 0) {
				arr[x][y][z][w-1] = arr[x][y][z][w] + 1;
				toDo.add(hypercube.get(w-1).cube.get(z).tiles[x][y]);
			}
			if(w+1 < 20 && !(cantDimensionHop2) && arr[x][y][z][w+1] == 0) {
				arr[x][y][z][w+1] = arr[x][y][z][w] + 1;
				toDo.add(hypercube.get(w+1).cube.get(z).tiles[x][y]);
			}
		}
		boolean backToStart = false;
		toDo.add(hypercube.get(0).cube.get(0).tiles[1][1]);
		while(!(toDo.isEmpty()) && !backToStart) {
//			System.out.println("Ran the while loop");
			Tile t = toDo.remove(0);
			int x = Integer.parseInt(t.x);
			int y = Integer.parseInt(t.y);
			int w = t.w;
			int z = t.z;
			int finalDepth = arr[1][1][0][0];
			if(x == 35 && y == 35 && z == 10 && w == 5) {
				backToStart = true;
				System.out.println("BACK TO START" + arr[1][1][0][0]);
			}
			if(y+1 < 50) {
				Tile upwardY = hypercube.get(w).cube.get(z).tiles[x][y-1];
				if(t.canMoveFrom('w') && t.canMoveTo(upwardY, 'w') && arr[x][y-1][z][w] != finalDepth) { // Can increment y upward
					arr[x][y-1][z][w] = finalDepth;
					toDo.add(upwardY);
				}
			}
			if(y-1 >= 0) {
				Tile downwardY = hypercube.get(w).cube.get(z).tiles[x][y+1];
				if(t.canMoveFrom('s') && t.canMoveTo(downwardY, 's') && arr[x][y+1][z][w] != finalDepth) {
					arr[x][y+1][z][w] = arr[x][y][z][w] - 1;
					toDo.add(downwardY);
				}
			}
			if(x-1 >= 0) {
				Tile rightX = hypercube.get(w).cube.get(z).tiles[x+1][y];
				if(t.canMoveFrom('d') && t.canMoveTo(rightX, 'd') && arr[x+1][y][z][w] != finalDepth) {
					arr[x+1][y][z][w] = arr[x][y][z][w] - 1;
					toDo.add(rightX);
				}
			}
			if(x+1 < 50) {
				Tile leftX = hypercube.get(w).cube.get(z).tiles[x-1][y];
				if(t.canMoveFrom('a') && t.canMoveTo(leftX, 'a') && arr[x-1][y][z][w] != finalDepth) {
					arr[x-1][y][z][w] = finalDepth;
					toDo.add(leftX);
				}
			}
			boolean cantTraverseUp = t.zPerms.equals("3") || t.zPerms.equals("4"); ;
			boolean cantGoDown = t.zPerms.equals("6") || t.zPerms.equals("7");
			boolean cantDimensionHop1 = t.zPerms.equals("0") || t.wPerms.equals("5");
			boolean cantDimensionHop2 = t.zPerms.equals("2") || t.wPerms.equals("8");
			if(z+1 < 50 && !(cantTraverseUp) && arr[x][y][z+1][w] != finalDepth) { // Up z
				arr[x][y][z+1][w] = finalDepth;
				toDo.add(hypercube.get(w).cube.get(z+1).tiles[x][y]);
			}
			if(z-1 >= 0 && !(cantGoDown) && arr[x][y][z-1][w] != finalDepth) {
				arr[x][y][z-1][w] = finalDepth;
				toDo.add(hypercube.get(w).cube.get(z-1).tiles[x][y]);				
			}
			if(w-1 >= 0 && !(cantDimensionHop1) && arr[x][y][z][w-1] != finalDepth) {
				arr[x][y][z][w-1] = finalDepth;
				toDo.add(hypercube.get(w-1).cube.get(z).tiles[x][y]);
			}
			if(w+1 < 20 && !(cantDimensionHop2) && arr[x][y][z][w+1] != finalDepth) {
				arr[x][y][z][w+1] = finalDepth;
				toDo.add(hypercube.get(w+1).cube.get(z).tiles[x][y]);
			}
		}
		minDistance = arr[1][1][0][0];
		
	}
	
}
