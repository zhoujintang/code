package com.qxiu.lucene.demo.ik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKTokenizer;

public class MyIKAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String text) {
	Reader reader = new BufferedReader(new StringReader(text));
	Tokenizer _IKTokenizer = new IKTokenizer(reader, false);
	TokenStream result = new IKTokenizerFilter(_IKTokenizer);
	result=new NGramTokenFilter(result, 1, 4);
		return new TokenStreamComponents(_IKTokenizer,result);
//		Tokenizer _ikTokenizer=new IKTokenizerFactory(args)
//		return new TokenStreamComponents(source);
	}
	
	public static void main(String[] args) throws IOException {
		 String str="中华人民共和国";
		 MyIKAnalyzer ikanalyzer=new MyIKAnalyzer();
		TokenStream stream= ikanalyzer.tokenStream("name", new StringReader(str) );
	  CharTermAttribute termAtt=stream.addAttribute(CharTermAttribute.class);
	  try {
	       stream.reset();
	     
	       // print all tokens until stream is exhausted
	       while (stream.incrementToken()) {
	         System.out.println(termAtt.toString());
	       }
	     
	       stream.end();
	     } finally {
	       stream.close();
	     }
	}

}
