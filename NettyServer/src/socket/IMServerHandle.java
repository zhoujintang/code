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
				//解析协议
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
			//out.writeUTF("服务器接收到数据："+read);
			 // 用 writer 对客户端 socket 输出一段 HTML 代码  
//             PrintWriter out = new PrintWriter(socket.getOutputStream(),  
//                     true);  
//             out.println("HTTP/1.0 200 OK");//返回应答消息,并结束应答  
//             out.println("Content-Type:text/html;charset=gbk");  
//             out.println();// 根据 HTTP 协议, 空行将结束头信息  
//
//             out.println("<h1> Hello Http Server</h1>");  
//             out.println("你好, 这是一个 Java HTTP 服务器 demo 应用.<br>");  
//             out.println("您请求的路径是: http://www.baidu.com<br>");  
//             out.println("你请求的页面含有图片:<img src='test.gif'><br>"  
//                     + "<a href='test.gif'>手动点击打开test.gif图片文件.</a>");  
//             out.println("<br>服务器不支持jpg格式图片，所以显示XX:"  
//                     + "<img src='test.jpg'><br><a href='test.jpg'>"  
//                     + "手动点击打开test.jpg，会跳转另一页面，并且服务返回为404错误</a><br>");  
//             out  
//                     .println("<form method=post action='/path?qryParm=POST URL查询参数' > POST 表单 "  
//                             + "<input name=username value='用户'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//             out  
//                     .println("<form method=get action='/path?qryParm=GET URL查询参数' > GET 表单 "  
//                             + "<input name=username value='用户'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//
//             out  
//                     .println("<form method=post action='/path?qryParm=POST URL查询参数'"  
//                             + " enctype='multipart/form-data' >"  
//                             + "文件上传  <input type='file' name=file1 ><br>"  
//                             + "          "  
//                             + "<input type='file' name=file2 ><br>"  
//                             + "          "  
//                             + "<input name=username value='用户'> "  
//                             + "<input name=submit type=submit value=submit></form>");  
//             out.println("<a href='/download'>点击此处模拟文件下载</a>");  
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
