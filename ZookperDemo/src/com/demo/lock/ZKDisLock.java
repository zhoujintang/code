package com.demo.lock;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKDisLock implements Watcher, Runnable {
	private ZooKeeper zk;
	private int i;
	private String ips;

	public ZKDisLock(int i, String ips) {
		this.i = i;
		this.ips = ips;
	}

	public void connection(String ips) {
		try {
			zk = new ZooKeeper(ips, 5000, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteNote(String path) {

	}

	public String getLocalIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	public void createParentPaths(String path) {
		try {
			String[] paths = path.split("/");
			String tempPath = "";
			for (int i = 1; i < paths.length - 1; i++) {
				tempPath = "/" + paths[i];
				Stat stat=zk.exists(tempPath, true);
				if (stat== null) {
					zk.create(tempPath, tempPath.getBytes(), Ids.OPEN_ACL_UNSAFE,
							CreateMode.PERSISTENT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void deletePaths(String path){
		try {
			String[] paths = path.split("/");
			String tempPath = "";
			for (int i = 1; i < paths.length - 1; i++) {
				tempPath = "/" + paths[i];
				if (zk.exists(tempPath, false) != null) {
					zk.create(tempPath, tempPath.getBytes(), Ids.OPEN_ACL_UNSAFE,
							CreateMode.PERSISTENT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 当时任务调用的方法 当节点中已经存在，获得节点的数据
	 */
	public void createLock(String path) {
		try {
			connection(ips);
			Stat stat = zk.exists(path, true);
			if (stat == null) {
				createParentPaths(path);
				String string = zk.create(path,
						(i + "_" + getLocalIp()).getBytes(),
						Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				System.out.println("创建锁成功" + i);
				// 执行任务
				Thread.sleep(10000);
				byte[] b = zk.getData(path, true, null);
				System.out.println(new String(b));
				zk.delete(path, -1);
				zk.close();
			} else {
				byte[] b = zk.getData(path, true, null);
				System.out.println("改锁已经创建：" + new String(b));
				//zk.delete(path, -1);
				zk.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			byte[] b;
			try {
				b = zk.getData(path, true, null);
				System.out.println("改锁已经创建：" + new String(b));
			} catch (KeeperException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			close();
		}
		
	}

	public void close() {
		try {
			zk.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("已经触发了" + event.getType() + "事件！" + event.getPath());
		KeeperState keeperState = event.getState();
		if (event.getState() == KeeperState.SyncConnected) {
		} else if (event.getState() == KeeperState.Expired) {
			// 当失效后 重连
			// session expired, may be never happending.
			// close old client and rebuild new client
			close();
			connection(ips);
		}
	}

	public static void main(String[] args) {
		String ips = "localhost:2181";
		for (int i = 0; i < 10; i++) {
			new Thread(new ZKDisLock(i, ips)).start();
		}
	}

	@Override
	public void run() {
		//connection(ips);
		String path = "/qiqi_push/getfanslock";
		createLock(path);

	}
}
