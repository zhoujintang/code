package com.qxiu.solrj.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;

public class SolrJAPI {
    public static String url="http://121.201.62.60:8083/solr/mycore";
    public static void add() throws IOException, SolrServerException{
    	//创建客户端
   	 SolrClient solr=new HttpSolrClient(url);
//   	 增加对象
   	 SolrBean bean=new SolrBean();
   	 bean.setId("99999");
   	 bean.setContent("我是中国人");
   	 bean.setLevel(0);
   	 bean.setWeight(0);
   	 bean.setOnline(0);
   
   	UpdateResponse response = 	solr.addBean(bean);  
    System.out.println(response.getElapsedTime());  
    solr.commit();  
    solr.close();  
    }
    public static void update() throws IOException, SolrServerException{
       	//创建客户端
      	 SolrClient solr=new HttpSolrClient(url);
//      	 增加对象
      	 SolrBean bean=new SolrBean();
      	 bean.setId("99998");
      	 bean.setContent("我是中国人呜呜呜呜");
      	 bean.setLevel(0);
      	 bean.setWeight(0);
      	 bean.setOnline(1);
      
      	UpdateResponse response = 	solr.addBean(bean);  
       System.out.println(response.getElapsedTime());  
       solr.commit();  
       solr.close();  
    }
    public static void delete() throws SolrServerException, IOException{
    	 SolrClient solr=new HttpSolrClient(url);
    		//UpdateResponse response = 	 solr.deleteById("99999");
    		String query="id:99998 AND type:0";
    		UpdateResponse response =solr.deleteByQuery(query);
    	  System.out.println(response.getElapsedTime());  
    	    solr.commit();  
    	    solr.close(); 
    }
    public static void select() throws SolrServerException, IOException{
    	 SolrClient solr = new HttpSolrClient(url);  
         Map<String, String> map = new HashMap<String, String>();  
         map.put("q", "content:中国");
         //在麦降序 类型升序
         map.put("sort", "online desc,type asc");
         SolrParams params = new MapSolrParams(map);  
         QueryResponse resp = solr.query(params);  
         //以下是第二种方法  
         //String queryString="content:test";  
         //MultiMapSolrParams mParams = SolrRequestParsers.parseQueryString("queryString");  
         //QueryResponse resp = solr.query(mParams);  
         SolrDocumentList docsList = resp.getResults();  
         System.out.println(docsList.size());  
         for (SolrDocument doc : docsList) {  
              System.out.println(doc.get("id")); 
              System.out.println(doc.get("content"));
         }  
        
         SolrQuery query=new SolrQuery();
         query.addFilterQuery("content:中国");
         query.addSort("online", SolrQuery.ORDER.desc);
         query.addSort("type", SolrQuery.ORDER.asc);
         query.setHighlight(true); // 开启高亮组件  
         query.addHighlightField("content");// 高亮字段  
         query.setHighlightSimplePre("<font color=\"red\">");// 标记  
         query.setHighlightSimplePost("</font>");  
         query.setHighlightSnippets(1);// 结果分片数，默认为1  
         query.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100  
         QueryResponse q=solr.query(query);
         SolrDocumentList docsList1 = q.getResults();  
         System.out.println(docsList1.size());  
         for (SolrDocument doc : docsList1) {  
              System.out.println(doc.get("id")); 
              System.out.println(doc.get("content"));
         } 
         solr.close();
    }
	public static void main(String[] args) {
       try {
		//add();
		//update();
    	//delete();
    	   select();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (SolrServerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
