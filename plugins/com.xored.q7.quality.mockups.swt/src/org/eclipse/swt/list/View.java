package org.eclipse.swt.list;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;


import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class View extends BaseMockupPart {
	public static final String ID = "Q7ListTest.view";

	private ListViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public Control construct (Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout());
		c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		List list = new List(c, SWT.MULTI | SWT.LEFT_TO_RIGHT | SWT.BORDER);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer = new ListViewer(list);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		// Provide the input to the ContentProvider
		viewer.setInput(new String[] {"One", "Two", "Three", "Three fourty", "View FORM", "Edit FORM"});
		
		List list2 = new List(c, SWT.MULTI | SWT.LEFT_TO_RIGHT | SWT.BORDER);
		list2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		list2.setItems(new String[] {"One", "Two", "Three", "Three fourty", "View FORM", "Edit FORM"});
		
		final Label l = new Label(c, SWT.NONE);
		l.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		l.setText("a");
		final Text text = new Text(c, SWT.BORDER);
		text.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				//Text t = (Text) e.widget;
				if (text.getText().trim().equals("test")) {
					l.setText("test");
					return;
				}
				l.setText("a");
			}
		});
		return null;
	}

}