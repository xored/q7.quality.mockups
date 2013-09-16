package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2167_DateTimePopup extends BaseMockupPart {

	public String getLabel() {
		return "QS-2167, DateTime picker in pop-up window";
	}

	public Control construct(Composite section) {
		Composite client = new Composite(section, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(client);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(client);

		final Text text = new Text(client, SWT.BORDER);
		text.setText("click here");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(text);

		text.addMouseListener(new MouseAdapter() {

			public void mouseUp(MouseEvent e) {
				new DateTimePicker(text);
			}
		});

		return client;
	}

	private static class DateTimePicker {

		public DateTimePicker(final Text text) {
			final Shell shell = new Shell(SWT.NONE);
			shell.setText("Pick a Date");

			final DateTime calendar = new DateTime(shell, SWT.CALENDAR);
			calendar.pack();
			shell.pack();

			Point location = text.getLocation();
			location.y += text.getBounds().height;
			shell.setLocation(text.toDisplay(location));

			calendar.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}

				public void widgetDefaultSelected(SelectionEvent e) {
					text.setText(String.format("%s/%s/%s",
							calendar.getMonth() + 1, calendar.getDay(),
							calendar.getYear()));
					shell.dispose();
				}
			});

			shell.addShellListener(new ShellListener() {

				public void shellIconified(ShellEvent e) {
					// TODO Auto-generated method stub

				}

				public void shellDeiconified(ShellEvent e) {
					// TODO Auto-generated method stub

				}

				public void shellDeactivated(ShellEvent e) {
					shell.getDisplay().asyncExec(new Runnable() {

						public void run() {
							shell.dispose();
						}
					});
				}

				public void shellClosed(ShellEvent e) {
					// TODO Auto-generated method stub

				}

				public void shellActivated(ShellEvent e) {
					// TODO Auto-generated method stub

				}
			});

			shell.open();
		}

	}

}
