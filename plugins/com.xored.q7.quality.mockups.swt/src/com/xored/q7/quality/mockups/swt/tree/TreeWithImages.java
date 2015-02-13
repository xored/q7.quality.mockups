package com.xored.q7.quality.mockups.swt.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TreeWithImages extends BaseMockupPart {
	private static final List<Integer> swtImageCodes = Arrays.asList(SWT.ICON_ERROR,
			SWT.ICON_INFORMATION,
			SWT.ICON_QUESTION, SWT.ICON_WARNING, SWT.ICON_WORKING);

	static class ImageColumnLabelProvider extends ColumnLabelProvider {
		private boolean isEnabled = false;
		@Override
		public Image getImage(Object element) {
			if (isEnabled)
				return (Image) element;
			return super.getImage(element);
		}

		public void enable(boolean enable) {
			isEnabled = enable;
		}
	};

	void createColumn(Composite controls, final TreeViewer tree, String name) {
		TreeViewerColumn column = new TreeViewerColumn(tree, SWT.NONE);
		column.getColumn().setText(name);
		column.getColumn().setWidth(200);
		final ImageColumnLabelProvider labelProvider = new ImageColumnLabelProvider();
		column.setLabelProvider(labelProvider);
		final Button button = new Button(controls, SWT.CHECK);
		button.setText(name);
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				labelProvider.enable(button.getSelection());
				tree.refresh();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				labelProvider.enable(button.getSelection());
				tree.refresh();
			}
		});
	}

	@Override
	public Control construct(Composite parent) {
		List<Image> images = new ArrayList<Image>(swtImageCodes.size());
		for (int i : swtImageCodes) {
			Image image = parent.getDisplay().getSystemImage(i);
			if (image != null)
				images.add(image);
		}
		Group group = new Group(parent, SWT.NONE);
		group.setText("Column enablement");
		group.setLayout(new RowLayout());
		GridDataFactory grab = GridDataFactory.fillDefaults().grab(true, true);
		TreeViewer viewer = new TreeViewer(parent, SWT.NONE);
		grab.applyTo(viewer.getTree());
		viewer.getTree().setHeaderVisible(true);
		viewer.setContentProvider(TreeContentProvider.INSTANCE);
		createColumn(group, viewer, "First");
		createColumn(group, viewer, "Second");
		createColumn(group, viewer, "Third");
		viewer.setInput(images);
		return viewer.getControl();
	}

}
