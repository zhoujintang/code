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
 * 分布式锁
 * @author Administrator
 *
 */
public class DistributedLock {
	
   String path="/task";
   /**
    * 判断节点是存在，如果不存在 则创建
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
			//同一个节点 同时只有一个创建成果
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
    * 创建节点
    * @param path
    * @param data
    */
   public void create(String path,String data, ZooKeeper zk){
	   
   }
   /**
    * 当分布式任务执行完成后 删除该节点
    * @param path
    */
   public void delete(String path, ZooKeeper zk){
	   
   }
   public static void main(String[] args) {
	   try {
		ZooKeeper zk = new ZooKeeper("localhost:2181", 
			        5000, new Watcher() { 
			            // 监控所有被触发的事件
			            public void process(WatchedEvent event) { 
			            	//如果session失效
			            	//NodeDeleted
			                System.out.println("已经触发了" + event.getType() + "事件！"+event.getPath()); 
			                
			                //判断是否为创建 修改 删除事件
			                if (event.getType() == EventType.NodeCreated) {  
			                    System.out.println("节点创建");
			                    //获得节点的内容 判断是否与当前ip一致，如果是 则执行任务
			                    System.out.println("ffffffffffffffff执行完成");
			                   // zk.delete(event.getPath(), version);
			                   //否则 跳过
			                } 
			       		 if (event.getType() == EventType.NodeDataChanged ) {  
			                    System.out.println("节点数据改变");
			              } 
			       		 if (event.getType() == EventType.NodeDeleted ) {  
			                      System.out.println("节点删除");
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
