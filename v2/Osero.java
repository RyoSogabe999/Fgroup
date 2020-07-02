import java.io.*;
import java.util.*;

public class Osero{
	public static void main(String[] args){
		int r=10, l=10, p=0, flag=3;
	  Ban osero = new Ban();
		System.out.println("---Osero game start!!---");
		osero.initMap();

		while(flag==3){
			Scanner scan = new Scanner(System.in);
			System.out.println("you have " + p);
			while(r>7){
				System.out.println("Where do you put on?(row)");
				r = scan.nextInt();
			}
			while(l>7){
				System.out.println("Where do you put on?(len)");
				l = scan.nextInt();
			}

			osero.updateMap(r, l, p);
			osero.printMap(p);
			flag = osero.isGameFinish();
			p = (p+1) % 2;
			r = 10;
			l = 10;
		}
		System.out.println("game finished");

	}
}
