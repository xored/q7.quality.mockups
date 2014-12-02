package org.eclipse.swt.messageBox;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class MessageBoxTest extends BaseMockupPart {

	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		Button button = new Button(composite, SWT.PUSH);
		button.setText("Simple Message Box Without Title");
		button.setBounds(2, 30, 100, 30);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(shell, SWT.NONE | SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage("This MessageBox with simple text");
				mb.open();

			}
		});

		Button button2 = new Button(composite, SWT.PUSH);
		button2.setText("Message With Question");
		button2.setLocation(50, 50);
		button2.setBounds(80, 2, 300, 20);

		final Text t = new Text(composite, SWT.BORDER | SWT.NONE | SWT.MULTI);
		t.setText("Example text");
		t.setBounds(80, 2, 300, 20);

		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(shell, SWT.NONE | SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
				mb.setText("Question");
				mb.setMessage("This MessageBox with question");
				int response = mb.open();
				if (response == SWT.OK)
					t.setText("Press OK");
				if (response == SWT.CANCEL)
					t.setText("Press Cancel");
			}

		});

		Button button3 = new Button(composite, SWT.PUSH);
		button3.setText("Message Box with RETRY Button");
		button3.setLocation(50, 50);
		button3.setBounds(80, 2, 300, 20);

		button3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(shell, SWT.NONE | SWT.ICON_ERROR | SWT.ABORT | SWT.RETRY | SWT.IGNORE);
				mb.setText("Error");
				mb.setMessage("This MessageBox with error");
				int response = mb.open();
				if (response == SWT.ABORT)
					t.setText("Press ABORT");
				if (response == SWT.RETRY)
					t.setText("Press RETRY");
				if (response == SWT.IGNORE)
					t.setText("Press IGNORE");
			}

		});

		Button button4 = new Button(composite, SWT.PUSH);
		button4.setText("Message Box with YES/NO Buttons");
		button4.setBounds(80, 2, 300, 20);

		button4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(shell, SWT.NONE | SWT.ICON_WARNING | SWT.YES | SWT.NO);
				mb.setText("Warning");
				mb.setMessage("This MessageBox with warning");
				int response = mb.open();
				if (response == SWT.YES)
					t.setText("Press YES");
				if (response == SWT.NO)
					t.setText("Press NO");
			}

		});

		return null;

	}

}
