package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * IM服务
 * @author Administrator
 *
 */
public class IMServer {
	private int port=80;
	ServerSocket serverSocket=null;
    public void start(){
    	try {
    	    serverSocket=new ServerSocket(port);
    		while(true){
    			Socket client=serverSocket.accept();
    			System.out.println("有客户端连接进来.....");
    			//启动线程
    			new IMServerHandle(client).start();
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    public void close(){
    	try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		IMServer server=new IMServer();
		server.start();

	}

}
