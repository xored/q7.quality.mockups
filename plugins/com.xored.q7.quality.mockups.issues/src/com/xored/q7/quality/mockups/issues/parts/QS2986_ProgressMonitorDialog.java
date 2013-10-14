package com.xored.q7.quality.mockups.issues.parts;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2986_ProgressMonitorDialog extends BaseMockupPart {

	private Composite parent;

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		this.parent = composite;
		final Button b = new Button(composite, SWT.PUSH);
		b.setText("Press me");
		b.setEnabled(false);
		
		final Button b2 = new Button(composite, SWT.PUSH);
		b2.setText("Press me");
		b2.setEnabled(false);
		
		Button showDialog = new Button(composite, SWT.PUSH);
		showDialog.setText("Show dialog");
		showDialog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new ProgressMonitorDialog(QS2986_ProgressMonitorDialog.this.parent.getShell()).run(true, false,
							new IRunnableWithProgress() {
								@Override
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									Thread.sleep(2000);
									b.getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											b.setEnabled(true);
										}
									});
								}
							});
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		Button showDialog2 = new Button(composite, SWT.PUSH);
		showDialog2.setText("Show dialog");
		showDialog2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new ProgressMonitorDialog(QS2986_ProgressMonitorDialog.this.parent.getShell()).run(false, false,
							new IRunnableWithProgress() {
								@Override
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									Thread.sleep(2000);
									b2.getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											b2.setEnabled(true);
										}
									});
								}
							});
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		return null;

	}

	@Override
	public String getLabel() {
		return "QS2986 - ProgressMonitorDialog";
	}

}
