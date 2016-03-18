package com.qxiu.lucene.demo;

import org.apache.lucene.util.Attribute;

public interface MySplitAttribute extends Attribute {
   public String getSplit();
   public void setSplit(String split);
}
