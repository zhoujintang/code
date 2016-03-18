package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * IM客户端
 * @author Administrator
 *
 */
public class IMClient implements Runnable {
	private String clientId;
	private int port=80;
	private String ip="localhost";
	Socket socket=null;
	DataInputStream inputStream=null;
	 DataOutputStream outputStream=null;
	 public IMClient(String clientId){
		 this.clientId=clientId;
	 }
    public void connection(){
    	try {
			socket=new Socket(ip, port);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void close(){
    	try {
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void sendMsg(String msg){
    	try {
    		 OutputStream os=socket.getOutputStream();
    	        outputStream=new DataOutputStream(os);
    	        outputStream.writeUTF(msg);
    	        InputStream	is=socket.getInputStream();
   			 inputStream=new DataInputStream(is);
       		 String read=inputStream.readUTF();
       		 System.out.println(read);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    public void msgHandle(){
    	try {
    		//connection();
    		// BufferedReader reader=new BufferedReader(new InputStreamReader(is));
    		while(true){
//    			  OutputStream os=socket.getOutputStream();
//    			  outputStream=new DataOutputStream(os);
//    			System.out.print("请输入: \t");    
//	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
//	                outputStream.writeUTF(str);
    			InputStream	is=socket.getInputStream();
    			if(is.available()>0){
    				 inputStream=new DataInputStream(is);
            		 String read=inputStream.readUTF();
            		System.out.println(read);
    			}
    			Thread.sleep(1000);
    		}
    		
    		// Thread.sleep(10000);
//    		 String lineString=reader.readLine();
//			 while(lineString!=null&&lineString.length()>0){
//				 lineString=reader.readLine();
//				 System.out.println(lineString);
//			 }
    		// outputStream.close();
    		// inputStream.close();
    		// outputStream.close();
    		// inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			try {
//				socket.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
			
		}
//    	try {
//    		 OutputStream outputStream= socket.getOutputStream();
//    		 outputStream.write("1231313".getBytes());
//    		 outputStream.flush();
//    		 InputStream is=socket.getInputStream();
//    		 BufferedReader inputstream=new BufferedReader(new InputStreamReader(is));
//    		 String line=null;
//    		 while((line=inputstream.readLine())!=null){
//    			 System.out.println(line);
//    		 }
//    		
//    		 outputStream.close();
//    		 is.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
    }
    
	public static void main(String[] args) {
   IMClient client=new IMClient(""+1);
   client.connection();
   Thread thread=new Thread(client);
   thread.start();
		while(true){
			try {
				// DataInputStream inputStream=new DataInputStream(System.in);
				 System.out.print("请输入: \t");    
	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
			     // String input=inputStream.readUTF();
			      client.sendMsg(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
	     
		}
	//client.sendMsg("第一次请求 哈哈哈");
	//    client.getMsg("");
//    for(int i=0;i<10;i++){
//    	Thread thread=new Thread(new IMClient(""+i));
//    	thread.start();
//    }
	}
	@Override
	public void run() {
//		connection();
//		while(true){
//			//sendMsg("clientId:"+clientId+" time:"+new Date().getTime());
//		}
		msgHandle();
		
	}

}
