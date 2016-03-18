package com.qxiu.lucene.demo;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class MySplitFilter extends TokenFilter {
	private final MySplitAttribute mysplit=addAttribute(MySplitAttribute.class);
	protected MySplitFilter(TokenStream input) {
		super(input);
	}

	@Override
	public boolean incrementToken() throws IOException {
		while(input.incrementToken()){
			//获得长度的判断
			if(mysplit.getSplit().length()>=3){
				return true;
			}
		}
		return false;
	}

}
