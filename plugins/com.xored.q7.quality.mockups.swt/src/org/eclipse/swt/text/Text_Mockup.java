package org.eclipse.swt.text;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Text_Mockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		
Composite composite = new Composite(parent, SWT.BORDER);		
		
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		    .grab(true, true).applyTo(composite);
		
		Label l1 = new Label(composite, SWT.NONE);
		l1.setText("Plain Limited Text:  ");
		l1.setBounds(2, 2, 100, 25);
				
		Text t = new Text(composite, SWT.BORDER);
		t.setTextLimit(5);
		t.setBounds(120, 2, 300, 25);
		t.setText("Left");
		
		Label l2 = new Label(composite, SWT.NONE);
		l2.setText("Right-oriented text:  ");
		l2.setBounds(2, 30, 102, 25);
		
		
		Text t2 = new Text(composite, SWT.BORDER | SWT.RIGHT);
		t2.setText("Right");
		t2.setBounds(120, 30, 300, 25);
		
		
		Label l3 = new Label(composite, SWT.NONE);
		l3.setText("Center-oriented text:  ");
		l3.setBounds(2, 60, 108, 25);
		
		
		Text t3 = new Text(composite, SWT.BORDER | SWT.CENTER);
		t3.setText("Center");
		t3.setBounds(120, 60, 300, 25);
		
		Label l4 = new Label(composite, SWT.NONE);
		l4.setText("Icon Search text:  ");
		l4.setBounds(2, 90, 108, 25);
				
		Text t4 = new Text(composite, SWT.ICON_SEARCH);
		t4.setText("Icon Search");
		t4.setBounds(120, 90, 300, 25);
		
		Label l5 = new Label(composite, SWT.NONE);
		l5.setText("Icon-Cancel text:  ");
		l5.setBounds(2, 120, 108, 25);
		
		
		Text t5= new Text(composite, SWT.BORDER | SWT.ICON_CANCEL);
		t5.setText("Icon Cancel");
		t5.setBounds(120, 120, 300, 45);
		
		Label l6 = new Label(composite, SWT.NONE);
		l6.setText("Password text:  ");
		l6.setBounds(2, 170, 108, 25);
		
		
		Text t6= new Text(composite, SWT.BORDER | SWT.PASSWORD);
		t6.setText("Password");
		t6.setBounds(120, 170, 300, 25);
		
		Label l7 = new Label(composite, SWT.NONE);
		l7.setText("Readonly text:  ");
		l7.setBounds(2, 200, 108, 25);
		
		
		Text t7= new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		t7.setText("Readonly");
		t7.setBounds(120, 200, 300, 25);
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Text Test";
	}

}
