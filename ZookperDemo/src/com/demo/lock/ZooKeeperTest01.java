package com.demo.lock;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * @author sulin
 * @date 2012-8-2 上午08:59:50
 */
@SuppressWarnings("deprecation")
public class ZooKeeperTest01 implements Watcher, Runnable, StatCallback{

	private static String znode = "/testddd";
	
	public ZooKeeper zk;
	public Stat stat;
	
	public ZooKeeperTest01(String hostPort, String znode, String filename) {
		try {
			zk = new ZooKeeper(hostPort, 5000, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(zk.exists(znode, false) == null){
				// 创建节点，权限随便设置了。
				Id id = new Id("ip", "192.168.27.113");
				ACL acl = new ACL(ZooDefs.Perms.ALL, id);
				List<ACL> acls = new ArrayList<ACL>();
				acls.add(acl);
				zk.create(znode, "这是第一次放入的数据".getBytes(), acls, CreateMode.PERSISTENT);
			}
			stat = new Stat();
			System.out.println("第一次读到的数据： " + new String(zk.getData(znode,true,stat)));
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZooKeeperTest01 temp = new ZooKeeperTest01("192.168.27.113:2181", znode, "");
		new Thread(temp).start();
		// 其他线程已启动
		try {
			temp.zk.setData(znode, "第二次放入的数据".getBytes(), temp.stat.getVersion());
			temp.zk.setData(znode, "第二次放入的数据".getBytes(), temp.stat.getVersion());
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (temp) {
			try {
				temp.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		synchronized(this){
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void close(){
		synchronized(this){
			this.notifyAll();
		}
	}

	/**
	 * 监视事件被触发时执行此方法。
	 */
	public void process(WatchedEvent event) {
		String path = event.getPath();
		if(event.getType() == Event.EventType.None){
			// 节点没有发生改变，无节点创建、无接点删除、节点数据未改变、子节点未改变
			// 那么说明可能是会话状态发生了改变
			switch(event.getState()){
			case SyncConnected:
				// 此客户端处于连接状态，不需要做任何事
				break;
			case Expired:
				// 会话失效，结束
				this.close();
				break;
			}
		}else{
			// 状态改变了，检查是否znode节点值改变。如果改变则取出
			if(path != null && path.equals(znode)){
				zk.exists(znode, true, this, null);
			}
		}
	}

	/**
	 * 状态回调方法，此方法被执行的触发条件是
	 * 在异步请求exists方法时，如果节点状态已经改变则执行此方法。
	 */
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		boolean exists = false;
		/**
		 * 现在ZooKeeper已经将异常代码换为枚举类型而不是静态int常量
		 * 可以用KeeperException.Code.get(rc)获取rc的枚举类型。
		 */
		switch(rc){
		case Code.Ok:
			// 一切完好
			exists = true;
			break;
		case Code.NoNode:
			// 节点不存在
			exists = false;
			break;
		case Code.SessionExpired:
		case Code.NoAuth:
			// 结束
			this.close();
			break;
		default:
			// 其他错误，重新尝试。。。
			zk.exists(znode, false, this, null);
			return ;
		}
		
		byte[] buf = null;
		if(exists){
			try {
				buf = zk.getData(znode, false, null);
			} catch (KeeperException e) {
				// 前面已经处理了此异常，此处不必处理
			} catch (InterruptedException e) {
				// 线程中断？事件线程中断？
				return;
			}
		}
		// 读到了数据，简单打印看看了事
		System.out.println("第二次异步读到的数据：" + new String(buf));
	}

}
