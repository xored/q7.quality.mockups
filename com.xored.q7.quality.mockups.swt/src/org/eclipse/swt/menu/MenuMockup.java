package org.eclipse.swt.menu;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class MenuMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		Composite composite = new Composite(parent, SWT.BORDER);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		Label l = new Label(composite, SWT.NONE);
		l.setText("Right-click to launch pop-up menu");

		Menu m1 = new Menu(shell, SWT.POP_UP);

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

		l.setMenu(m1);

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Menu Test";
	}

}
