package com.xored.q7.quality.mockups.issues.internal;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SampleTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		return ((List<SampleTreeNode>) inputElement).toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((SampleTreeNode) parentElement).children.toArray();
	}

	@Override
	public Object getParent(Object element) {
		return ((SampleTreeNode) element).getParent().orElse(null);
	}

	@Override
	public boolean hasChildren(Object element) {
		return !((SampleTreeNode) element).children.isEmpty();
	}

}
