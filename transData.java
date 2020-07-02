import java.io.Serializable;
import java.util.*;

public class transData implements Serializable{
    /*
    client ---> server
    10 : login
    20 : register
    13 : room_choice
    50 : battle end


    server ---> client
    11 : login_answer
    12 : room_info
    80 : battle_start
    85 : port_send
    1000 : your turn notice


    client ---> server ---> client
    3 : board_info

    */


    private static final long serialVersionUID = 1L;

    int protocol=3;

    public transData(int protocol) {
        this.protocol = protocol;
    }
    public int get_protocol() {
        return protocol;
    }

    public void set_protocol(int p){
        this.protocol = p;
    }

    //protocol 1000 = your turn notice



    // board_info--------------------------
    // client ---> server ---> client
    // protocol = 3
    private int row=-1;
    private int line=-1;
    public int get_row() {
        return row;
    }
    public int get_line() {
        return line;
    }
    public void set_row(int row){
        this.row = row;
    }
    public void set_line(int line){
        this.line = line;
    }
    // --------------------------board_info


    // battle end---------------------------
    // client ---> server
    // protocol = 50
    private boolean battle_end;
    public boolean get_battle_end(){
        return battle_end;  // true when battle finished
    }
    public void set_battle_end(boolean battle_end){
        this.battle_end = battle_end;
    }
    //---------------------------battle end

    // room_info------------------------------
    // server ---> client
    // protocol = 12
    // room_info
    private Map<Integer,Integer> room_info = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> get_room_info(){
        return room_info;
    }
    public void set_room_info(Map<Integer, Integer> room_info){
        this.room_info = room_info;
    }
    // ------------------------------room_info

    // room_choice------------------------------
    // client ---> server
    // protocol = 13
    private int room_num;
    public int get_room_num(){
        return room_num;
    }
    public void set_room_num(int room_num){
        this.room_num = room_num;
    }
    // ------------------------------room_choice

    // port_send--------------------------
    // server ---> client
    // protocol = 85
    private int port_send;
    public int get_port(){
        return port_send;
    }
    public void set_port_send(int port_send){
        this.port_send = port_send;
    }
    // --------------------------port_send


    // battle_start--------------------------
    // server ---> client
    // protocol = 80
//    boolean first_turn = false;


    // --------------------------battle_start

    // login------------------------------
    // client ---> server
    // protocol = 10
    private String login_name="no set", login_pass="no set";
    public String get_login_name(){
        return login_name;
    }
    public String get_login_pass(){
        return login_pass;
    }
    public void set_login_name_pass(String login_name, String login_pass){
        this.login_name = login_name;
        this.login_pass = login_pass;
    }
    // ------------------------------login

    // login_answer--------------------------
    // server ---> client
    // protocol = 11
    private String login_answer="no set";
    public String get_login_answer(){
        return login_answer;
    }
    public void set_login_answer(String login_answer){
        this.login_answer = login_answer;
    }
    // --------------------------login_answer

    // register------------------------------
    // client ---> server
    // protocol = 20
    private String register_name="no set", register_pass="no set", secret_question="sogabe";
    public String get_register_name(){
        return register_name;
    }
    public String get_register_pass(){
        return register_pass;
    }
    public String get_register_secret_question(){
        return this.secret_question;
    }
    public void set_register_name_pass(String register_name, String register_pass, String secret_question){
        this.register_name = register_name;
        this.register_pass = register_pass;
        this.secret_question = secret_question;
    }
    // ------------------------------register

}
