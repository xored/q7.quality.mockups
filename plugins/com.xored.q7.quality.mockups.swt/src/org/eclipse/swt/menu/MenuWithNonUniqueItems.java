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

public class MenuWithNonUniqueItems extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		final Composite composite = new Composite(parent, SWT.BORDER);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		final Label label = new Label(composite, SWT.NONE);
		label.setText("Right-click to open context menu");
		label.setMenu(createMenu(shell));

		return null;
	}

	private Menu createMenu(final Shell shell) {
		Menu root = new Menu(shell);

		MenuItem item0 = new MenuItem(root, SWT.CASCADE);
		item0.setText("item");
		Menu item0menu = new Menu(shell, SWT.DROP_DOWN);
		item0.setMenu(item0menu);

		MenuItem item00 = new MenuItem(item0menu, SWT.CASCADE);
		item00.setText("item");
		Menu item00menu = new Menu(shell, SWT.DROP_DOWN);
		item00.setMenu(item00menu);

		MenuItem item000 = new MenuItem(item00menu, SWT.NONE);
		item000.setText("item 0-0-0");

		MenuItem item01 = new MenuItem(item0menu, SWT.CASCADE);
		item01.setText("item");
		Menu item01menu = new Menu(shell, SWT.DROP_DOWN);
		item01.setMenu(item01menu);

		MenuItem item010 = new MenuItem(item01menu, SWT.NONE);
		item010.setText("item 0-1-0");

		MenuItem item1 = new MenuItem(root, SWT.CASCADE);
		item1.setText("item");
		Menu item1menu = new Menu(shell, SWT.DROP_DOWN);
		item1.setMenu(item1menu);

		MenuItem item10 = new MenuItem(item1menu, SWT.CASCADE);
		item10.setText("item");
		Menu item10menu = new Menu(shell, SWT.DROP_DOWN);
		item10.setMenu(item10menu);

		MenuItem item100 = new MenuItem(item10menu, SWT.NONE);
		item100.setText("item 1-0-0");

		return root;
	}

}
