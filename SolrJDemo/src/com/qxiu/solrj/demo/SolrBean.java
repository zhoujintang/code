package com.qxiu.solrj.demo;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBean {
	@Field(value="id")
	private String id;
	@Field
	private String content;
	@Field
	private int weight;
	@Field
	private int level;
	@Field
	private int type;
	@Field
	private int online;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

}
