package com.qxiu.lucene.demo;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilteringTokenFilter;
import org.apache.lucene.util.Version;

public class LengthFilter extends FilteringTokenFilter {
	
	public LengthFilter(Version version, TokenStream in, int min, int max) {
	     super(in);
	     this.min = min;
	     this.max = max;
	   }
	private final int min;
	   private final int max;
	   
	 private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	
	@Override
	protected boolean accept() throws IOException {
		 final int len = termAtt.length();
	     return (len >= min && len <= max);
	}

}
