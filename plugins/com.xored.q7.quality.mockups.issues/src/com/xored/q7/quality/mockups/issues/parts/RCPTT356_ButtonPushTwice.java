package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT356_ButtonPushTwice extends BaseMockupPart {

	private Composite composite = null;
	private Text text = null;
	private Button btnFinish = null;

	@Override
	public Control construct(Composite parent) {
		createComposit(parent);
		addRadioButtons(composite);
		addTextField(composite);
		return null;
	}

	private void addTextField(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void addRadioButtons(Composite composite) {
		btnFinish = new Button(composite, SWT.PUSH);
		btnFinish.setText("Finish");
		btnFinish.addSelectionListener(new ClickListener1());
		btnFinish.addSelectionListener(new ClickListener2());
	}

	private void createComposit(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
	}

	private class ClickListener1 extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			log("Button click listener 1 ." + e.getSource());
		}
	}

	private class ClickListener2 extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			log("Button click listener 2 ." + e.getSource());
			SelectionAdapter myDialog = new MyDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			myDialog.widgetSelected(e);
		}
	}

	public class MyDialog extends SelectionAdapter {
		private final Shell shell;

		public MyDialog(Shell shell) {
			this.shell = shell;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			log("Button pressed, about to open modal dialog");
			final Shell dialogShell = new Shell(shell, SWT.PRIMARY_MODAL | SWT.SHEET);
			dialogShell.setLayout(new FillLayout());
			Button closeButton = new Button(dialogShell, SWT.PUSH);
			closeButton.setText("Close");
			closeButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					dialogShell.dispose();
				}
			});
			dialogShell.setDefaultButton(closeButton);
			dialogShell.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					log("Modal dialog closed");
				}
			});
			dialogShell.pack();
			dialogShell.open();
		}
	}

	public void refresh() {
		composite.redraw();
	}

	public void log(String string) {
		text.setText(text.getText() + string + "\n");
	}
}
