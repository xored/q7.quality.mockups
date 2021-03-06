package org.eclipse.swt.dialogs;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class NestedDialog extends BaseMockupPart {

	private final Runnable noop = new Runnable() {
		@Override
		public void run() {
		}
	};
	
	class Builder implements Runnable {
		Builder question(final String title) {
			final Runnable temp = this;
			return new Builder() { 
				@Override
				public void run() {
					boolean result = MessageDialog
							.openQuestion(getShell(), title, "Do you wish to proceed?");
					if (result)
						temp.run();
				}
			};
		}

		Builder wizard(final String title) {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					Wizard w = new Wizard() {
						{
							setWindowTitle(title);
						}

						@Override
						public void addPages() {
							addPage(new WizardPage("Dummy page") {

								@Override
								public void createControl(Composite parent) {
									setControl(new Label(parent, SWT.None));
								}
							});
						}

						@Override
						public boolean performFinish() {
							temp.run();
							return true;
						}
					};
					new WizardDialog(getShell(), w).open();
				}
			};
		}

		Builder workspace() {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					try {
						ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
							@Override
							public void run(IProgressMonitor monitor) throws CoreException {
								temp.run();
							}
						}, new NullProgressMonitor());
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				}
			};
		}

		Builder workspaceJob() {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					new WorkspaceJob("meh") {
						
						@Override
						public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
							temp.run();
							return Status.OK_STATUS;
						}
					}.schedule();
				}
			};
		}

		Builder async() {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					getShell().getDisplay().asyncExec(temp);
				}
			};
		}

		Builder sync() {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					getShell().getDisplay().syncExec(temp);
				}
			};
		}

		Builder asyncDelayed(final Display display) {
			final Runnable temp = this;
			return new Builder() {
				@Override
				public void run() {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							display.asyncExec(temp);
						}
					}).start();
				}
			};
		}

		@Override
		public void run() {
		}
	}

	void createButton(Composite parent, String text, final Runnable action) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(text);
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				action.run();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	@Override
	public Control construct(Composite parent) {
		createButton(parent, "Simple wizard with confirmation",
				new Builder().question("Question").wizard("Simple wizard"));
		createButton(parent, "Simple wizard with workspace confirmation", new Builder().question("Workspace question")
				.workspace().wizard("Workspace wizard"));
		createButton(parent, "Simple wizard with async workspace confirmation",
				new Builder().question("Workspace question")
						.workspace().async().wizard("Workspace wizard"));
		createButton(parent, "Simple wizard with sync workspace confirmation",
				new Builder().question("Workspace question")
						.workspace().sync().wizard("Workspace wizard"));
		createButton(parent, "Simple wizard with double workspace confirmation",
				new Builder().question("Workspace question2").question("Workspace question")
						.workspace().wizard("Workspace wizard"));
		createButton(parent, "Simple wizard with sync WorkspaceJob confirmation",
				new Builder().question("Workspace question")
						.sync().workspaceJob().wizard("Workspace wizard"));
		createButton(parent, "Simple wizard with delayed async workspace confirmation",
				new Builder().question("Workspace question")
						.workspace().asyncDelayed(Display.getCurrent()).wizard("Workspace wizard"));
		return null;
	}

	private Shell getShell() {
		return view.getSite().getShell();
	}

}
