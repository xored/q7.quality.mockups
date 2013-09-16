package com.xored.q7.quality.mockups.ui.treeviewer.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeCategory;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeViewerModel;

public class CategoryProvider implements ITreeContentProvider {

	private TreeViewerModel model;

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.model = (TreeViewerModel) newInput;
	}

	public Object[] getElements(Object inputElement) {
		return model.getCategories().toArray();
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TreeCategory) {
			TreeCategory category = (TreeCategory) parentElement;
			return category.getItems().toArray();
		}
		return null;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof TreeCategory) {
			return true;
		}
		return false;
	}
}
