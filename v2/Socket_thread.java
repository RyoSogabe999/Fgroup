import java.io.*;
import java.net.*;
import java.util.*;

public class Socket_thread extends Thread{
    private boolean running = true;

    private int port, num, room_num=-1;

    private String username;

    public Socket_thread(int port,int num){
        this.port = port;
        this.num = num;
    }

    public int get_num(){
        return num;
    }
    public int get_port(){
        return port;
    }
    public int get_room_num(){
        return room_num;
    }
    public String get_username(){
        return username;
    }




    public void run_inside(){
        System.out.println("thread [ " + String.valueOf(num) + " ] is running");

        ServerSocket ss = null;
        Socket s = null;

        try{
            ss = new ServerSocket(port);
            s = ss.accept();
            System.out.println("thread [ " + String.valueOf(num) + " ] socket accept");

            //  入出力ストリーム
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            transData data =(transData)ois.readObject();
            if(data instanceof transData) {
//                System.out.println(data.get_protocol());
                int protocol_1 = data.get_protocol();

                // check data is for login?
                if (protocol_1==10){
                    // login
                    if (Server.login(data, os)){
                        // 認証成功
                        // ルーム情報送信
                        transData room_obj = new transData(12);
                        room_obj.set_room_info(Server.room_info);
                        os.writeObject(room_obj);

                        System.out.println("thread [ " + String.valueOf(num) + " ] : room info sent");
//                        os.flush();

                        // ユーザネーム設定
                        this.username = data.get_login_name();

                    }else{
                        // 失敗

                    }
                }else{
                    // ログイン用のデータでは無いエラー

                }
            }else{

            }


            //選択ルーム受信
            transData data_room_num =(transData)ois.readObject();
            if (data_room_num.get_protocol()==13){
                int num = data_room_num.get_room_num();
                if(Server.room_info.get(num)==0){
                    // CPU 対戦
                    Server.room_info.put(num,1);
                    this.room_num = num;
                    this.running = false;

                    int port_send=0;
                    if(num==1){
                        port_send = 10011;
                    }else if(num==2){
                        port_send = 10021;
                    }else if(num==3){
                        port_send = 10031;
                    }else if(num==4){
                        port_send = 10041;
                    }
                    transData port_send_obj = new transData(85);
                    port_send_obj.set_port_send(port_send);
                    os.writeObject(port_send_obj);

                }else if(Server.room_info.get(num)==1){
                    // 対局スタート
                    Server.room_info.put(num,2);
                    this.room_num = num;
                    this.running = false;

                    int port_send=0;
                    if(num==1){
                        port_send = 10012;
                    }else if(num==2){
                        port_send = 10022;
                    }else if(num==3){
                        port_send = 10032;
                    }else if(num==4){
                        port_send = 10042;
                    }
                    transData port_send_obj = new transData(85);
                    port_send_obj.set_port_send(port_send);
                    os.writeObject(port_send_obj);
//                    os.flush();

                }else{
                    //入れないルームを選択したエラー
                    //

                }
            }


        }catch(Exception e) {
            e.printStackTrace();

        }finally {
            try{
                if (s!=null){
                    s.close();
                }
                if (ss!=null){
                    ss.close();
                }
                System.out.println("thread [ " + String.valueOf(num) + " ] : socket close");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("thread [ " + String.valueOf(num) + " ] is finished");
        }
    }


    public void run() {
//        while (running){
//            run_inside();
//        }
        run_inside();


    }
}
