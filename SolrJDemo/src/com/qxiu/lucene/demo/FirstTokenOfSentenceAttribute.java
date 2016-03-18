package com.qxiu.lucene.demo;

import org.apache.lucene.util.Attribute;


public interface FirstTokenOfSentenceAttribute extends Attribute{
	   public void setFirstChart(String chart);
	   
	     public String getFirstChart();
}
