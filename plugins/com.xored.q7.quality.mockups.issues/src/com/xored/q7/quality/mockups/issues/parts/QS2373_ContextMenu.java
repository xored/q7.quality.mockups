package com.xored.q7.quality.mockups.issues.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeCategory;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeItem;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeViewerModel;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.CategoryProvider;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.TreeLabelProvider;

public class QS2373_ContextMenu extends BaseMockupPart {

	private List<Action> actionsToShow;

	private List<Action> actionsShowed;

	private MenuManager mManager = new MenuManager("context_menu");

	@Override
	public Control construct(Composite parent) {
		initActions();
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		final TreeViewer viewer = new TreeViewer(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getTree());
		viewer.setContentProvider(new CategoryProvider());
		viewer.setLabelProvider(new TreeLabelProvider());
		viewer.setAutoExpandLevel(2);
		viewer.setInput(new TreeViewerModel());

		mManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		mManager.setRemoveAllWhenShown(true);
		mManager.addMenuListener(new IMenuListener2() {
			public void menuAboutToShow(IMenuManager manager) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (!selection.isEmpty()) {
					Object item = selection.getFirstElement();
					String actionName;
					if (item instanceof TreeItem) {
						actionName = "Action for item: "
								+ ((TreeItem) item).getName();
					} else {
						actionName = "Action for category: "
								+ ((TreeCategory) item).getName();
					}
					Action action = new Action(actionName) {
						@Override
						public void run() {
							System.out.println("\"" + this.getText()
									+ "\" command executed.");
						}
					};
					mManager.add(action);
				}

				MenuManager subMenu = new MenuManager("Sub-menu", null);

				// This is an emulation of eclipse 4.2 behavior
				// also @see menuAboutToHide method
				for (Action action : actionsToShow) {
					subMenu.add(action);
				}
				actionsShowed.addAll(actionsToShow);
				actionsToShow.clear();

				mManager.add(subMenu);
			}

			public void menuAboutToHide(IMenuManager manager) {
				actionsToShow.addAll(actionsShowed);
				actionsShowed.clear();
			}
		});

		viewer.getControl().setMenu(
				mManager.createContextMenu(viewer.getControl()));
		return parent;
	}

	@Override
	public String getLabel() {
		return "QS2373, Context Menu";
	}

	private void initActions() {
		actionsToShow = new ArrayList<Action>();
		actionsShowed = new ArrayList<Action>();

		Action a = new Action("") {
			@Override
			public void run() {
				System.out.println("Say hello command executed.");
			}
		};
		a.setText("Say hello");
		actionsToShow.add(a);
	}
}
