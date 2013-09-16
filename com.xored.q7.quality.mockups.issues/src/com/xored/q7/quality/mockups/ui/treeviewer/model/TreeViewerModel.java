package com.xored.q7.quality.mockups.ui.treeviewer.model;

import java.util.ArrayList;
import java.util.List;

public class TreeViewerModel {
	public List<TreeCategory> getCategories() {
		List<TreeCategory> categories = new ArrayList<TreeCategory>();

		TreeCategory cat1 = new TreeCategory("Category 1");
		TreeCategory cat2 = new TreeCategory("Category 2");
		TreeCategory cat3 = new TreeCategory("Category 3");
		TreeCategory cat4 = new TreeCategory("Category 4");

		cat1.addItem(new TreeItem("Item 1"));
		cat2.addItem(new TreeItem("Item 2"));
		cat3.addItem(new TreeItem("Item 3"));
		cat4.addItem(new TreeItem("Item 4"));
		cat1.addItem(new TreeItem("Item 5"));
		cat2.addItem(new TreeItem("Item 6"));
		cat3.addItem(new TreeItem("Item 7"));
		cat4.addItem(new TreeItem("Item 8"));
		cat1.addItem(new TreeItem("Item 9"));
		cat1.addItem(new TreeItem("Item 10"));
		cat3.addItem(new TreeItem("Item 11"));
		cat3.addItem(new TreeItem("Item 12"));
		
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		categories.add(cat4);
		
		return categories;
	}
}
