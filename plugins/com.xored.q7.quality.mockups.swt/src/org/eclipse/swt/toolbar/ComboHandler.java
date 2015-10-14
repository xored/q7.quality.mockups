package org.eclipse.swt.toolbar;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Shell;

public class ComboHandler {

	private MouseAdapter mouseListener;

	public ComboHandler(final Combo combo) {
		mouseListener = new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == SWT.KeyDown) {
					Shell shell = combo.getDisplay().getActiveShell();
					MessageDialog.openInformation(shell, "ComboHandler Popup on mouseDown",
							"Notification text is here.");
					combo.notifyListeners(SWT.MouseUp, null);
				}
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}
		};
		combo.addMouseListener(mouseListener);
	}

}