package com.xored.q7.quality.mockups.issues;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xored.q7.quality.mockups.issues.PartManager.MockupPart;

public class MockupPartContentProvider implements ITreeContentProvider {

	private Object[] empty = new Object[0];

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("rawtypes")
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			return ((List) inputElement).toArray();
		}
		return empty;
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MockupPart
				&& ((MockupPart) parentElement).getChildren() != null) {
			return ((MockupPart) parentElement).getChildren().toArray();
		}
		return empty;
	}

	public Object getParent(Object element) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public boolean hasChildren(Object element) {
		if (element instanceof List) {
			return !((List) element).isEmpty();
		}
		return element instanceof MockupPart
				&& ((MockupPart) element).getChildren() != null
				&& !((MockupPart) element).getChildren().isEmpty();
	}
}
