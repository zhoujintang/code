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
	 * ����zookeeper
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
		//�ж��Ƿ���ڣ���������ڣ��򴴽�
		try {
			
			if(zooKeeper.exists(path, true)!=null){
				zooKeeper.delete(path, -1);
			}
			zooKeeper.create(path, "��ʼ������".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ʵ��watcher�Ľӿڷ�����������zookeeper�ɹ���zookeeper��ͨ���˷���֪ͨwatcher<br>
	 * ��ch��Ϊ������ܵ����ӳɹ���event����countDown���õ�ǰ�̼߳����������顣
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
             System.out.println("�ڵ㴴��");
         } 
		 if (event.getType() == EventType.NodeDataChanged ) {  
             System.out.println("�ڵ����ݸı�");
       } 
		 if (event.getType() == EventType.NodeDeleted ) {  
               System.out.println("�ڵ�ɾ��");
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
			//���Ӽ���
			
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
	 * ����·�������ڵ㣬�������ýڵ�����
	 * @param path
	 * @param data
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String createNode(String path, byte[] data) throws KeeperException,
			InterruptedException
	{
		//�ж��Ƿ���ڣ�������ڣ���ɾ��
		Stat stat=this.zooKeeper.exists(path, false);
		if(stat!=null){
			System.out.println("�Ѿ����ڣ���ɾ��ԭ���ļ�¼");
			this.zooKeeper.delete(path, stat.getVersion());
		}
		return this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
	}

	/**
	 * ����·����ȡ���к��ӽڵ�
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
	 * ����·����ȡ�ڵ�����
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
	 * ɾ���ڵ�
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
	 * �ر�zookeeper����
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
	// ����zookeeper
	baseZookeeper.connectZookeeper(host);
	try {
		System.out.println("--------connect zookeeper ok-----------");
		//�����ж��Ƿ���ڣ�������ڣ���ɾ��
		//���� ����
		//baseZookeeper.zooKeeper.delete("/zookeeper", -1);
//		// �����ڵ�
//		byte [] data = {1, 2, 3, 4, 5};
//		String result = baseZookeeper.createNode("/testcate", data);
//		System.out.println(result);
//		System.out.println("--------create node ok-----------");
//		
//		// ��ȡĳ·�������нڵ�
//		List<String> children = baseZookeeper.getChildren("/");
//		for (String child : children)
//		{
//			System.out.println(child);
//		}
//		System.out.println("--------get children ok-----------");
//		
//		// ��ȡ�ڵ�����
//		byte [] nodeData = baseZookeeper.getData("/testcate");
//		System.out.println(Arrays.toString(nodeData));
//		System.out.println("--------get node data ok-----------");
//		
//		// ���½ڵ�����
//		data = "test data".getBytes();
//		baseZookeeper.setData("/testcate", data, 0);
//		System.out.println("--------set node data ok-----------");
//		
//		nodeData = baseZookeeper.getData("/testcate");
//		System.out.println(new String(nodeData));
//		System.out.println("--------get node new data ok-----------");
//		
//		//��������
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
