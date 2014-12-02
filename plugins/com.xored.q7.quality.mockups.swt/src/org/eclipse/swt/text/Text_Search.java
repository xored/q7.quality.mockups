package org.eclipse.swt.text;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Text_Search extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);

		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		// Display display = PlatformUI.getWorkbench().getDisplay();
		Text text;

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				Text t = (Text) event.widget;
				String msg = t.getMessage();
				if (event.detail == SWT.ICON_CANCEL) {
					System.out.println("Cancel on " + msg);
				} else if (event.detail == SWT.ICON_SEARCH) {
					System.out.println("ICON on " + msg);
				} else {
					System.out.println("Default selection on " + msg);
				}
			}
		};

		text = new Text(composite, SWT.SEARCH);
		text.setMessage("search");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addListener(SWT.DefaultSelection, listener);

		text = new Text(composite, SWT.SEARCH | SWT.ICON_CANCEL);
		text.setMessage("search + cancel");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addListener(SWT.DefaultSelection, listener);

		text = new Text(composite, SWT.SEARCH | SWT.ICON_SEARCH);
		text.setMessage("search + icon");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addListener(SWT.DefaultSelection, listener);

		text = new Text(composite, SWT.SEARCH | SWT.ICON_CANCEL | SWT.ICON_SEARCH);
		text.setMessage("search + cancel + icon");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addListener(SWT.DefaultSelection, listener);

		return null;
	}

}
