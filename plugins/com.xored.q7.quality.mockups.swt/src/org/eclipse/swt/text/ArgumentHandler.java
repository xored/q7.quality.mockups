package org.eclipse.swt.text;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

public class ArgumentHandler {
	private MouseAdapter mouseListener;

	public ArgumentHandler(final StyledText text) {

		mouseListener = new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == SWT.KeyDown) {
					Shell shell = text.getDisplay().getActiveShell();
					MessageDialog.openInformation(shell, "ArgumentHandler Popup on mouseDown",
							"Notification text here.");
					text.notifyListeners(SWT.MouseUp, null);
				}
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}
		};
		text.addMouseListener(mouseListener);

	}

}