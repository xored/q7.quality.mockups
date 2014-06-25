package org.eclipse.platform.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.swt.internal.QualityPlatformPlugin;

/**
 * Emulates hanged job with specified parameters.
 * Developed for UIJobCollector testing.
 * */
public class HangingJobPart extends BaseMockupPart {

	private Label jobsStartedLabel;
	private Combo hangTypeCombo;
	private Combo hangThreadCombo;
	private Spinner jobHangTimeoutSpinner;
	private List<Job> startedJobs = new ArrayList<Job>();

	@Override
	public Control construct(Composite parent) {
		GridLayoutFactory.swtDefaults().applyTo(parent);
		final Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(container);

		Label hangTypeLabel = new Label(container, SWT.NONE);
		hangTypeLabel.setText("Hang Type:");
		hangTypeCombo = new Combo(container, SWT.NONE);
		hangTypeCombo.setItems(new String[] { "Infinite loop", "Thread.sleep()", "Object.wait()" });
		hangTypeCombo.select(0);

		Label jobHangTimeoutLabel = new Label(container, SWT.NONE);
		jobHangTimeoutLabel.setText("Hang Timeout (ms):");
		jobHangTimeoutSpinner = new Spinner(container, SWT.BORDER);
		jobHangTimeoutSpinner.setMinimum(0);
		jobHangTimeoutSpinner.setMaximum(180000);
		jobHangTimeoutSpinner.setSelection(10000);

		Label jobHangThreadLabel = new Label(container, SWT.NONE);
		jobHangThreadLabel.setText("Hang Type:");
		hangThreadCombo = new Combo(container, SWT.NONE);
		hangThreadCombo.setItems(new String[] { "Display", "Schedule" });
		hangThreadCombo.select(0);

		Label jobsStartedTextLabel = new Label(container, SWT.NONE);
		jobsStartedTextLabel.setText("Jobs started:");
		jobsStartedLabel = new Label(container, SWT.NONE);
		jobsStartedLabel.setText("0");

		Button startNewJobButton = new Button(container, SWT.NONE);
		startNewJobButton.setText("Start New Job!");
		startNewJobButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				startNewJob();
			}
		});

		Button terminateAllJobsButton = new Button(container, SWT.NONE);
		terminateAllJobsButton.setText("Terminate All");
		terminateAllJobsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				terminateAll();
			}
		});

		return container;
	}

	private void startNewJob() {
		HangType hangType;
		switch (hangTypeCombo.getSelectionIndex()) {
		case 0:
		default:
			hangType = HangType.INFINITE_LOOP;
			break;
		case 1:
			hangType = HangType.THREAD_SLEEP;
			break;
		case 2:
			hangType = HangType.OBJECT_WAIT;
			break;
		}

		final JobHangBehaviour hangBehaviour = new JobHangBehaviour(jobHangTimeoutSpinner.getSelection(), hangType);

		if (hangThreadCombo.getSelectionIndex() == 0) {
			try {
				hangBehaviour.hang(new NullProgressMonitor());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			Job job = new Job("Another Hanging Job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						hangBehaviour.hang(monitor);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return new Status(Status.ERROR, QualityPlatformPlugin.PLUGIN_ID, "Failed to hang!", e);
					}
					return Status.OK_STATUS;
				}
			};

			job.addJobChangeListener(new IJobChangeListener() {
				@Override
				public void done(IJobChangeEvent event) {
					if (startedJobs.contains(event.getJob())) {
						startedJobs.remove(event.getJob());
					}
					updateStartedJobs();
				}

				@Override
				public void sleeping(IJobChangeEvent event) {
				}

				@Override
				public void scheduled(IJobChangeEvent event) {
				}

				@Override
				public void running(IJobChangeEvent event) {
				}

				@Override
				public void awake(IJobChangeEvent event) {
				}

				@Override
				public void aboutToRun(IJobChangeEvent event) {
				}
			});

			startedJobs.add(job);
			job.schedule();
		}
		updateStartedJobs();
	}

	private void terminateAll() {
		for (Job job : startedJobs) {
			job.cancel();
		}
	}

	private void updateStartedJobs() {
		jobsStartedLabel.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				jobsStartedLabel.setText(String.valueOf(startedJobs.size()));
			}

		});
	}

	@Override
	public String getLabel() {
		return "Hanging Jobs";
	}

	private static class JobHangBehaviour {
		private int timeoutMs;
		private HangType hangType;

		public JobHangBehaviour(int timeoutMs, HangType hangType) {
			this.timeoutMs = timeoutMs;
			this.hangType = hangType;
		}

		public void hang(IProgressMonitor monitor) throws InterruptedException {
			switch (hangType) {
			case INFINITE_LOOP:
				hangLoop(monitor);
				break;
			case OBJECT_WAIT:
				this.wait(timeoutMs);
				break;
			case THREAD_SLEEP:
				Thread.sleep(timeoutMs);
				break;
			default:
				break;

			}
		}

		private void hangLoop(IProgressMonitor monitor) {
			Date startTime = new Date();

			while (!monitor.isCanceled()) {
				Date currentTime = new Date();
				if (currentTime.getTime() - startTime.getTime() >= timeoutMs) {
					break;
				}
			}
		}
	}

	private enum HangType {
		INFINITE_LOOP,
		THREAD_SLEEP,
		OBJECT_WAIT
	}
}
