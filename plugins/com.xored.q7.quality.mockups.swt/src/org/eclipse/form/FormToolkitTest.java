package org.eclipse.form;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class FormToolkitTest extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		/**
		 * The constructor.
		 */
		Color customColor = new Color(null, 187, 255, 255);
		final Color customColor2 = new Color(null, 0, 255, 255);
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		final ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		form.setText("Eclipse Forms");
		form.setFocus();
		form.getBody().setLayout(new GridLayout());

		Button button = toolkit.createButton(form.getBody(), "Test button",
				SWT.NULL);
		final Label label1 = toolkit.createLabel(form.getBody(), "Test label",
				SWT.NONE);
		// Boolean f = label1.getVisible();
		// System.out.print(f);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				label1.setText("Pressing on the button");
				// label1.setBounds(10, 25, 200, 25);
			}
		});

		final Text tt = toolkit.createText(form.getBody(), "test");

		Hyperlink link = toolkit.createHyperlink(form.getBody(), "Test link",
				SWT.NONE);
		link.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				// System.out.println("Selection: " + event.text);
				tt.setBackground(customColor2);
				// tt.setBounds(10, 100, 200, 100);
				tt.setText("hyperlink");

			}
		});

		// tool bar
		form.getToolBarManager().add(new Action("TEST") {
			public void run() {
			}
		});

		final Composite composite = toolkit.createComposite(form.getBody());
		final Tree tree1 = toolkit.createTree(composite, SWT.BORDER
				| SWT.VIRTUAL | SWT.FULL_SELECTION);
		// tree1.setLayout(new FillLayout());
		tree1.setBounds(10, 60, 500, 600);
		tree1.setItemCount(20);
		tree1.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event event) {
				TreeItem item = (TreeItem) event.item;
				TreeItem parentItem = item.getParentItem();
				String text = null;
				if (parentItem == null) {
					text = "node " + tree1.indexOf(item);
				} else {
					text = parentItem.getText() + " - "
							+ parentItem.indexOf(item);
				}
				item.setText(text);
				item.setItemCount(10);
			}
		});

		Menu menu = new Menu(form.getBody());
		MenuItem item = new MenuItem(menu, SWT.NULL);
		item.setText("Testing item");
		form.setMenu(menu);
		item.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				tt.setText("MenuItem");

			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				tt.setText("MenuItem");

			}
		});

		form.updateToolBar();

		// Button b1 =toolkit.createButton(parent, "Test button", SWT.PUSH);
		// GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		// b1.setLayoutData(gd);

		return null;
	}

}
