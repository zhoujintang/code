package socket;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IMClientConnectionManager {
	private static Map<String, IMServerHandle> clients=new ConcurrentHashMap<String, IMServerHandle>();
	//注册客户端的请求
    public static void regClientSocket(String clientId,IMServerHandle handle){
		clients.put(clientId, handle);
	}
    //获得客户端的请求
    public static IMServerHandle getClientSocket(String clientId){
    	return clients.get(clientId);
    }

}
