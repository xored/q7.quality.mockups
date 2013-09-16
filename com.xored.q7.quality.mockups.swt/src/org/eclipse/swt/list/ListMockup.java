package org.eclipse.swt.list;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ListMockup extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		final Text t = new Text(composite, SWT.SINGLE);
		t.setText("Listening...");

		Label lb = new Label(composite, SWT.NONE);
		lb.setText("SINGLE List: ");

		final List l1 = new List(composite, SWT.NONE);

		for (int i = 0; i < 5; i++) {
			l1.add("Value" + i);

		}

		Color red = new Color(composite.getDisplay(), new RGB(255, 0, 0));

		l1.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				String string = "";
				String[] selection = l1.getSelection();
				for (int i = 0; i < selection.length; i++)
					string += selection[i] + " ";
				t.setText(string);

			}
		});

		Label lb2 = new Label(composite, SWT.NONE);
		lb2.setText("MULTI List: ");

		final List l2 = new List(composite, SWT.MULTI);

		for (int i = 0; i < 5; i++) {
			l2.add("Item" + i);

		}

		l2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				String string = "";
				String[] selection = l2.getSelection();
				for (int i = 0; i < selection.length; i++)
					string += selection[i] + " ";
				t.setText(string);

			}
		});

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "List Test";
	}

}
