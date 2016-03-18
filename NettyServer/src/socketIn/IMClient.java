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
 * IM�ͻ���
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
//		client.sendMsg("��һ������ ������");
//	    client.getMsg("");
		  System.out.println("�ͻ�������...");    
	        System.out.println("�����յ����������ַ�Ϊ \"OK\" ��ʱ��, �ͻ��˽���ֹ\n");   
	        while (true) {    
	            Socket socket = null;  
	            try {  
	                //����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
	                socket = new Socket("localhost", 1234);    
	                    
	                //��ȡ������������    
	                DataInputStream input = new DataInputStream(socket.getInputStream());    
	                //��������˷�������    
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
	                System.out.print("������: \t");    
	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
	                out.writeUTF(str);    
	                    
	                String ret = input.readUTF();     
	                System.out.println("�������˷��ع�������: " + ret);    
	                // ����յ� "OK" ��Ͽ�����    
	                if ("OK".equals(ret)) {    
	                    System.out.println("�ͻ��˽��ر�����");    
	                    Thread.sleep(500);    
	                    break;    
	                }    
	                  
	                out.close();  
	                input.close();  
	            } catch (Exception e) {  
	                System.out.println("�ͻ����쳣:" + e.getMessage());   
	            } finally {  
	                if (socket != null) {  
	                    try {  
	                        socket.close();  
	                    } catch (IOException e) {  
	                        socket = null;   
	                        System.out.println("�ͻ��� finally �쳣:" + e.getMessage());   
	                    }  
	                }  
	            }  
	        }  

	}

}
