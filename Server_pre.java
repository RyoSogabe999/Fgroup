import java.io.*;


public class Server_pre {

    // room init
//    transData room_obj = new transData(12);

    public static void login(transData obj){
        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        String ans = null;

        try{
            //読み込みファイルのインスタンス生成
            //ファイル名を指定する
            fi = new FileInputStream("login.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);



            //読み込み行
            String line;

            //列名を管理する為の配列
            String[] arr = null;

            //1行ずつ読み込みを行う
            //1行目から検索して名前が一致したらパスを確認
            //一致したら "true" 違えば "false password" を返す
            int i = 0;
            while ((line = br.readLine()) != null){
                if (i == 0){
//                    System.out.println("line 1");
                }else{
                    arr = line.split(",");
//                    System.out.println(arr[0]);

                    if(arr[0].equals(obj.get_login_name())){
                        if (arr[1].equals(obj.get_login_pass())){
                            ans = "login succeed";
                            break;

                        }else{
                            ans = "login failed : false password";
                            break;
                        }
                    }
                }
                i++;
            }
            if(ans==null){
                ans = "login failed : this name does not exist";
            }
            // 送信用インスタンス作成
//            Login_answer login_answer = new Login_answer(ans);
            transData login_ans = new transData(11);
            login_ans.set_login_answer(ans);
            //send
//            ObjectOutputStream os = new ObjectOutputStream (socket_2.getOutputStream ());
//            os.writeObject (login_answer);
            System.out.println(login_ans.get_login_answer());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void register(transData obj){
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        FileInputStream fi_2 = null;
        InputStreamReader is_2 = null;
        BufferedReader br_2 = null;
        try {

            //同じ名前のユーザがいないか判定
            boolean ok_flag = true;
            fi = new FileInputStream("login.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);
            String line;
            String[] arr = null;
            int i = 0;
            while ((line = br.readLine()) != null){
                if (i == 0){
//                    System.out.println("line 1");
                }else{
                    arr = line.split(",");
//                    System.out.println(arr[0]);

                    if(arr[0].equals(obj.get_register_name())){
                        ok_flag = false;
                    }
                }
                i++;
            }


            if(ok_flag==true){
                FileWriter f = new FileWriter("login.csv", true);
                PrintWriter p = new PrintWriter(new BufferedWriter(f));

                p.print(obj.get_register_name());
                p.print(",");
                p.print(obj.get_register_pass());
                p.println();

                p.close();


                //  record
                FileWriter f_2 = new FileWriter("record.csv", true);
                PrintWriter p_2 = new PrintWriter(new BufferedWriter(f_2));

                p_2.print(obj.get_register_name());
                p_2.print(",0,0,0,0");

                p_2.println();

                p_2.close();

                System.out.println("register succeed");
            }else {

                System.out.println("Register failed : this name is already used");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void get_record(transData obj){
        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

//        String name = "yanki-";
//        String pass = "majimannji";

        // answe to client
        String ans = null;

        try{
            //読み込みファイルのインスタンス生成
            //ファイル名を指定する
            fi = new FileInputStream("record.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);



            //読み込み行
            String line;

            //列名を管理する為の配列
            String[] arr = null;

            //1行ずつ読み込みを行う
            //1行目から検索して名前が一致したらパスを確認
            //一致したら "true" 違えば "false password" を返す
            int i = 0;
            while ((line = br.readLine()) != null){
                if (i == 0){
//                    System.out.println("line 1");
                }else{
                    arr = line.split(",");
//                    System.out.println(arr[0]);

                    if(arr[0].equals(obj.get_login_name())){
                        if (arr[1].equals(obj.get_login_pass())){
                            ans = "login succeed";
                            break;

                        }else{
                            ans = "login failed : false password";
                            break;
                        }
                    }
                }
                i++;
            }
            if(ans==null){
                ans = "login failed : this name does not exist";
            }
            // 送信用インスタンス作成
            transData login_answer = new transData(11);
            login_answer.set_login_answer(ans);

            //send
//            ObjectOutputStream os = new ObjectOutputStream (socket_2.getOutputStream ());
//            os.writeObject (login_answer);
            System.out.println(login_answer.get_login_answer());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        System.out.println("main start--------------------");




        // 新規登録
        transData obj = new transData(20);
        obj.set_register_name_pass("usr_8", "pass_8");
        register(obj);


        // ログイン認証
        transData usr = new transData(10);
        usr.set_login_name_pass("usr_1", "pass_1");
        login(usr);



        System.out.println("--------------------main close");
    }
}
