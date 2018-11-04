package code;

public class Tile {
	public String x,y,TileType,zPerms,wPerms;
	public boolean traversed;
	public int rValue , w , z;
	public boolean isGoodSpot;
	Tile(String[] arr){
		x = arr[0];
		y = arr[1];
		isGoodSpot = false;
		traversed = false;
		rValue = 255;
		TileType = arr[2];
		zPerms = arr[3];
		wPerms = arr[4];
	}
	boolean canMoveFrom(char key) {
		String s = TileType;
		if(key == 'w') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return true;
			}if(s.equals("6")) { return false;
			}if(s.equals("7")) { return false;
			}if(s.equals("8")) { return true;
			}if(s.equals("9")) { return true;
			}if(s.equals("0")) { return true;				
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return false;
			}
		}
		if(key == 's') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return true;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return true;
			}if(s.equals("7")) { return true;
			}if(s.equals("8")) { return false;
			}if(s.equals("9")) { return false;
			}if(s.equals("0")) { return true;
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return false;
			}if(s.equals("14")) { return true;
			}
		}if(key == 'a') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return true;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return false;
			}if(s.equals("7")) { return true;
			}if(s.equals("8")) { return true;
			}if(s.equals("9")) { return false;
			}if(s.equals("0")) { return true;
			}if(s.equals("11")) { return false;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return true;
			}
		}
		if(key == 'd') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return true;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return true;
			}if(s.equals("7")) { return false;
			}if(s.equals("8")) { return false;
			}if(s.equals("9")) { return true;
			}if(s.equals("0")) { return true;	
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return false;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return true;
			}
		}
		return false;
	}
	boolean canMoveTo(Tile t, char key) {
		String s = t.TileType;
		if(key == 'w') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return true;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return true;
			}if(s.equals("7")) { return true;
			}if(s.equals("8")) { return false;
			}if(s.equals("9")) { return false;
			}if(s.equals("0")) { return true;
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return false;
			}if(s.equals("14")) { return true;
			}
		}
		if(key == 's') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return true;
			}if(s.equals("6")) { return false;
			}if(s.equals("7")) { return false;
			}if(s.equals("8")) { return true;
			}if(s.equals("9")) { return true;
			}if(s.equals("0")) { return true;				
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return false;
			}
		}if(key == 'a') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return true;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return false;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return true;
			}if(s.equals("7")) { return false;
			}if(s.equals("8")) { return false;
			}if(s.equals("9")) { return true;
			}if(s.equals("0")) { return true;	
			}if(s.equals("11")) { return true;
			}if(s.equals("12")) { return false;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return true;
			}
		}
		if(key == 'd') {
			if(s.equals("1")) { return false;
			}if(s.equals("2")) { return false;
			}if(s.equals("3")) { return false;
			}if(s.equals("4")) { return true;
			}if(s.equals("5")) { return false;
			}if(s.equals("6")) { return false;
			}if(s.equals("7")) { return true;
			}if(s.equals("8")) { return true;
			}if(s.equals("9")) { return false;
			}if(s.equals("0")) { return true;
			}if(s.equals("11")) { return false;
			}if(s.equals("12")) { return true;
			}if(s.equals("13")) { return true;
			}if(s.equals("14")) { return true;
			}
		}
		return false;
	}
}
