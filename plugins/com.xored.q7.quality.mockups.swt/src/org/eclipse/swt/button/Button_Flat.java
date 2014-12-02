package org.eclipse.swt.button;

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

public class Button_Flat extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		final Text t = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.CENTER);

		Button b1 = new Button(composite, SWT.FLAT);
		b1.setText("Flat1");

		b1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				t.setText("Flat1 pressed...");
				t.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
			}
		});

		Button b2 = new Button(composite, SWT.FLAT);
		b2.setText("Flat2");

		b2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				t.setText("Flat2 pressed...");
				t.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
				t.redraw();
			}
		});

		Button b3 = new Button(composite, SWT.FLAT);
		b3.setText("Flat3");

		b3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				t.setText("Flat3 pressed...");
				t.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
			}
		});

		Button b4 = new Button(composite, SWT.FLAT);
		b4.setText("Flat4");
		b4.setEnabled(false);

		Button b5 = new Button(composite, SWT.FLAT);
		b5.setToolTipText("Button 5");

		Button b6 = new Button(composite, SWT.FLAT);

		return null;
	}

}
