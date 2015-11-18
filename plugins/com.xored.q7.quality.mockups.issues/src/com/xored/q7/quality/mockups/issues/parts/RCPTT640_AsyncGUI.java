package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/**
 * A {@link Button} that disposes of and recreates itself asynchronously on the UI thread.
 */
public class RCPTT640_AsyncGUI extends BaseMockupPart {

	private Composite composite;

	@Override
	public Control construct(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		update("Do not click");
		return null;
	}

	private void update(final String text) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				for (Control control : composite.getChildren()) {
					control.dispose();
				}
				Button button = new Button(composite, SWT.NONE);
				button.setText(text);
				button.addSelectionListener(buttonSelectionListener);
				composite.layout(true);
			}
		});
	}

	private SelectionListener buttonSelectionListener = new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			update("You clicked!");
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			update("You clicked!");
		}
	};

}
