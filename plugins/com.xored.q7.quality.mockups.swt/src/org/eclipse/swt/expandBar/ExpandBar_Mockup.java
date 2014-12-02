package org.eclipse.swt.expandBar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ExpandBar_Mockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);

		// final Shell shell =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		final Display display = PlatformUI.getWorkbench().getDisplay();

		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);
		SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);
		Composite leftComposite = new Composite(sashForm, SWT.BORDER);
		leftComposite.setLayout(new FillLayout());
		Composite rightComposite = new Composite(sashForm, SWT.NONE);
		rightComposite.setLayout(new FillLayout());
		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBounds(2, 2, 400, 400);
		final ExpandItem expandItem1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem1.setText("item");
		final ExpandItem expandItem2 = new ExpandItem(expandBar, SWT.NONE); /* expandItem2 */
		expandItem2.setText("item");

		final StyledText text = new StyledText(expandBar, SWT.MULTI | SWT.WRAP);

		expandItem1.setControl(text);
		text.setText("initial text that will wrap if it's long enough");

		final StyledText text2 = new StyledText(expandBar, SWT.MULTI | SWT.WRAP);

		text2.setText("This is a text of Expand Item 2");
		text2.setEditable(true);
		expandItem2.setControl(text2);
		/*
		 * update the item's height if needed in response to changes in the
		 * text's size
		 */

		Composite composite2 = new Composite(expandBar, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite2.setLayout(layout);
		Button button = new Button(composite2, SWT.PUSH);
		button.setText("SWT.PUSH");
		button = new Button(composite2, SWT.RADIO);
		button.setText("SWT.RADIO");
		button = new Button(composite2, SWT.CHECK);
		button.setText("SWT.CHECK");
		button = new Button(composite2, SWT.TOGGLE);
		button.setText("SWT.TOGGLE");
		ExpandItem item0 = new ExpandItem(expandBar, SWT.NONE, 0);
		item0.setText("What is your favorite button");
		item0.setHeight(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item0.setControl(composite2);

		ImageDescriptor descriptor = ImageDescriptor.createFromFile(getClass(), "/icons/q7.png");
		final Image img = descriptor.createImage();
		expandBar.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				img.dispose();
			}
		});
		item0.setImage(img);

		item0.setExpanded(true);

		// bar.setSpacing(8);

		final int TRIAL_WIDTH = 100;
		final int trimWidth = text.computeTrim(0, 0, TRIAL_WIDTH, 100).width - TRIAL_WIDTH;
		text.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				Point size = text.computeSize(text.getSize().x - trimWidth, SWT.DEFAULT);
				if (expandItem1.getHeight() != size.y) {
					expandItem1.setHeight(size.y);
				}
			}
		});

		final int trimWidth2 = text2.computeTrim(0, 0, TRIAL_WIDTH, 100).width - TRIAL_WIDTH;
		text2.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				Point size2 = text2.computeSize(text2.getSize().x - trimWidth2, SWT.DEFAULT);
				if (expandItem2.getHeight() != size2.y) {
					expandItem2.setHeight(size2.y);
				}
			}
		});

		Point size = text.computeSize(expandBar.getClientArea().width, SWT.DEFAULT);
		expandItem1.setHeight(size.y);
		expandItem1.setExpanded(true);

		Point size2 = text2.computeSize(expandBar.getClientArea().width, SWT.DEFAULT);
		expandItem2.setHeight(size2.y);
		expandItem2.setExpanded(false);

		return null;
	}

}
