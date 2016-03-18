package com.qxiu.lucene.demo;

import org.apache.lucene.util.AttributeImpl;

public class MySplitAttributeImpl extends AttributeImpl implements
		MySplitAttribute {
    private String split;
	@Override
	public void clear() {
    split=null;
	}

	@Override
	public void copyTo(AttributeImpl target) {
       ((MySplitAttributeImpl)target).setSplit(split);
	}

	@Override
	public String getSplit() {
		return split;
	}

	@Override
	public void setSplit(String split) {
		this.split=split;
	}

}
