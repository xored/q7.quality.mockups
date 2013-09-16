package com.xored.q7.quality.mockups.ui.treeviewer.model;

import java.util.ArrayList;
import java.util.List;

public class TreeCategory {
	private String name;
	
	private List<TreeItem> items = new ArrayList<TreeItem>();
	
	public TreeCategory(String name) {
		this.name = name;
	}
	
	public void addItem(TreeItem item) {
		items.add(item);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TreeItem> getItems() {
		return items;
	}	
}
