import java.io.*;
import java.net.*;
import java.util.*;

public class Manage extends Thread{

    public void run_inside(){
        Scanner scan = new Scanner(System.in);
        System.out.print("type \"connect\" if you wanna manage");

        while(true){
            String tmp = scan.nextLine();
            if (tmp.equals("connect")){
                break;
            }
        }


        System.out.println("port 1 : " + Server.name_p1);
        System.out.println("port 2 : " + Server.name_p2);
        System.out.println("port 3 : " + Server.name_p3);
        System.out.println("port 4 : " + Server.name_p4);
        System.out.println("port 5 : " + Server.name_p5);
        System.out.println("port 6 : " + Server.name_p6);
        System.out.println("port 7 : " + Server.name_p7);
        System.out.println("port 8 : " + Server.name_p8);

        System.out.println("room 1 - 1 : " + Server.name_r1_1);
        System.out.println("room 1 - 2 : " + Server.name_r1_2);
        System.out.println("room 2 - 1 : " + Server.name_r2_1);
        System.out.println("room 2 - 2 : " + Server.name_r2_2);
        System.out.println("room 3 - 1 : " + Server.name_r3_1);
        System.out.println("room 3 - 2 : " + Server.name_r3_2);
        System.out.println("room 4 - 1 : " + Server.name_r4_1);
        System.out.println("room 4 - 2 : " + Server.name_r4_2);

    }

    public void run(){
        run_inside();
    }
}
