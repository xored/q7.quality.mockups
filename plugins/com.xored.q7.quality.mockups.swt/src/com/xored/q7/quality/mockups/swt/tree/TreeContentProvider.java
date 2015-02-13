package com.xored.q7.quality.mockups.swt.tree;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

class TreeContentProvider extends ArrayContentProvider implements ITreeContentProvider {
	static public final TreeContentProvider INSTANCE = new TreeContentProvider();
	private static final Object[] EMPTY = new Object[0];

	@Override
	public Object[] getChildren(Object parentElement) {
		return EMPTY;
	}
	@Override
	public Object getParent(Object element) {
		return null;
	}
	@Override
	public boolean hasChildren(Object element) {
		return false;
	}
}