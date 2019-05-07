package service;

import service.ChatTools;
import service.DaoTools;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket client;//线程中的处理对象
    private OutputStream ous;//输出流对象
    private UserInfo user;//用户信息对象

    public ServerThread(Socket client){
        this.client = client;
    }
    public UserInfo getOwerUser(){
        return this.user;
    }
    public void run(){
        try {
            processSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //在显示屏中打印信息，例如 用户名 密码
    public void sendMsg2Me(String msg) throws IOException {
        msg+="\r\n";
        ous.write(msg.getBytes());
        ous.flush();
    }
    public void processSocket() throws IOException {
        InputStream ins = client.getInputStream();
        ous = client.getOutputStream();
        BufferedReader brd = new BufferedReader(new InputStreamReader(ins));

        sendMsg2Me("欢迎你来聊天，请输入你的用户名：");
        String userName = brd.readLine();
        sendMsg2Me("请输入密码：");
        String pwd = brd.readLine();
        user = new UserInfo();
        user.setName(userName);
        user.setPassword(pwd);
        //调用数据库，验证用户信息是否存在
        boolean loginState = DaoTools.checkLogin(user);
        if(loginState){
            //如果不存在这个账号则关闭
            this.closeMe();
            return;
        }
        ChatTools.addClient(this);//认证成功，把这个用户加入服务器队列
        String input =  brd.readLine();
        while (!input.equals("bye")){
            System.out.println("服务器读到的是："+input);
            ChatTools.castMsg(this.user,input);
            input = brd.readLine();
        }
        ChatTools.castMsg(this.user,"bye");
        ChatTools.castMsg(this.user,"你已下线");
        this.closeMe();
    }
    //关闭肚囊谦客户机与服务器的连接
    public void closeMe() throws IOException{
        client.close();
    }
}
