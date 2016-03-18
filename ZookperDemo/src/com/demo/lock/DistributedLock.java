package com.demo.lock;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * �ֲ�ʽ��
 * @author Administrator
 *
 */
public class DistributedLock {
	
   String path="/task";
   /**
    * �жϽڵ��Ǵ��ڣ���������� �򴴽�
    * @param path
    */
   public static void checkExist(String path, ZooKeeper zk){
	  // zk.exists(path, watch)
	try {
		Stat stat=zk.exists(path, true);
		if(stat!=null){
			byte[] b=zk.getData(path, false, stat);
			System.out.println(new String(b));
			zk.delete(path, stat.getVersion());
		}else{
			//ͬһ���ڵ� ͬʱֻ��һ�������ɹ�
			String string=zk.create(path, "192.168.28.126".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			//String string1=zk.create(path, "192.168.28.129".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			
			//zk.setData(path, "fawefafeaf".getBytes(),1);
			byte[] b=zk.getData(path, true, null);
			System.out.println(new String(b));
		}
	} catch (KeeperException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   /**
    * �����ڵ�
    * @param path
    * @param data
    */
   public void create(String path,String data, ZooKeeper zk){
	   
   }
   /**
    * ���ֲ�ʽ����ִ����ɺ� ɾ���ýڵ�
    * @param path
    */
   public void delete(String path, ZooKeeper zk){
	   
   }
   public static void main(String[] args) {
	   try {
		ZooKeeper zk = new ZooKeeper("localhost:2181", 
			        5000, new Watcher() { 
			            // ������б��������¼�
			            public void process(WatchedEvent event) { 
			            	//���sessionʧЧ
			            	//NodeDeleted
			                System.out.println("�Ѿ�������" + event.getType() + "�¼���"+event.getPath()); 
			                
			                //�ж��Ƿ�Ϊ���� �޸� ɾ���¼�
			                if (event.getType() == EventType.NodeCreated) {  
			                    System.out.println("�ڵ㴴��");
			                    //��ýڵ������ �ж��Ƿ��뵱ǰipһ�£������ ��ִ������
			                    System.out.println("ffffffffffffffffִ�����");
			                   // zk.delete(event.getPath(), version);
			                   //���� ����
			                } 
			       		 if (event.getType() == EventType.NodeDataChanged ) {  
			                    System.out.println("�ڵ����ݸı�");
			              } 
			       		 if (event.getType() == EventType.NodeDeleted ) {  
			                      System.out.println("�ڵ�ɾ��");
			                } 
			                
			            } 
			        });
		checkExist("/task", zk);
		String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(ip);
		
	} catch (IOException e) {
		e.printStackTrace();
	} 
}
}
