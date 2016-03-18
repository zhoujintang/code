package com.demo.lock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

public class Lock  implements Watcher, StatCallback,Runnable{
	private static final int SESSION_TIMEOUT = 10000; 
    private static final String CONNECTION_STRING = "localhost:2181"; 
    private static String znode = "/znodetest";
	
	public ZooKeeper zk;
    public Lock(){
		try {
			zk = new ZooKeeper(CONNECTION_STRING,SESSION_TIMEOUT,this);
			if(zk.exists(znode, true)==null){
				zk.create(znode, "开始".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
public static void main(String[] args) {
//	Lock lock=new Lock();
//	try {
//		lock.zk.setData(znode, "修改内容了".getBytes(), lock.stat.getAversion());
//		byte[] b=lock.zk.getData(znode, false, lock.stat);
//		System.out.println(new String(b));
//	} catch (KeeperException e) {
//		e.printStackTrace();
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}
	Thread thread=new Thread(new Lock());
	thread.start();
}
@Override
public void process(WatchedEvent event) {
	
	System.out.println("触发时间类型"+event.getType()+" "+event.getPath());
   try {
	zk.exists(znode, true);
} catch (KeeperException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
}
@Override
public void processResult(int rc, String path, Object ctx, Stat stat) {
	System.out.println("监控");
	
}
@Override
public void run() {
	while (true) {
		//修改值
		Random random=new Random();
		String dd="ssssssssssssss"+random.nextDouble();
		System.out.println("set date=>"+dd);
		try {
			zk.setData(znode, dd.getBytes(),-1);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

}
