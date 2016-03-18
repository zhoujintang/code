package com.qxiu.lucene.demo;

import org.apache.lucene.util.AttributeImpl;

public class FirstTokenOfSentenceAttributeImpl extends AttributeImpl implements
		FirstTokenOfSentenceAttribute {
   private String firstChart;
	@Override
	public void setFirstChart(String chart) {
      this.firstChart=chart;
	}

	@Override
	public String getFirstChart() {
		return firstChart;
	}

	@Override
	public void clear() {
		firstChart=null;

	}

	@Override
	public void copyTo(AttributeImpl target) {
     ((FirstTokenOfSentenceAttribute)target).setFirstChart(firstChart);
	}

}
