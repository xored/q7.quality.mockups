package org.eclipse.form;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class FormToolkitTest extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
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

		setStatusLineMessage(null);
		setClearStatusLineOnDispose(form);

		Button button = toolkit.createButton(form.getBody(), "Test button", SWT.NULL);
		final Label labelA = toolkit.createLabel(form.getBody(), "Label for mouse clicking test", SWT.NONE);
		final Label labelB = toolkit.createLabel(form.getBody(), "Test label", SWT.NONE);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				labelB.setText("Button pressed. SelectionListener.widgetSelected called. StateMask: " + String.valueOf(e.stateMask));
				labelB.setSize(500, 18);
				// label1.setBounds(10, 25, 200, 25);
			}
		});

		addMouseListener(button, "Button", button.getText());
		addMouseListener(labelA, "Label", labelA.getText());

		final Text tt = toolkit.createText(form.getBody(), "test");

		Hyperlink link = toolkit.createHyperlink(form.getBody(), "Test link", SWT.NONE);

		link.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				// System.out.println("Selection: " + event.text);
				tt.setBackground(customColor2);
				// tt.setBounds(10, 100, 200, 100);
				tt.setText("hyperlink");
				tt.setSize(100, 18);
			}
		});

		addMouseListener(link, "Hyperlink", link.getText());

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
				tt.setText("MenuItem");

			}

			public void widgetDefaultSelected(SelectionEvent e) {
				tt.setText("MenuItem");
			}
		});

		form.updateToolBar();

		// Button b1 =toolkit.createButton(parent, "Test button", SWT.PUSH);
		// GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		// b1.setLayoutData(gd);

		return null;
	}

	private void addMouseListener(Control control, String type, String name) {
		control.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringBuilder sb = new StringBuilder();
				sb.append(type).append(" \"").append(name).append("\" clicked. MouseButton: ").append(e.button)
						.append(". StateMask: ").append(e.stateMask);
				setStatusLineMessage(sb.toString());
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				StringBuilder sb = new StringBuilder();
				sb.append(type).append(" \"").append(name).append("\" double-clicked. MouseButton: ").append(e.button)
						.append(". StateMask: ").append(e.stateMask);
				setStatusLineMessage(sb.toString());
			}
		});
	}

	private void setClearStatusLineOnDispose(Widget widget) {
		widget.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				setStatusLineMessage(null);
			}
		});
	}

	private void setStatusLineMessage(String message) {
		view.getViewSite().getActionBars().getStatusLineManager().setMessage(message);
	}
}
