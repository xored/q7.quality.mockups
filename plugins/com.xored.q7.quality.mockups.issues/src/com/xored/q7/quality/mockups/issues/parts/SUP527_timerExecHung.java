package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SUP527_timerExecHung extends BaseMockupPart {

	private final class LocalRunnable implements Runnable {
		private final boolean[] debug_terminate;
		private final Text t;
		private final Button b;
		private boolean alwaysNew;

		private LocalRunnable(boolean[] debug_terminate, Text t, Button b, boolean alwaysNew) {
			this.debug_terminate = debug_terminate;
			this.t = t;
			this.b = b;
			this.alwaysNew = alwaysNew;
		}

		@Override
		public void run() {
			// debug_terminate[0] = true;
			if (debug_terminate[0]) {
				return;
			}
			if (!b.isDisposed()) {
				t.setText("value:" + System.currentTimeMillis());
				if (alwaysNew) {
					b.getDisplay().timerExec(150, new LocalRunnable(debug_terminate, t, b, alwaysNew));
				}
				else {
					b.getDisplay().timerExec(150, this);
				}
				// System.out.println("Running timer exec");
			}
			else {
				System.out.println("Disposed.");
			}
		}
	}

	private Composite parent;

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		this.parent = composite;
		final Text t = new Text(this.parent, SWT.BORDER);

		final Button b = new Button(this.parent, SWT.PUSH);
		b.setText("run new exec");
		final boolean[] debug_terminate = new boolean[] { false };
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				b.getDisplay().timerExec(50, new LocalRunnable(debug_terminate, t, b, true));
			}
		});

		final Button b3 = new Button(this.parent, SWT.PUSH);
		b3.setText("run same exec");
		b3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				b.getDisplay().timerExec(50, new LocalRunnable(debug_terminate, t, b, false));
			}
		});

		final Button b2 = new Button(this.parent, SWT.PUSH);
		b2.setText("Change label");
		b2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				t.setText("This is cool value");
			}
		});
		return null;

	}

	@Override
	public String getLabel() {
		return "SUP527 - Timer exec hung";
	}

}
