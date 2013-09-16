package org.eclipse.form;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ExpandableCompositeMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		GridLayoutFactory.swtDefaults().applyTo(parent);

		Color cl = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		final ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Expandable Composite Mockup");
		form.setBackground(cl);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(form);
		GridLayoutFactory.swtDefaults().applyTo(form.getBody());

		ExpandableComposite composite = toolkit.createExpandableComposite(form.getBody(),
				ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TREE_NODE);
		composite.setText("ExpandableComposite");

		String text = "We will now create a somewhat long text so that " +
				"we can use it as content for the expandable composite. " +
				"Expandable composite is used to hide or show the text using the " +
				"toggle control";

		Label client = toolkit.createLabel(composite, text, SWT.WRAP);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		Rectangle rect = parent.getMonitor().getClientArea();
		gd.widthHint = rect.width / 6;

		composite.setLayoutData(gd);
		composite.setClient(client);

		ExpandableComposite composite2 = toolkit.createExpandableComposite(form.getBody(),
				ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TWISTIE
						| ExpandableComposite.NO_TITLE_FOCUS_BOX);
		composite2.setText("TwistieExpandableComposite");

		String text2 = "This is a text of expandable twistie composite";

		Label client2 = toolkit.createLabel(composite2, text2, SWT.WRAP);

		GridData gd2 = new GridData();
		gd2.horizontalSpan = 2;
		gd2.horizontalAlignment = SWT.FILL;

		gd2.widthHint = rect.width / 6;

		composite2.setLayoutData(gd2);
		composite2.setClient(client2);

		ExpandableComposite composite3 = toolkit.createExpandableComposite(form.getBody(),
				ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TWISTIE | ExpandableComposite.NO_TITLE
						| ExpandableComposite.EXPANDED);
		composite3.setText("No Title ExpandableComposite");

		String text3 = "This is a text of expandable no title composite";

		Label client3 = toolkit.createLabel(composite3, text3, SWT.WRAP);

		GridData gd3 = new GridData();
		gd3.horizontalSpan = 2;
		gd3.horizontalAlignment = SWT.FILL;

		gd3.widthHint = rect.width / 6;

		composite3.setLayoutData(gd3);
		composite3.setClient(client3);

		ExpandableComposite composite4 = toolkit.createExpandableComposite(form.getBody(),
				ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TREE_NODE);
		composite4.setText("Parent ExpandableComposite");

		// String text4 = "This is a text of a parent composite";

		ExpandableComposite composite5 = toolkit.createExpandableComposite(composite4,
				ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TWISTIE);
		composite5.setText("Nested ExpandableComposite");

		String text5 = "This is a text of a nested composite";

		Label client5 = toolkit.createLabel(composite5, text5, SWT.WRAP);
		GridData gd5 = new GridData();
		gd5.horizontalSpan = 2;
		gd5.horizontalAlignment = SWT.FILL;
		gd5.widthHint = rect.width / 6;
		composite5.setLayoutData(gd5);
		composite5.setClient(client5);

		composite4.setClient(composite5);

		composite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		composite2.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		composite3.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		composite4.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		composite5.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Expandable Composite Test";
	}

}
