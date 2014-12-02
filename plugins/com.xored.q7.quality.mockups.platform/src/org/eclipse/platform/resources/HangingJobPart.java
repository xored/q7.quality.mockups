package org.eclipse.platform.resources;

import static com.xored.q7.quality.mockups.swt.internal.QualityPlatformPlugin.PLUGIN_ID;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.swt.internal.QualityPlatformPlugin;

/**
 * Emulates hanged job with specified parameters.
 * Developed for UIJobCollector testing.
 * */
public class HangingJobPart extends BaseMockupPart {

	interface Function<Argument, Return> {
		Return apply(Argument input);
	}

	private static final IStatus[] STATUSES = new IStatus[] {
			new Status(IStatus.OK, PLUGIN_ID, "Job OK"),
			new Status(IStatus.INFO, PLUGIN_ID, "Job info"),
			new Status(IStatus.WARNING, PLUGIN_ID, "Job warning"),
			new Status(IStatus.ERROR, PLUGIN_ID, "Job error")
	};

	private int count = 0;

	private final List<String> activeJobs = Collections.synchronizedList(new ArrayList<String>());

	private ListViewer activeJobsViewer;

	private static Object getSelected(final ComboViewer combo) {
		return ((IStructuredSelection) combo.getSelection()).getFirstElement();
	}

	@Override
	public Control construct(Composite parent) {
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(parent);

		Label label = new Label(parent, SWT.NONE);
		label.setText("Hang type:");
		final ComboViewer hangTypeCombo = createCombo(parent, asList(HangType.values()));

		label = new Label(parent, SWT.NONE);
		label.setText("Thread type:");
		final ComboViewer threadTypeCombo = createCombo(parent, asList(ThreadType.values()));

		label = new Label(parent, SWT.NONE);
		label.setText("Return status:");
		final ComboViewer statusCombo = createCombo(parent, asList(STATUSES));
		statusCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof IStatus)
					return ((IStatus) element).getMessage();
				return super.getText(element);
			}
		});

		label = new Label(parent, SWT.NONE);
		label.setText("Return method:");
		final ComboViewer returnPolicyCombo = createCombo(parent, asList(ReturnPolicy.values()));

		label = new Label(parent, SWT.NONE);
		label.setText("Hang time (ms):");
		final Spinner jobHangTimeoutSpinner = createSpinner(parent);

		label = new Label(parent, SWT.NONE);
		label.setText("Delay (ms):");
		final Spinner delaySpinner = createSpinner(parent);

		

		Button startNewJobButton = new Button(parent, SWT.NONE);
		startNewJobButton.setText("Start New Job!");
		startNewJobButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				HangType hang = (HangType) getSelected(hangTypeCombo);
				ThreadType thread = (ThreadType) getSelected(threadTypeCombo);
				IStatus status = (IStatus) getSelected(statusCombo);
				ReturnPolicy returnPolicy = (ReturnPolicy) getSelected(returnPolicyCombo);
				startNewJob(hang, delaySpinner.getSelection(), jobHangTimeoutSpinner.getSelection(), thread, status,
						returnPolicy);
			}

		});

		Button terminateAllJobsButton = new Button(parent, SWT.NONE);
		terminateAllJobsButton.setText("Terminate All");
		terminateAllJobsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				terminateAll();
			}
		});

		activeJobsViewer = new ListViewer(parent);
		activeJobsViewer.setContentProvider(new ArrayContentProvider());
		activeJobsViewer.setLabelProvider(new LabelProvider());
		activeJobsViewer.setInput(activeJobs);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, true).applyTo(activeJobsViewer.getControl());
		return parent;
	}

	private Spinner createSpinner(final Composite container) {
		final Spinner spinner = new Spinner(container, SWT.BORDER);
		spinner.setMinimum(0);
		spinner.setMaximum(180000);
		spinner.setSelection(1000);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(spinner);
		return spinner;
	}

	private static ComboViewer createCombo(final Composite container, List<?> values) {
		ComboViewer combo = new ComboViewer(container);
		combo.setContentProvider(new ArrayContentProvider());
		combo.setLabelProvider(new LabelProvider());
		combo.setInput(values);
		combo.setSelection(new StructuredSelection(values.get(0)));
		GridDataFactory.fillDefaults().grab(true, false).applyTo(combo.getControl());
		return combo;
	}

	private Runnable activeJobsRefreshRunnable = new Runnable() {
		@Override
		public void run() {
			activeJobsViewer.refresh(true);
			while (activeJobsViewer.getControl().getDisplay().readAndDispatch())
				;
			activeJobsViewer.getControl().redraw();
		}
	};

	private void startNewJob(final HangType hangType, int delay, final int timeout, ThreadType thread,
			final IStatus rv, final ReturnPolicy returnPolicy) {
		final String name;
		synchronized (this) {
			name = "Wait type: " + hangType + ", threading: " + thread + ", return: " + rv.getMessage() + ", delay "
					+ delay + ", time: " + timeout + ", instance:" + count++;
		}
		thread.start(delay, new Function<IProgressMonitor, IStatus>() {
			@Override
			public IStatus apply(IProgressMonitor input) {
				try {
					activeJobs.add(name);
					refresh();
					hangType.hang(input, timeout);
				} catch (InterruptedException e) {
					return new Status(IStatus.WARNING, PLUGIN_ID, "Interrupted");
				} finally {
					activeJobs.remove(name);
					refresh();
				}
				return returnPolicy.processReturnValue(rv);
			}

			private void refresh() {
				Display.getDefault().syncExec(activeJobsRefreshRunnable);
			}
		});
	}

	private void terminateAll() {
		Job.getJobManager().cancel(ThreadType.jobGroup);
	}

	private enum ThreadType {
		DIRECT {
			@Override
			void start(int delay, Function<IProgressMonitor, IStatus> method) {
				toRunnable(method).run();
			}
		},
		SYNC_DISPLAY {
			@Override
			void start(int delay, final Function<IProgressMonitor, IStatus> method) {
				Display.getDefault().syncExec(toRunnable(method));
			}
		},

		ASYNC_DISPLAY {
			@Override
			void start(int delay, Function<IProgressMonitor, IStatus> method) {
				Display.getDefault().asyncExec(toRunnable(method));
			}
		},
		TIMER_EXEC {
			@Override
			void start(int delay, Function<IProgressMonitor, IStatus> method) {
				Display.getDefault().timerExec(delay, toRunnable(method));
			}
		},
		JOB {
			@Override
			void start(int delay, final Function<IProgressMonitor, IStatus> method) {
				Job job = new Job(method.getClass().getName()) {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						return method.apply(monitor);
					}
				};
				job.schedule(delay);
			}
		};
		abstract void start(int delay, Function<IProgressMonitor, IStatus> method);

		private final static Object jobGroup = new Object();
	}

	private static Runnable toRunnable(final Function<IProgressMonitor, IStatus> method) {
		return new Runnable() {

			@Override
			public void run() {
				final IStatus status = method.apply(new NullProgressMonitor());
				if (!status.isOK()) {
					throw new RuntimeException(status.getMessage());
				}
			}
		};
	}

	private enum ReturnPolicy {
		RETURN
		{
			@Override
			IStatus processReturnValue(IStatus status) {
				return status;
			}
		},
		LOG {
			@Override
			IStatus processReturnValue(IStatus status) {
				QualityPlatformPlugin.getDefault().getLog().log(status);
				return Status.OK_STATUS;
			}
		},
		THROW {
			@Override
			IStatus processReturnValue(IStatus status) {
				throw new RuntimeException(new CoreException(status));
			}
		};
		abstract IStatus processReturnValue(IStatus status);
	}
	private enum HangType {
		LOOP {
			@Override
			void hang(IProgressMonitor monitor, int timeout) {
				Date startTime = new Date();
				while (!monitor.isCanceled()) {
					Date currentTime = new Date();
					if (currentTime.getTime() - startTime.getTime() >= timeout) {
						break;
					}
				}
			}
		},
		THREAD_SLEEP {
			@Override
			void hang(IProgressMonitor monitor, int timeout) throws InterruptedException {
				Thread.sleep(timeout);
			}
		},
		OBJECT_WAIT {
			@Override
			void hang(IProgressMonitor monitor, int timeout) throws InterruptedException {
				Object object = new Object();
				synchronized (object) {
					object.wait(timeout);
				}
			}
		};
		abstract void hang(IProgressMonitor monitor, int timeout) throws InterruptedException;
	}
}
