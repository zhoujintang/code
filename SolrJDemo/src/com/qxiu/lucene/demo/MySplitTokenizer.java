package com.qxiu.lucene.demo;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.helpers.DateLayout;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;
import org.apache.lucene.analysis.util.CharacterUtils;
import org.apache.lucene.analysis.util.CharacterUtils.CharacterBuffer;

public class MySplitTokenizer extends Tokenizer {
    private int start=0;
    private int length=4;
    private int dataLength;
    private static final int IO_BUFFER_SIZE = 4096;
    private final CharacterUtils charUtils = CharacterUtils.getInstance();
    private final CharacterBuffer ioBuffer = CharacterUtils.newCharacterBuffer(IO_BUFFER_SIZE);
    private final MySplitAttribute mysplit=addAttribute(MySplitAttribute.class);
	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		if(dataLength==0){
			charUtils.fill(ioBuffer, input);
	        dataLength=ioBuffer.getLength();
		}
        String str=new String(ioBuffer.getBuffer());
              if(dataLength==0){
            	  return false;
              }else{
            	  if(start>=dataLength){
            		  return false;
            	  }
            	  length=new Random().nextInt(6)+1;
            	  String split=str.substring(start, start+length);
            	  mysplit.setSplit(split);
            	  start=start+length;
            	  return true;
              }
		     
		   
	}

}
