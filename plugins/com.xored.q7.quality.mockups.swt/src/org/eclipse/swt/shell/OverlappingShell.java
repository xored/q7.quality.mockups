package org.eclipse.swt.shell;

import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.UIJob;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;

public class OverlappingShell extends BaseMockupPart {

	private Shell shell = null;
	private TreeViewer viewer = null;

	@Override
	public Control construct(final Composite parent) {
		Button openButton = new Button(parent, SWT.NONE);
		openButton.setText("Create shell");
		openButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createShell(parent.getShell());
			}
		});
		Button closeButton = new Button(parent, SWT.NONE);
		closeButton.setText("Close shell");
		closeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				closeShell();
			}
		});
		closeButton.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				closeShell();
			}
		});
		viewer = new TreeViewer(parent, SWT.FULL_SELECTION|SWT.VIRTUAL);
		viewer.setUseHashlookup(true);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(viewer.getTree());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setContentProvider(new LazyTreePathContentProvider(operation -> {
			new UIJob("updating") {
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					operation.run();
					return Status.OK_STATUS;
				}
			}.schedule(50);
		}));
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.FILL);
		column.getColumn().setWidth(100);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((SampleTreeNode)element).column1;
			}
		});
		column = new TreeViewerColumn(viewer, SWT.FILL);
		column.getColumn().setWidth(100);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((SampleTreeNode)element).column2;
			}
		});
		viewer.setInput(Collections.emptyList());
		return null;
	}

	protected void closeShell() {
		if (this.shell != null) {
			this.shell.dispose();
			this.shell = null;
		}
		viewer.setInput(Collections.emptyList());
	}

	protected void createShell(Shell origin) {
		closeShell();
		Shell newShell = new Shell(origin.getDisplay());
		newShell.setText("New shell");
		newShell.setMaximized(true);
		this.shell = newShell;
		shell.open();
		viewer.setInput(SampleTreeNode.createSample());
		// new Job("Updating tree content") {
		// @Override
		// protected IStatus run(IProgressMonitor monitor) {
		// shell.getDisplay().asyncExec(new Runnable() {
		//
		// @Override
		// public void run() {
		// updateTreeContent();
		// }
		// });
		// return Status.OK_STATUS;
		// }
		// }.schedule();
	}

	// private void updateTreeContent() {
	// viewer.setInput(SampleTreeNode.createSample());
	// }
}
