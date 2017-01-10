package com.xored.q7.quality.mockups.jobs;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class JobAndAsyncMix extends BaseMockupPart {

	private boolean user = false;
	private int busywait = 1000;

	@Override
	public Control construct(Composite parent) {
		Composite grid = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().grab(true, false).applyTo(grid);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(grid);
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		toolkit.createLabel(grid, "Busy wait");
		final Text busyWaitText = toolkit.createText(grid, "1000", SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(busyWaitText);
		GridDataFactory spandata = GridDataFactory.swtDefaults().span(2, 1);
		final Button userCheckBox = new Button(grid, SWT.CHECK);
		userCheckBox.setText("User job");
		spandata.applyTo(userCheckBox);
		Button button = new Button(parent, SWT.NONE);
		button.setText("Run");
		spandata.applyTo(button);

		Runnable execute = new Runnable() {
			@Override
			public void run() {
				user = userCheckBox.getSelection();
				busywait = Integer.parseInt(busyWaitText.getText());
				execute();
			}
		};

		button.addSelectionListener(toListener(execute));
		parent.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}
		});
		return null;
	}

	private final AtomicInteger count = new AtomicInteger(1);

	private final Runnable async(final Runnable runnable) {
		return new Runnable() {
			@Override
			public void run() {
				getShell().getDisplay().asyncExec(runnable);
			}

		};
	}

	private Shell getShell() {
		return view.getSite().getWorkbenchWindow().getShell();
	}

	private final Runnable job(final Runnable runnable) {
		return new Runnable() {
			@Override
			public void run() {
				new Job("Job " + count.incrementAndGet()) {
					{
						setUser(user);
					}
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						long stop = System.currentTimeMillis() + busywait;
						while (stop > System.currentTimeMillis())
							;
						runnable.run();
						return Status.OK_STATUS;
					}

				}.schedule();
			}
		};
	}

	private final Runnable dialog(final String text) {
		return new Runnable() {
			@Override
			public void run() {
				MessageDialog.openInformation(getShell(), "Async dialog", text);
			}
		};
	}

	private static SelectionListener toListener(final Runnable runnable) {
		return new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				runnable.run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				runnable.run();
			}
		};
	}

	private void dispose() {
		
	}

	private void execute() {
		Runnable runnable;
		runnable = dialog("Job complete");
		runnable = async(runnable);
		runnable = job(runnable);
		runnable = async(runnable);
		runnable = async(runnable);
		runnable = job(runnable);
		runnable.run();
	}

}
