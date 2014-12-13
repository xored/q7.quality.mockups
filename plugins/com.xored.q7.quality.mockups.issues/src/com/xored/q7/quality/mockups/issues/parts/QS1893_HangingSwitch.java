package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS1893_HangingSwitch extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(content);
		log = new Text(content, SWT.MULTI);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(log);
		pollMessagesJob.schedule();
		return content;
	}

	private Text log;
	private Job pollMessagesJob = new Job("Server log") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// because fork you
			}

			if (!monitor.isCanceled()) {
				rescheduleJob();
			}
			// Display.getDefault().timerExec(400, refreshTextAsync);
			return Status.OK_STATUS;
		}
	};

	private void rescheduleJob() {
		pollMessagesJob.schedule(400);
	}

}
