package com.demo.lock;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperDemo {
 public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
	// ����һ���������������
	 ZooKeeper zk = new ZooKeeper("localhost:2181", 
	        5000, new Watcher() { 
	            // ������б��������¼�
	            public void process(WatchedEvent event) { 
	                System.out.println("�Ѿ�������" + event.getType() + "�¼���"+event.getPath()); 
	            } 
	        }); 
	 zk.exists("/testRootPath",true);
	 // ����һ��Ŀ¼�ڵ�
	 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
	   CreateMode.PERSISTENT); 
	 // ����һ����Ŀ¼�ڵ�
	 zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
	   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	 System.out.println(new String(zk.getData("/testRootPath",true,null))); 
	 // ȡ����Ŀ¼�ڵ��б�
	 System.out.println(zk.getChildren("/testRootPath",true)); 
	 // �޸���Ŀ¼�ڵ�����
	 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
	 System.out.println("Ŀ¼�ڵ�״̬��["+zk.exists("/testRootPath",true)+"]"); 
	 // ��������һ����Ŀ¼�ڵ�
	 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
	   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	 System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
	 zk.exists("/testRootPath/testChildPathOne",true); 
	 zk.setData("/testRootPath/testChildPathOne", "�޸�����".getBytes(), -1);
	 zk.exists("/testRootPath/testChildPathOne",true); 
	 zk.setData("/testRootPath/testChildPathOne", "�޸�����11".getBytes(), -1);
	 System.out.println(new String(zk.getData("/testRootPath/testChildPathOne",true,null))); 
	 // ɾ����Ŀ¼�ڵ�
	 zk.delete("/testRootPath/testChildPathTwo",-1); 
	 zk.delete("/testRootPath/testChildPathOne",-1); 
	 // ɾ����Ŀ¼�ڵ�
	 zk.delete("/testRootPath",-1); 
	 // �ر�����
	 zk.close();
}
}
