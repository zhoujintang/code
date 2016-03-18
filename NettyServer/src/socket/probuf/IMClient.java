package socket.probuf;

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
 * IM�ͻ���
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
    //��¼��ϵͳ
    public void login(){
    	try {
    		Chart.ReqLogin.Builder reqbuilder=Chart.ReqLogin.newBuilder();
    		reqbuilder.setStrUserid(""+10000);
    		reqbuilder.setInt32Oemid(1);
    		//������ת��Ϊ�ֽ�
        	byte[] b=reqbuilder.build().toByteArray();
        	OutputStream os=socket.getOutputStream();
        	os.write(b);
        	os.flush();
        	InputStream	is=socket.getInputStream();
        	 byte[] t=new byte[is.available()];
			 is.read(t);
			//���ֽ�ת��Ϊ�ö���
        	Chart.RspLogin rspLogin=Chart.RspLogin.parseFrom(t);
        	int code=rspLogin.getInt32Code();
        	String message=rspLogin.getStrMsg();
        	System.out.println(code+"  message"+message);
        	os.close();
        	inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
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
    
	public static void main(String[] args) {
   //IMClient client=new IMClient(""+1);
 	//client.connection();
 	//client.login();
//		while(true){
//			try {
//				// DataInputStream inputStream=new DataInputStream(System.in);
//				 System.out.print("������: \t");    
//	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
//			     // String input=inputStream.readUTF();
//			      client.sendMsg(str);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	     
//		}
	//client.sendMsg("��һ������ ������");
	//    client.getMsg("");
//    for(int i=0;i<10;i++){
//    	Thread thread=new Thread(new IMClient(""+i));
//    	thread.start();
//    }
 	 IMClient client=new IMClient(""+1);
 	   client.connection();
 	   client.login();
 	  // Thread thread=new Thread(client);
 	  // thread.start();
// 			while(true){
// 				try {
// 					// DataInputStream inputStream=new DataInputStream(System.in);
// 					 System.out.print("������: \t");    
// 		                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
// 				     // String input=inputStream.readUTF();
// 				      client.sendMsg(str);
// 				} catch (Exception e) {
// 					e.printStackTrace();
// 				}
// 		     
// 			}
	}
	@Override
	public void run() {
		connection();
		while(true){
			sendMsg("clientId:"+clientId+" time:"+new Date().getTime());
		}
		
	}

}
