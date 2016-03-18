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
 * @date 2012-8-2 ����08:59:50
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
				// �����ڵ㣬Ȩ����������ˡ�
				Id id = new Id("ip", "192.168.27.113");
				ACL acl = new ACL(ZooDefs.Perms.ALL, id);
				List<ACL> acls = new ArrayList<ACL>();
				acls.add(acl);
				zk.create(znode, "���ǵ�һ�η��������".getBytes(), acls, CreateMode.PERSISTENT);
			}
			stat = new Stat();
			System.out.println("��һ�ζ��������ݣ� " + new String(zk.getData(znode,true,stat)));
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZooKeeperTest01 temp = new ZooKeeperTest01("192.168.27.113:2181", znode, "");
		new Thread(temp).start();
		// �����߳�������
		try {
			temp.zk.setData(znode, "�ڶ��η��������".getBytes(), temp.stat.getVersion());
			temp.zk.setData(znode, "�ڶ��η��������".getBytes(), temp.stat.getVersion());
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
	 * �����¼�������ʱִ�д˷�����
	 */
	public void process(WatchedEvent event) {
		String path = event.getPath();
		if(event.getType() == Event.EventType.None){
			// �ڵ�û�з����ı䣬�޽ڵ㴴�����޽ӵ�ɾ�����ڵ�����δ�ı䡢�ӽڵ�δ�ı�
			// ��ô˵�������ǻỰ״̬�����˸ı�
			switch(event.getState()){
			case SyncConnected:
				// �˿ͻ��˴�������״̬������Ҫ���κ���
				break;
			case Expired:
				// �ỰʧЧ������
				this.close();
				break;
			}
		}else{
			// ״̬�ı��ˣ�����Ƿ�znode�ڵ�ֵ�ı䡣����ı���ȡ��
			if(path != null && path.equals(znode)){
				zk.exists(znode, true, this, null);
			}
		}
	}

	/**
	 * ״̬�ص��������˷�����ִ�еĴ���������
	 * ���첽����exists����ʱ������ڵ�״̬�Ѿ��ı���ִ�д˷�����
	 */
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		boolean exists = false;
		/**
		 * ����ZooKeeper�Ѿ����쳣���뻻Ϊö�����Ͷ����Ǿ�̬int����
		 * ������KeeperException.Code.get(rc)��ȡrc��ö�����͡�
		 */
		switch(rc){
		case Code.Ok:
			// һ�����
			exists = true;
			break;
		case Code.NoNode:
			// �ڵ㲻����
			exists = false;
			break;
		case Code.SessionExpired:
		case Code.NoAuth:
			// ����
			this.close();
			break;
		default:
			// �����������³��ԡ�����
			zk.exists(znode, false, this, null);
			return ;
		}
		
		byte[] buf = null;
		if(exists){
			try {
				buf = zk.getData(znode, false, null);
			} catch (KeeperException e) {
				// ǰ���Ѿ������˴��쳣���˴����ش���
			} catch (InterruptedException e) {
				// �߳��жϣ��¼��߳��жϣ�
				return;
			}
		}
		// ���������ݣ��򵥴�ӡ��������
		System.out.println("�ڶ����첽���������ݣ�" + new String(buf));
	}

}
