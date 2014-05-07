package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Push extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		
		
		Device d = Display.getCurrent ();
		Color red = new Color(d, 255, 0, 0);
		
		
		Button pushButton1 = new Button(composite, SWT.PUSH);
		pushButton1.setText("OK");
		pushButton1.setFocus();
		pushButton1.setToolTipText("Press if OK");
		
		
		
		final Button pushButton2 = new Button(composite, SWT.PUSH);
		pushButton2.setText("CANCEL");
		pushButton2.setEnabled(false);
		
		FontData fontData = pushButton2.getFont().getFontData()[0];
		Font font = new Font(d, new FontData(fontData.getName(), fontData
		    .getHeight(), SWT.ITALIC));
		
		pushButton2.setFont(font);
		
		
		final Button pushButton3 = new Button(composite, SWT.PUSH);
		pushButton3.setText("NEXT >");
		pushButton3.setBackground(red);
		pushButton3.setForeground(red);
		
		Point size = pushButton3.computeSize(SWT.DEFAULT, SWT.DEFAULT);
	    pushButton3.setSize(size);
		
		
		
		pushButton3.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        if (pushButton3.getText()=="NEXT >"){
		        	pushButton3.setText("< PREVIOUS");
		        	Point size = pushButton3.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		    	    pushButton3.setSize(size);
		        	
		        }else{
		        	
		        	pushButton3.setText("NEXT >");
		        	Point size = pushButton3.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		    	    pushButton3.setSize(size);
		    	    
		        }
		    	   	  
		        		      }
		    });
		
		Button pushButton4 = new Button(composite, SWT.PUSH);
		pushButton4.setText("Button With Data");
		pushButton4.setFocus();
		pushButton4.setToolTipText("button with data set");
		pushButton4.setData("myKey", "myValue");
		
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Button Push Test";
	}

}
