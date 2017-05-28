import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        //服务器接口类
        ServerSocket sst=null;
        //套接口类
        Socket sc=null;
        InputStream is=null;
        OutputStream os=null;
        //dataInput为input的子类
        DataInputStream in=null;
        //printStream为output的子类
        PrintStream ps=null;
        try {
            sst =new ServerSocket(8000);//构造对象端口为8000
            sc=sst.accept();//建立套接口
            is=sc.getInputStream(); //获取套接口的输入流
            in=new DataInputStream(is);//构造数据的输入流datainput对象in
            os=sc.getOutputStream();//获取套接口的输出流
            ps=new PrintStream(os); //构造数据的输出流PrintStream对象os
            InetAddress Ip=sc.getInetAddress();//获取客户端的IP
            System.out.println("连接地址ip："+Ip);//显示ip
            int port;
            port=sc.getPort();
            System.out.println("客户端端口"+port);
            ps.println("Welcome");
            String str=in.readLine();//在in上读取一行
            while(!str.equals("quit")){
                System.out.println("客户说："+str);
                str=in.readLine();
            }
            System.out.println("客户离开");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭
            try {
                is.close();
                os.close();
                sc.close();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
