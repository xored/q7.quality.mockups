package org.eclipse.platform.resources;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class DelayedBuildsPart extends BaseMockupPart {

	public String getLabel() {
		return "Invoke AutoBuild with timeout";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);
		final Composite client = new Composite(section, SWT.NONE);

		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);

		doABuild(client, "Build with delay by timer", new Runnable() {

			public void run() {
				client.getDisplay().timerExec(10000, new Runnable() {

					public void run() {
						doAJob();
					}
				});
			}
		});
		doABuild(client, "Build with delay by Thread", new Runnable() {

			public void run() {
				Thread t = new Thread(new Runnable() {

					public void run() {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						doAJob();
					}
				});
				t.start();
			}
		});
		return client;
	}

	private void doAJob() {
		final Object lock = new Object();
		final Thread t = new Thread(new Runnable() {

			public void run() {
				int ticks = 180;
				while (ticks > 0) {
					ticks--;
					synchronized (lock) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		t.start();
		Job j = new Job("Locking job") {

			public boolean belongsTo(Object family) {
				return ResourcesPlugin.FAMILY_AUTO_BUILD.equals(family);
			}

			protected IStatus run(IProgressMonitor monitor) {
				while (t.isAlive()) {
					synchronized (lock) {
						System.out.println("#");
					}
				}
				return Status.OK_STATUS;
			}
		};
		j.schedule();
	}

	private void doABuild(Composite client, String msg, final Runnable runnable) {
		Button wiaJob = new Button(client, SWT.PUSH);
		wiaJob.setText(msg);
		wiaJob.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				runnable.run();
			}
		});
	}
}
