package com.qxiu.lucene.demo;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Demo {
public static void main(String[] args) throws IOException, ParseException {
	  Analyzer analyzer = new StandardAnalyzer();
      Analyzer ik=new IKAnalyzer(true);
	    Directory directory = new RAMDirectory();
	 
	    //Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
	    IndexWriterConfig config = new IndexWriterConfig(ik);
	    IndexWriter iwriter = new IndexWriter(directory, config);

	    String[] texts = new String[]{"女人花"
	    };

	    for (String text : texts) {
	        Document doc = new Document();
	        doc.add(new TextField("fieldname", text,Store.YES));
	        doc.add(new Field("","",  
	        		Field.Store.YES, Field.Index.ANALYZED));  
	        iwriter.addDocument(doc);
	    }
	    iwriter.close();
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	   QueryParser parser = new QueryParser("fieldname", ik);
	  // parser.setDefaultOperator(QueryParser.AND_OPERATOR);
	    Query query = parser.parse("女人");
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	    for (int i = 0; i < hits.length; i++) {
	        Document hitDoc = isearcher.doc(hits[i].doc);
	        System.out.println(hitDoc.get("fieldname"));
	    }
	    ireader.close();
	    directory.close();
}
}
