package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ToolbarWithComboToolItem extends BaseMockupPart {

	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(5).applyTo(composite);

		CoolBar coolbar = new CoolBar(composite, SWT.BORDER);

		Button button = new Button(coolbar, SWT.PUSH);
		button.setText("Button");
		Point size = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);

		CoolItem item = new CoolItem(coolbar, SWT.NONE);
		item.setPreferredSize(item.computeSize(size.x, size.y));
		item.setControl(button);

		item = new CoolItem(coolbar, SWT.NONE);
		Combo comboItem = new Combo(coolbar, SWT.NONE);
		for (int i = 0; i < 4; i++) {
			comboItem.add("Item " + i);
		}

		@SuppressWarnings("unused")
		ComboHandler handler = new ComboHandler(comboItem);

		size = comboItem.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		item.setPreferredSize(item.computeSize(size.x, size.y));
		item.setControl(comboItem);

		final Text text = new Text(composite, SWT.BORDER);
		text.setText("click here");
		final Menu textMenu = createMenu(text);

		text.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				textMenu.setVisible(true);
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		final Menu buttonMenu = createMenu(button);
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonMenu.setVisible(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		return null;
	}

	private Menu createMenu(final Control control) {
		Menu m1 = new Menu(control);

		MenuItem item1 = new MenuItem(m1, SWT.CASCADE);
		item1.setText("Item1_Cascade");

		MenuItem item2 = new MenuItem(m1, SWT.PUSH);
		item2.setText("Item2_Push");

		MenuItem item3 = new MenuItem(m1, SWT.CHECK);
		item3.setText("Item3_Check");

		MenuItem item4 = new MenuItem(m1, SWT.RADIO);
		item4.setText("Item4_Radio");

		MenuItem separator = new MenuItem(m1, SWT.SEPARATOR);
		separator.setText("Item_SEPARATOR");

		MenuItem item5 = new MenuItem(m1, SWT.RADIO);
		item5.setText("Item5_Radio");
		item5.setEnabled(false);
		return m1;
	}
}
