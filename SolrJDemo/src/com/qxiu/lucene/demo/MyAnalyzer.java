package com.qxiu.lucene.demo;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class MyAnalyzer extends Analyzer {

	   private Version matchVersion;
	   
	   public MyAnalyzer(Version matchVersion) {
	     this.matchVersion = matchVersion;
	   }
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// return new TokenStreamComponents(new WhitespaceTokenizer());
		//return new TokenStreamComponents(new MyTokenizer(1,4));
		//return new TokenStreamComponents(new MySplitTokenizer());
		  final Tokenizer source = new WhitespaceTokenizer();
		     TokenStream result = new LengthFilter(matchVersion, source, 3, Integer.MAX_VALUE);
		    result = new PartOfSpeechTaggingFilter(result);
		    result=new FirstTokenOfSentenceFilter(result);
		     return new TokenStreamComponents(source, result);
	}
  public static void main(String[] args) throws IOException {
	// text to tokenize
	     final String text = "This is a demo of the TokenStream API";
	     
	     Version matchVersion = Version.LUCENE_5_3_1; // Substitute desired Lucene version for XY
	     MyAnalyzer analyzer = new MyAnalyzer(matchVersion);
	     TokenStream stream = analyzer.tokenStream("field", new StringReader(text));
	     CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);
	     // get the CharTermAttribute from the TokenStream
	     PartOfSpeechAttribute posAtt = stream.addAttribute(PartOfSpeechAttribute.class);
	     //
	     FirstTokenOfSentenceAttribute firstAtt=stream.addAttribute(FirstTokenOfSentenceAttribute.class);
	 
	     try {
	       stream.reset();
	     
	       // print all tokens until stream is exhausted
	       while (stream.incrementToken()) {
	         System.out.println(termAtt.toString()+ ": " + posAtt.getPartOfSpeech()+" "+firstAtt.getFirstChart());
	       }
	     
	       stream.end();
	     } finally {
	       stream.close();
	     }
}

}
