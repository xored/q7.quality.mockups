package org.eclipse.swt.text;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Styled_Text extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);

		Display display = composite.getDisplay();
		Color green = new Color(composite.getDisplay(), new RGB(0, 255, 0));
		Color red = new Color(composite.getDisplay(), new RGB(255, 0, 0));

		StyledText t1 = new StyledText(composite, SWT.SINGLE | SWT.BORDER);
		t1.setLayoutData(new GridData(GridData.FILL_BOTH));
		t1.setText("SINGLE, Green Background");
		t1.setBackground(green);

		StyledText t2 = new StyledText(composite, SWT.FULL_SELECTION
				| SWT.BORDER);
		t2.setLayoutData(new GridData(GridData.FILL_BOTH));
		t2.setText("Full_Selection, Red Foreground");
		t2.setForeground(red);

		StyledText t3 = new StyledText(composite, SWT.MULTI | SWT.BORDER);
		t3.setLayoutData(new GridData(GridData.FILL_BOTH));
		t3.setText("MULTI, Style Range is applied");

		StyleRange styleRange = new StyleRange();
		styleRange.start = 0;
		styleRange.length = t3.getText().length();
		styleRange.fontStyle = SWT.BOLD;
		styleRange.foreground = display.getSystemColor(SWT.COLOR_BLUE);
		t3.setStyleRange(styleRange);

		StyledText t4 = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER);
		t4.setLayoutData(new GridData(GridData.FILL_BOTH));
		t4.setText("READ ONLY");

		StyleRange styleRange2 = new StyleRange();
		styleRange2.start = 5;
		styleRange2.length = t4.getText().length() - 5;
		styleRange2.fontStyle = SWT.ITALIC;
		styleRange2.foreground = display.getSystemColor(SWT.COLOR_MAGENTA);
		t4.setStyleRange(styleRange2);

		StyledText t5 = new StyledText(composite, SWT.WRAP | SWT.BORDER);
		t5.setLayoutData(new GridData(GridData.FILL_BOTH));
		t5.setText("WRAP");

		StyledText t6 = new StyledText(composite, SWT.MULTI | SWT.BORDER);
		t6.setLayoutData(new GridData(GridData.FILL_BOTH));
		t6.setText("This is a text for testing Caret Offset");
		CaretListener caretListener = new CaretListener() {
			@Override
			public void caretMoved(CaretEvent event) {
				Shell shell = event.widget.getDisplay().getActiveShell();
				MessageDialog.openInformation(shell, "Info", "Info for you");
			}
		};
		t6.addCaretListener(caretListener);

		StyledText tMouseDown = new StyledText(composite, SWT.MULTI | SWT.BORDER);
		tMouseDown.setLayoutData(new GridData(GridData.FILL_BOTH));
		tMouseDown.setText("Handler on mouse down Listener");
		tMouseDown.addMouseListener(new StyledTextMouseDownHandler());

		StyledText tArgument = new StyledText(composite, SWT.MULTI | SWT.BORDER);
		tArgument.setLayoutData(new GridData(GridData.FILL_BOTH));
		tArgument.setText("Custom Argument Handler on mouse with case e.button=SWT.KeyDown");
		@SuppressWarnings("unused")
		ArgumentHandler argumentHandler = new ArgumentHandler(tArgument);

		StyledText tMouseUp = new StyledText(composite, SWT.MULTI | SWT.BORDER);
		tMouseUp.setLayoutData(new GridData(GridData.FILL_BOTH));
		tMouseUp.setText("Handler on mouse up Listener");
		tMouseUp.addMouseListener(new StyledTextMouseUpHandler());

		return null;

	}

	static class StyledTextMouseDownHandler extends MouseAdapter {
		@Override
		public void mouseDown(MouseEvent e) {
			Shell shell = e.widget.getDisplay().getActiveShell();
			MessageDialog.openInformation(shell, "StyledTextHandler Popup on mouseDown", "Notification text here.");
			e.widget.notifyListeners(SWT.MouseUp, null);
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}
	}

	static class StyledTextMouseUpHandler extends MouseAdapter {
		@Override
		public void mouseDown(MouseEvent e) {
		}

		@Override
		public void mouseUp(MouseEvent e) {
			Shell shell = e.widget.getDisplay().getActiveShell();
			MessageDialog.openInformation(shell, "StyledTextHandler Popup on mouseUp", "Notification text here.");
		}
	}

}
