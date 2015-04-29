package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT532_SetFocusInTextFieldDisablesButton extends BaseMockupPart {

	private Composite composite = null;
	private Text text = null;
	private Text text2 = null;
	private Button button = null;
	private Button chkBox = null;
	
	
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		createComposite(parent);
		addButton(composite);
		addCheckBox(composite);
		addTextField(composite);
		return null;	
	}
	
	private void addButton(Composite parent) {
		button = new Button (parent, SWT.PUSH);
		button.setText("I'm button");
		
	}
	
	private void addCheckBox(Composite parent) {
		chkBox = new Button (parent, SWT.CHECK);
		
		chkBox.addSelectionListener (new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (chkBox.getSelection()){
					text.setEnabled(true);
					text2.setText("Yes");
				} else {
			    text.setEnabled(false);
				text2.setText("No");}
				
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
		
			}
			
			
			
		});
		
	}
	
	
	private void createComposite(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
	}
	
	private void addTextField(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP );
		text.setEnabled(false);
		text2 = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP );
		
		text.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
				button.setEnabled(false);
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				button.setEnabled(true);
				
			}
			
			
			
			
			
			
		});
		
    }
	
	
	


}
