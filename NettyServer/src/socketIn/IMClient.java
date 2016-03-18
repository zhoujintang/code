package socketIn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * IM客户端
 * @author Administrator
 *
 */
public class IMClient {
	private int port=1234;
	private String ip="localhost";
	Socket socket=null;
    public void connection(){
    	try {
			socket=new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void sendMsg(String msg){
      try {
		OutputStream outputStream=socket.getOutputStream();
		outputStream.write(msg.getBytes());
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
    public void getMsg(String msg){
       try {
		InputStream inputStream=socket.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		String lineString=null;
		while((lineString=reader.readLine())!=null){
			System.out.println("server:"+lineString);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
	public static void main(String[] args) {
//		IMClient client=new IMClient();
//		client.connection();
//		client.sendMsg("第一次请求 哈哈哈");
//	    client.getMsg("");
		  System.out.println("客户端启动...");    
	        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");   
	        while (true) {    
	            Socket socket = null;  
	            try {  
	                //创建一个流套接字并将其连接到指定主机上的指定端口号  
	                socket = new Socket("localhost", 1234);    
	                    
	                //读取服务器端数据    
	                DataInputStream input = new DataInputStream(socket.getInputStream());    
	                //向服务器端发送数据    
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
	                System.out.print("请输入: \t");    
	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
	                out.writeUTF(str);    
	                    
	                String ret = input.readUTF();     
	                System.out.println("服务器端返回过来的是: " + ret);    
	                // 如接收到 "OK" 则断开连接    
	                if ("OK".equals(ret)) {    
	                    System.out.println("客户端将关闭连接");    
	                    Thread.sleep(500);    
	                    break;    
	                }    
	                  
	                out.close();  
	                input.close();  
	            } catch (Exception e) {  
	                System.out.println("客户端异常:" + e.getMessage());   
	            } finally {  
	                if (socket != null) {  
	                    try {  
	                        socket.close();  
	                    } catch (IOException e) {  
	                        socket = null;   
	                        System.out.println("客户端 finally 异常:" + e.getMessage());   
	                    }  
	                }  
	            }  
	        }  

	}

}
