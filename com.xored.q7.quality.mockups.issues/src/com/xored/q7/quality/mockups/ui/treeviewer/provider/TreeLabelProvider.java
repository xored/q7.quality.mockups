package com.xored.q7.quality.mockups.ui.treeviewer.provider;

import org.eclipse.jface.viewers.LabelProvider;

import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeCategory;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeItem;

public class TreeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
	    if (element instanceof TreeCategory) {
	        TreeCategory category = (TreeCategory) element;
	        return category.getName();
	    }
	    return ((TreeItem) element).getName();
	}
}
