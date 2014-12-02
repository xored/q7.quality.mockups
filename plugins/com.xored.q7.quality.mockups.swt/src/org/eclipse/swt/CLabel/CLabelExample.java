package org.eclipse.swt.CLabel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CLabelExample extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		
		final Text t = new Text(parent, SWT.MULTI | SWT.LEFT);
	    t.setText("no events");
		
		CLabel l = new CLabel(parent, SWT.LEFT);
		l.setText("This is my CLabel");
		l.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    l.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
	    l.addMouseListener(new MouseListener(){
	    		
	    	    
	    	    public void mouseDoubleClick(MouseEvent e) {
		    	       saySomething("Mouse double clicked");
		    	}
	    	    
	    	    public void mouseDown(MouseEvent e) {
		    	       saySomething("CLabelDown");
		    	}
	    	    
	    	    public void mouseUp(MouseEvent e) {
		    	       saySomething("CLabelUp");
		    	}
	    	    
	    	    void saySomething(String eventDescription) {
	    	        t.setText(eventDescription);
	    	    }
	    	
	    });
	    
	    
		
	    Label label = new Label (parent, SWT.LEFT);
	    label.setText("This is simple Label");
	    label.addMouseListener(new MouseListener(){
    		
    	    
    	    public void mouseDoubleClick(MouseEvent e) {
	    	       saySomething("Mouse double clicked");
	    	}
    	    
    	    public void mouseDown(MouseEvent e) {
	    	       saySomething("LabelDown");
	    	}
    	    
    	    public void mouseUp(MouseEvent e) {
	    	       saySomething("LabelUp");
	    	}
    	    
    	    void saySomething(String eventDescription) {
    	        t.setText(eventDescription);
    	    }
    	
    });
	    
	    
		
		return null;
	}

}
