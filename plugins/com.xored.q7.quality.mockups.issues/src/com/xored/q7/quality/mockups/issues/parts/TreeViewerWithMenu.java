package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeItem;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeViewerModel;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.CategoryProvider;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.TreeLabelProvider;

public class TreeViewerWithMenu extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		final TreeViewer viewer = new TreeViewer(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getTree());
		viewer.setContentProvider(new CategoryProvider());
		viewer.setLabelProvider(new TreeLabelProvider());
		viewer.setAutoExpandLevel(2);
		viewer.setInput(new TreeViewerModel());

		final MenuManager m = new MenuManager();
		m.setRemoveAllWhenShown(true);

		final Action a1 = new Action("Action1") {
			@Override
			public void run() {
				MessageDialog.openInformation(null, "Action1", "Action1 called");
			}
		};
		final Action a2 = new Action("Action2") {
			@Override
			public void run() {
				MessageDialog.openInformation(null, "Action2", "Action2 called");
			}
		};
		m.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				IStructuredSelection s = (IStructuredSelection) viewer.getSelection();
				if (s.isEmpty()) {
					return;
				}
				Object itemObject = s.getFirstElement();
				if (!(itemObject instanceof TreeItem)) {
					return;
				}

				m.add(a1);
				m.add(a2);
			}
		});
		viewer.getControl().setMenu(m.createContextMenu(viewer.getControl()));
		return parent;
	}

}
