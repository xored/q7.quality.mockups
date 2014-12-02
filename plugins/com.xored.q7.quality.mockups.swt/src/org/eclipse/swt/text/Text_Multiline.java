package org.eclipse.swt.text;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Text_Multiline extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.BORDER);	
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		    .grab(true, true).applyTo(composite);
		
		
		Text t1 = new Text (composite, SWT.MULTI | SWT.LEFT);
		t1.setLayoutData(new GridData(GridData.FILL_BOTH));
		t1.setText("Left");
		
		Text t2 = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.RIGHT);
        t2.setLayoutData(new GridData(GridData.FILL_BOTH));
        t2.setText("Right");
        
		
        Text t3 = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.CENTER);
        t3.setLayoutData(new GridData(GridData.FILL_BOTH));
        t3.setText("Center");
        
        Text t4 = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        t4.setLayoutData(new GridData(GridData.FILL_BOTH));
        t4.setText("Scrolls");
        
        Text t5 = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP);
        t5.setLayoutData(new GridData(GridData.FILL_BOTH));
        t5.setText("Wrap");
        
        
        
                
		return null;
	}


}
