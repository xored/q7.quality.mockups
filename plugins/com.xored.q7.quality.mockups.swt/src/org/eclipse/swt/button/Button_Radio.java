package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Radio extends BaseMockupPart {

	private Button[] radioButtons = new Button[6];
	private Text text;

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		text = new Text(parent, SWT.NONE);
		text.setText("unselected");
		radioButtons[0] = new Button(composite, SWT.RADIO);
		radioButtons[0].setSelection(true);
		radioButtons[0].setText("Choice 1");
		
		radioButtons[1] = new Button(composite, SWT.RADIO);
		radioButtons[1].setText("Choice 2");
		radioButtons[1].setEnabled(false);
		
		radioButtons[2] = new Button(composite, SWT.RADIO);
		radioButtons[2].setText("Choice 3");
		
		radioButtons[3] = new Button(composite, SWT.RADIO);
		radioButtons[3].setText("Choice 3");
		
		radioButtons[4] = new Button(composite, SWT.RADIO);
		radioButtons[4].setToolTipText("Choice 4");

		radioButtons[5] = new Button(composite, SWT.RADIO);
		radioButtons[5].setText("Choice 5");

		radioButtons[5].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                		selectionChange();
            		}
        	});
		
		return null;
	}

	private void selectionChange() {
        boolean buttonSelected = radioButtons[5].getSelection();
        text.setText(buttonSelected ? "selected" : "unselected");
    }

}