package com.xored.q7.quality.mockups.swt.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeStyledTextContentProvider implements ITreeContentProvider {

	public Object[] getElements(Object inputElement) {
		return (String[]) inputElement;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return false;
	}

	public Object[] getChildren(Object parentElement) {
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
