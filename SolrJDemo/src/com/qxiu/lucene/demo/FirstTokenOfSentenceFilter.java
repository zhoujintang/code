package com.qxiu.lucene.demo;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class FirstTokenOfSentenceFilter extends TokenFilter {
	FirstTokenOfSentenceAttribute firstAttr=addAttribute(FirstTokenOfSentenceAttribute.class);
	  CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	protected FirstTokenOfSentenceFilter(TokenStream input) {
		super(input);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if(!input.incrementToken()){return false;}
	  String s=termAtt.toString().substring(0, 1);
	  firstAttr.setFirstChart(s.toLowerCase());
		return true;
	}

}
