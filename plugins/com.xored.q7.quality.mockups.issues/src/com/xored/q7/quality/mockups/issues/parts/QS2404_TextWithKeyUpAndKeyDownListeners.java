package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2404_TextWithKeyUpAndKeyDownListeners extends BaseMockupPart {

	public String getLabel() {
		return "QS-2404 sample";
	}

	public Control construct(Composite section) {

		Composite client = new Composite(section, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(client);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(client);

		final Text text = new Text(client, SWT.BORDER);
		text.setText("");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(text);

		text.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				System.out.println("KeyUp: " + text.getText());
			}

			public void keyPressed(KeyEvent e) {
				System.out.println("KeyDown: " + text.getText());
			}
		});

		return client;
	}

}
