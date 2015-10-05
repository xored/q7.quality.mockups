package org.eclipse.swt.text;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

public class StyledTextHandler extends MouseAdapter {

	private StyledText styledText;

	public StyledTextHandler(StyledText styledText) {
		this.styledText = styledText;
	}

	public void mouseDown(MouseEvent e) {
		Shell shell = styledText.getDisplay().getActiveShell();
		MessageDialog.openInformation(shell, "StyledTextHandler Popup on mouseDown", "Notification text here.");
		styledText.notifyListeners(SWT.MouseUp, null);
	}

	public void mouseUp(MouseEvent e) {
	}

}