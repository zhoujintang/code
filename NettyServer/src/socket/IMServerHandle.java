package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IMServerHandle extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	 public IMServerHandle(Socket socket){
		 try {
			 this.socket=socket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	 }
	 public void sendMsg(String msg){
		 try {
			OutputStream	 os=socket.getOutputStream();
			DataOutputStream outputStream=new DataOutputStream(os);
			outputStream.writeUTF(msg);
		//	outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 public void run() {
		 try {
			// BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//			 
//			 String lineString=reader.readLine();
//			 while(lineString!=null&&lineString.length()>0){
//				 lineString=reader.readLine();
//				 System.out.println(lineString);
//			 }
			 while(true){
				 InputStream	 is=socket.getInputStream();
				 OutputStream	 os=socket.getOutputStream();
				 in=new DataInputStream(is);
				 out=new DataOutputStream (os);
				String read= in.readUTF();
				//����Э��
				String[] param=read.split(" ");
				String openType=param[0];
				if("login".equalsIgnoreCase(openType)){
					String clientId=param[1];
					IMClientConnectionManager.regClientSocket(clientId, this);
				}else if("send".equalsIgnoreCase(openType)){
					String from=param[1];
					String to=param[2];
					String msg=param[3];
				IMServerHandle toHandle=IMClientConnectionManager.getClientSocket(to);
				toHandle.sendMsg("from:"+from+" msg:"+msg);
				}
				//System.out.println(read);
				out.writeUTF("ok");
			 }
			
			//DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			//out.writeUTF("���������յ����ݣ�"+read);
			 // �� writer �Կͻ��� socket ���һ�� HTML ����  
//             PrintWriter out = new PrintWriter(socket.getOutputStream(),  
//                     true);  
//             out.println("HTTP/1.0 200 OK");//����Ӧ����Ϣ,������Ӧ��  
//             out.println("Content-Type:text/html;charset=gbk");  
//             out.println();// ���� HTTP Э��, ���н�����ͷ��Ϣ  
//
//             out.println("<h1> Hello Http Server</h1>");  
//             out.println("���, ����һ�� Java HTTP ������ demo Ӧ��.<br>");  
//             out.println("�������·����: http://www.baidu.com<br>");  
//             out.println("�������ҳ�溬��ͼƬ:<img src='test.gif'><br>"  
//                     + "<a href='test.gif'>�ֶ������test.gifͼƬ�ļ�.</a>");  
//             out.println("<br>��������֧��jpg��ʽͼƬ��������ʾXX:"  
//                     + "<img src='test.jpg'><br><a href='test.jpg'>"  
//                     + "�ֶ������test.jpg������ת��һҳ�棬���ҷ��񷵻�Ϊ404����</a><br>");  
//             out  
//                     .println("<form method=post action='/path?qryParm=POST URL��ѯ����' > POST �� "  
//                             + "<input name=username value='�û�'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//             out  
//                     .println("<form method=get action='/path?qryParm=GET URL��ѯ����' > GET �� "  
//                             + "<input name=username value='�û�'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//
//             out  
//                     .println("<form method=post action='/path?qryParm=POST URL��ѯ����'"  
//                             + " enctype='multipart/form-data' >"  
//                             + "�ļ��ϴ�  <input type='file' name=file1 ><br>"  
//                             + "          "  
//                             + "<input type='file' name=file2 ><br>"  
//                             + "          "  
//                             + "<input name=username value='�û�'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//             out.println("<a href='/download'>����˴�ģ���ļ�����</a>");  
			//out.close();
			//in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		 try {
//	    		InputStream is=socket.getInputStream();
//	    		 BufferedReader inputstream=new BufferedReader(new InputStreamReader(is));
//	    		 String line=null;
//	    		 while((line=inputstream.readLine())!=null){
//	    			 System.out.println("client send:"+line);
//	    		 }
//	    		 OutputStream outputStream= socket.getOutputStream();
//	    		 outputStream.write("1231313".getBytes());
//	    		 outputStream.close();
//	    		 is.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	 }
	 public static void main(String[] args) {
		String cmd="login 23444  212e1323";
		String[] param=cmd.split(" ");
		for(String str:param){
			System.out.println(str);
		}
	}
	 
}
