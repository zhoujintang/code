package socket;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IMClientConnectionManager {
	private static Map<String, IMServerHandle> clients=new ConcurrentHashMap<String, IMServerHandle>();
	//ע��ͻ��˵�����
    public static void regClientSocket(String clientId,IMServerHandle handle){
		clients.put(clientId, handle);
	}
    //��ÿͻ��˵�����
    public static IMServerHandle getClientSocket(String clientId){
    	return clients.get(clientId);
    }

}
