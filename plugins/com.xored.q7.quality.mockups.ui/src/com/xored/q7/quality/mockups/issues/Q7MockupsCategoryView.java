package com.xored.q7.quality.mockups.issues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.part.ViewPart;

import com.xored.q7.quality.mockups.issues.PartManager.MockupPart;

public class Q7MockupsCategoryView extends ViewPart implements ISelectionProvider {

	public void createPartControl(Composite parent) {
		getSite().setSelectionProvider(this);

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parent);
		ScrolledForm form = toolkit.createScrolledForm(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(form);
		ToolBarManager manager = (ToolBarManager) form.getToolBarManager();
		toolkit.decorateFormHeading(form.getForm());
		IMenuService menuService = (IMenuService) getSite().getService(
				IMenuService.class);
		menuService.populateContributionManager(manager, "popup:formsToolBar");
		manager.update(true);

		form.setText("Q7 Quality Mockups");

		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(form.getBody());
		SashForm sash = new SashForm(form.getBody(), SWT.HORIZONTAL);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(sash);

		TreeViewer viewer = new TreeViewer(toolkit.createTree(sash, SWT.BORDER));
		viewer.getTree().setHeaderVisible(true);
		final Composite child = new Composite(sash, SWT.BORDER);
		sash.setWeights(new int[] { 30, 70 });

		List<MockupPart> parts = new PartManager().getParts();

		// viewer.setLabelProvider(new ColumnLabelProvider() {
		// @Override
		// public String getText(Object element) {
		// return super.getText(element);
		// }
		// });
		viewer.setContentProvider(new MockupPartContentProvider());

		TreeViewerColumn name = new TreeViewerColumn(viewer, SWT.NONE);
		name.getColumn().setWidth(300);
		name.getColumn().setText("Name");
		name.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((MockupPart) element).getName();
			}

			@Override
			public Image getImage(Object element) {
				ISharedImages images = PlatformUI.getWorkbench()
						.getSharedImages();
				if (((MockupPart) element).getPart() != null) {
					return images.getImage(ISharedImages.IMG_OBJ_ELEMENT);
				} else {
					return images.getImage(ISharedImages.IMG_OBJ_FOLDER);
				}
			}
		});

		TreeViewerColumn issue = new TreeViewerColumn(viewer, SWT.NONE);
		issue.getColumn().setText("Issue");
		issue.getColumn().setWidth(100);
		issue.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String issueValue = ((MockupPart) element).getIssue();
				if (issueValue != null) {
					return issueValue;
				}
				return "";
			}
		});
		viewer.setInput(parts);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				MockupPart element = (MockupPart) (((IStructuredSelection) event
						.getSelection()).getFirstElement());
				IQ7MockupPart part = element.getPart();
				if (part == null) {
					return;
				}
				Control[] children = child.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				GridLayoutFactory.fillDefaults().applyTo(child);

				Group s = new Group(child, SWT.BORDER);
				GridDataFactory.fillDefaults().grab(true, true).applyTo(s);
				GridLayoutFactory.swtDefaults().numColumns(1).applyTo(s);

				s.setText(element.getName());
				part.setView(Q7MockupsCategoryView.this);
				part.construct(s);
				s.layout();
				child.layout();
				child.getParent().layout();
			}
		});

		form.layout();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */

	public void setFocus() {
	}

	private List<ISelectionChangedListener> selectionChangeListeners = new ArrayList<ISelectionChangedListener>();
	private ISelection selection = null;

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangeListeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangeListeners.remove(listener);
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void setSelection(ISelection selection) {
		if (this.selection == selection)
			return;

		this.selection = selection;
		for (ISelectionChangedListener l : selectionChangeListeners)
			l.selectionChanged(new SelectionChangedEvent(this, selection));
	}
}
