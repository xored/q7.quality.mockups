package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Toggle extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		final Group group = new Group(composite, SWT.NONE);
		group.setText("Channel 1");
		group.setLayout(new FillLayout());

		Listener changeChannelListener = new Listener() {
			public void handleEvent(Event e) {
				Control[] children = group.getChildren();
				for (int i = 0; i < children.length; i++) {
					Control child = children[i];
					if (e.widget != child && child instanceof Button
							&& (child.getStyle() & SWT.TOGGLE) != 0) {
						((Button) child).setSelection(false);
					}
				}
				((Button) e.widget).setSelection(true);
				group.setText("Channel " + ((Button) e.widget).getText());
			}
		};

		for (int i = 0; i < 5; i++) {
			Button button = new Button(group, SWT.TOGGLE);
			if (i == 0) {
				button.setSelection(true);
			}
			button.setText("" + (i + 1));
			button.addListener(SWT.Selection, changeChannelListener);
			button.setToolTipText("Channel " + (i + 1));
		}
		group.pack();

		final Button toggleNotPressedButton = new Button(composite, SWT.TOGGLE);
		toggleNotPressedButton.setSelection(false);
		toggleNotPressedButton.setText("Button Not Pressed");

		Button togglePressedButton = new Button(composite, SWT.TOGGLE);
		togglePressedButton.setSelection(true);
		togglePressedButton.setText("Button Pressed");

		Button disabledButton = new Button(composite, SWT.TOGGLE);
		disabledButton.setText("Button Disabled");
		disabledButton.setEnabled(false);

		Button emptyButton = new Button(composite, SWT.TOGGLE);

		final Label hidden = new Label(composite, SWT.NONE);
		hidden.setText("Surprise!");
		hidden.setVisible(false);

		toggleNotPressedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hidden.setVisible(toggleNotPressedButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widget default selected");
			}
		});
		return null;
	}

}
