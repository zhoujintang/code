package com.qxiu.lucene.demo;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import com.qxiu.lucene.demo.ik.IKTokenizerFilter;

public class MySplitAnalyzer extends Analyzer {

	   private Version matchVersion;
	   
	   public MySplitAnalyzer(Version matchVersion) {
	     this.matchVersion = matchVersion;
	   }
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// return new TokenStreamComponents(new WhitespaceTokenizer());
		//return new TokenStreamComponents(new MyTokenizer(1,4));
		//return new TokenStreamComponents(new MySplitTokenizer());
		 Tokenizer token=new MySplitTokenizer();
		 TokenStream strem=new MySplitFilter(token);
		     return new TokenStreamComponents(token,strem);
	}
  public static void main(String[] args) throws IOException {
	// text to tokenize
	     final String text = "This is a demo of the TokenStream API";
	     
	     Version matchVersion = Version.LUCENE_5_3_1; // Substitute desired Lucene version for XY
	     MySplitAnalyzer analyzer = new MySplitAnalyzer(matchVersion);
	     TokenStream stream = analyzer.tokenStream("field", new StringReader(text));
	     MySplitAttribute termAtt = stream.addAttribute(MySplitAttribute.class);
	     // get the CharTermAttribute from the TokenStream
	    // PartOfSpeechAttribute posAtt = stream.addAttribute(PartOfSpeechAttribute.class);
	     //
	    // FirstTokenOfSentenceAttribute firstAtt=stream.addAttribute(FirstTokenOfSentenceAttribute.class);
	 
	     try {
	       stream.reset();
	     
	       // print all tokens until stream is exhausted
	       while (stream.incrementToken()) {
	         System.out.println(termAtt.getSplit());
	       }
	     
	       stream.end();
	     } finally {
	       stream.close();
	     }
}

}
