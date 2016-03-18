package socketIn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class IMServerHandle extends Thread {
	private Socket socket;
	 public IMServerHandle(Socket socket){
		 this.socket=socket;
	 }
	 public void run() {
		 try {    
             // ��ȡ�ͻ�������    
             DataInputStream input = new DataInputStream(socket.getInputStream());  
             String clientInputStr = input.readUTF();//����Ҫע��Ϳͻ����������д������Ӧ,������� EOFException  
             // ����ͻ�������    
             System.out.println("�ͻ��˷�����������:" + clientInputStr);    
 
             // ��ͻ��˻ظ���Ϣ    
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
             System.out.print("������:\t");    
             // ���ͼ��������һ��    
             String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
             out.writeUTF(s);    
               
             out.close();    
             input.close();    
         } catch (Exception e) {    
             System.out.println("������ run �쳣: " + e.getMessage());    
         } finally {    
             if (socket != null) {    
                 try {    
                     socket.close();    
                 } catch (Exception e) {    
                     socket = null;    
                     System.out.println("����� finally �쳣:" + e.getMessage());    
                 }    
             }    
         }   
     }    
}
