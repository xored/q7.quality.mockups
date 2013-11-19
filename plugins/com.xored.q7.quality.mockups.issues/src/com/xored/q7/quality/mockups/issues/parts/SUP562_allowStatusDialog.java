package com.xored.q7.quality.mockups.issues.parts;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.ui.statushandlers.StatusManager;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SUP562_allowStatusDialog extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);

		final Button b = new Button(composite, SWT.PUSH);
		b.setText("Show Status Dialog");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProgressMonitorDialog pmd = new ProgressMonitorDialog(b.getShell());
				try {
					pmd.run(true, false, new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Copy resources", 100);
							for (int i = 0; i < 100; i++) {
								monitor.worked(1);
								Thread.sleep(20);
								if (i == 37) {
									StatusManager.getManager()
											.handle(
													new Status(Status.ERROR, "some", 7777, "Some bad happen",
															new Exception()),
													StatusManager.BLOCK);
								}
							}
						}
					});
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Button b2 = new Button(composite, SWT.PUSH);
		b2.setText("Click me");

		return null;
	}

	@Override
	public String getLabel() {
		return "SUP527 - Timer exec hung";
	}

}
