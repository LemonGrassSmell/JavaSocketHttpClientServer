/**
 * Created by root on 5/27/17.
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        //套接口类
        Socket sc=null;
        InputStream is=null;
        OutputStream os=null;
        //dataInput为input的子类
        DataInputStream in=null;
        //printStream为output的子类
        PrintStream ps=null;
        String str=null;
        try {
            //创建客户端接口
            sc=new Socket("localhost",8000);
            System.out.println("come to server..");
            is=sc.getInputStream();
            os=sc.getOutputStream();
            in=new DataInputStream(is);
            ps=new PrintStream(os);
            str=in.readLine();
            System.out.println("server 说"+str);
            byte bt[]=new byte[20];
            System.in.read(bt);
            String msg=new String (bt,0);
            msg=msg.trim();
            while(!msg.equals("quit")){
                ps.println(msg);
                System.in.read(bt);
                msg=new String(bt,0);
                msg=msg.trim();
            }
            //传输信息到服务端
            ps.println(msg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
