package com.xored.q7.quality.mockups.issues.internal;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SampleTreeContentProvider implements ITreeContentProvider {

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		return ((List<SampleTreeNode>) inputElement).toArray();
	}

	public Object[] getChildren(Object parentElement) {
		return ((SampleTreeNode) parentElement).children.toArray();
	}

	public Object getParent(Object element) {
		return ((SampleTreeNode) element).parent;
	}

	public boolean hasChildren(Object element) {
		return !((SampleTreeNode) element).children.isEmpty();
	}

}
