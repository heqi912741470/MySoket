package service;

import java.io.IOException;
import java.util.ArrayList;

public class ChatTools {
    //保存线程处理对象
    private static ArrayList<ServerThread> stList = new ArrayList();
    //不需要实例化类，因此构造器为私有
    private ChatTools(){}
    //将一个客户对应的线程处理对象加入到队列中
    public static void addClient(ServerThread st) throws IOException {
        stList.add(st);//将这个线程处理对象加入到队列中
        castMsg(st.getOwerUser(),"我上线了！当前在线人数："+stList.size());

    }
    public static void castMsg(UserInfo sender, String msg)throws IOException {
        msg = sender.getName()+"说："+msg;//加上说的对象
        for (int i=0;i<stList.size();i++){
            ServerThread st = stList.get(i);
            st.sendMsg2Me(msg);//发送消息给每一个客户机
        }
    }
}
