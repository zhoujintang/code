package com.demo.lock;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.sun.org.apache.regexp.internal.recompile;

public class BaseZookeeper implements Watcher
{

	public ZooKeeper zooKeeper;

	private static final int SESSION_TIME_OUT = 2000;

	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	private String path="";

	/**
	 * 连接zookeeper
	 * 
	 * @param host
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void connectZookeeper(String host) throws IOException,
			InterruptedException
	{
		zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
		countDownLatch.await();
		System.out.println("zookeeper connect ok");
		//判断是否存在，如果不存在，则创建
		try {
			
			if(zooKeeper.exists(path, true)!=null){
				zooKeeper.delete(path, -1);
			}
			zooKeeper.create(path, "初始化数据".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 实现watcher的接口方法，当连接zookeeper成功后，zookeeper会通过此方法通知watcher<br>
	 * 此chǔ为如果接受到连接成功的event，则countDown，让当前线程继续其他事情。
	 */
	@Override
	public void process(WatchedEvent event)
	{
		System.out.println("state:"+event.getState());
		System.out.println("type:"+event.getType());
//		if (event.getState() == KeeperState.SyncConnected)
//		{
//			System.out.println("watcher received event");
//			//countDownLatch.countDown();
//		}
		 if (event.getType() == EventType.NodeCreated) {  
             System.out.println("节点创建");
         } 
		 if (event.getType() == EventType.NodeDataChanged ) {  
             System.out.println("节点数据改变");
       } 
		 if (event.getType() == EventType.NodeDeleted ) {  
               System.out.println("节点删除");
         } 
		 Stat stat=new Stat();
	try {
		zooKeeper.getData(path, this, stat);
	} catch (KeeperException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//	try {
			//增加监听
			
				//zooKeeper.exists(path, this);
			
	//	} catch (KeeperException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	}

	/**
	 * 根据路径创建节点，并且设置节点数据
	 * @param path
	 * @param data
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String createNode(String path, byte[] data) throws KeeperException,
			InterruptedException
	{
		//判断是否存在，如果存在，则删除
		Stat stat=this.zooKeeper.exists(path, false);
		if(stat!=null){
			System.out.println("已经存在，则删除原来的记录");
			this.zooKeeper.delete(path, stat.getVersion());
		}
		return this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
	}

	/**
	 * 根据路径获取所有孩子节点
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public List<String> getChildren(String path) throws KeeperException,
			InterruptedException
	{
		return this.zooKeeper.getChildren(path, false);
	}

	public Stat setData(String path, byte [] data, int version) throws KeeperException, InterruptedException
	{
		return this.zooKeeper.setData(path, data, version);
	}
	
	/**
	 * 根据路径获取节点数据
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] getData(String path) throws KeeperException,
			InterruptedException
	{
		return this.zooKeeper.getData(path, true, null);
	}

	/**
	 * 删除节点
	 * 
	 * @param path
	 * @param version
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	public void deletNode(String path, int version)
			throws InterruptedException, KeeperException
	{
		this.zooKeeper.delete(path, version);
	}

	/**
	 * 关闭zookeeper连接
	 * 
	 * @throws InterruptedException
	 */
	public void closeConnect() throws InterruptedException
	{
		if (null != zooKeeper)
		{
			zooKeeper.close();
		}
	}
public static void main(String[] args) throws Exception{
	BaseZookeeper baseZookeeper = new BaseZookeeper();
	
	String host = "test.zookper.com:2181";
	baseZookeeper.path="/testcate";
	// 连接zookeeper
	baseZookeeper.connectZookeeper(host);
	try {
		System.out.println("--------connect zookeeper ok-----------");
		//首先判断是否存在，如果存在，则删除
		//否则 创建
		//baseZookeeper.zooKeeper.delete("/zookeeper", -1);
//		// 创建节点
//		byte [] data = {1, 2, 3, 4, 5};
//		String result = baseZookeeper.createNode("/testcate", data);
//		System.out.println(result);
//		System.out.println("--------create node ok-----------");
//		
//		// 获取某路径下所有节点
//		List<String> children = baseZookeeper.getChildren("/");
//		for (String child : children)
//		{
//			System.out.println(child);
//		}
//		System.out.println("--------get children ok-----------");
//		
//		// 获取节点数据
//		byte [] nodeData = baseZookeeper.getData("/testcate");
//		System.out.println(Arrays.toString(nodeData));
//		System.out.println("--------get node data ok-----------");
//		
//		// 更新节点数据
//		data = "test data".getBytes();
//		baseZookeeper.setData("/testcate", data, 0);
//		System.out.println("--------set node data ok-----------");
//		
//		nodeData = baseZookeeper.getData("/testcate");
//		System.out.println(new String(nodeData));
//		System.out.println("--------get node new data ok-----------");
//		
//		//创建序列
//		System.out.println("--------get node sequential-----------");
		//baseZookeeper.zooKeeper.create("/_locknode_", "PERSISTENT_SEQUENTIAL".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		//baseZookeeper.zooKeeper.create("/_locknode_/", "PERSISTENT_SEQUENTIAL".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		//baseZookeeper.zooKeeper.create("/_locknode_/guid-lock-", "PERSISTENT_SEQUENTIAL".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		//baseZookeeper.zooKeeper.create("/_locknode_/guid-lock-", "PERSISTENT_SEQUENTIAL".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		//baseZookeeper.zooKeeper.create("/_locknode_/guid-lock-", "PERSISTENT_SEQUENTIAL".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		//baseZookeeper.zooKeeper.create("/locks", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//baseZookeeper.zooKeeper.create("/locks"+"/"+"lock3", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//List<String> list=baseZookeeper.zooKeeper.getChildren("/locks", true);
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//			//baseZookeeper.zooKeeper.delete("/"+list.get(i), -1);
//		}
		byte[] nodeData=baseZookeeper.getData("/testcate");
		System.out.println(new String(nodeData));
		baseZookeeper.zooKeeper.setData("/testcate", "fawefaf".getBytes(), -1);
		//baseZookeeper.setData("/testcate", "fawefaf".getBytes(), -1);
		byte[] nodeData1=baseZookeeper.getData("/testcate");
		System.out.println(new String(nodeData1));
		baseZookeeper.zooKeeper.setData("/testcate", "fawefaf".getBytes(), -1);
		baseZookeeper.zooKeeper.delete("/testcate",-1);
		
	} catch (Exception e) {
		throw e;
	}finally{
		baseZookeeper.closeConnect();
		System.out.println("--------close zookeeper ok-----------");
	}
	
}
}
