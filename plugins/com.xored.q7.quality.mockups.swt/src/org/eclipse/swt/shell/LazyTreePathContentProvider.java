package org.eclipse.swt.shell;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.ILazyTreePathContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;

public final class LazyTreePathContentProvider implements ILazyTreePathContentProvider {
	private List<SampleTreeNode> input = Collections.emptyList();
	private Optional<TreeViewer> viewerOptional = Optional.empty();

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewerOptional = Optional.ofNullable((TreeViewer) viewer);
		input = Optional.ofNullable((List<?>)newInput)
			.orElse(Collections.emptyList())
			.stream()
			.filter(SampleTreeNode.class::isInstance)
			.map(SampleTreeNode.class::cast)
			.collect(Collectors.toList());
	}

	@Override
	public void updateElement(TreePath parentPath, int index) {
		viewerOptional.ifPresent(viewer -> {
			List<SampleTreeNode> children = getChildren(parentPath);
			if (index < children.size()) {
				SampleTreeNode child = children.get(index);
				viewer.replace(parentPath, index, child);
				viewer.setChildCount(parentPath.createChildPath(child), child.children.size());
			}
		});
	}

	@Override
	public void updateChildCount(TreePath treePath, int currentChildCount) {
		viewerOptional.ifPresent(viewer -> {
			int count = getChildren(treePath).size();
			viewer.setChildCount(treePath, count);
		});
	}

	@Override
	public void updateHasChildren(TreePath path) {
		viewerOptional.ifPresent(viewer -> {
			int count = getChildren(path).size();
			viewer.setHasChildren(path, count > 0);
		});
	}

	@Override
	public TreePath[] getParents(Object element) {
		if (element instanceof SampleTreeNode) {
			return new TreePath[]{getParentPath((SampleTreeNode) element)};
		} 
		return new TreePath[]{TreePath.EMPTY};
	}
	
	private static TreePath getParentPath(SampleTreeNode node) {
		return node.getParent()
				.map(p -> getParentPath(p).createChildPath(p))
				.orElse(TreePath.EMPTY);
	}

	private List<SampleTreeNode> getChildren(TreePath path) {
		if (path.getSegmentCount() == 0) {
			return input;
		}
		return ((SampleTreeNode) path.getLastSegment()).children;
	}

}
