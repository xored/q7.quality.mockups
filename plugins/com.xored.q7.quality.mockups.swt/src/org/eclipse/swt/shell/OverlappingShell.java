package org.eclipse.swt.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class OverlappingShell extends BaseMockupPart {

	private Shell shell = null;
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
		
		return null;
	}

	protected void closeShell() {
		if (this.shell != null) {
			this.shell.dispose();
			this.shell = null;
		}
	}

	protected void createShell(Shell origin) {
		closeShell();
		Shell newShell = new Shell(origin.getDisplay());
		newShell.setText("New shell");
		newShell.setMaximized(true);
		this.shell = newShell; 
		shell.open();
	}
}
