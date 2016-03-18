package com.qxiu.lucene.demo.ik;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilteringTokenFilter;

public class IKTokenizerFilter extends FilteringTokenFilter {
	 public IKTokenizerFilter(TokenStream in) {
		super(in);
	}
	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	@Override
	protected boolean accept() throws IOException {
		if(termAtt.length()>=2){
			return true;
		}
		return false;
	}
	

}
