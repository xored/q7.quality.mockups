package org.eclipse.swt.directoryDialog;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Directory_Dialog extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		Composite composite = new Composite(parent, SWT.BORDER);		
		
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		    .grab(true, true).applyTo(composite);
		
		Label l = new Label(composite, SWT.NONE);
	    l.setText("File location: ");
	    l.setBounds(2, 2, 70, 20);
	    
	    final Text t  = new Text(composite, SWT.BORDER);
	    t.setBounds(80, 2, 300, 20);
	    
	    Button button = new Button(composite, SWT.PUSH);
	    button.setText("Browse...");
	    button.setBounds(2, 30, 100, 30);
	    
		
		button.addSelectionListener(new SelectionAdapter() {
	    
	    	public void widgetSelected(SelectionEvent event) {
	        
	        DirectoryDialog dlg = new DirectoryDialog(shell);

	        dlg.setText("Choose a location");
	        dlg.setMessage("Please select a directory");
	        dlg.open();
	        
	        t.setText(dlg.getFilterPath());
	        
	        
	      }
	    
	    });
	    
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Directory Dialog Test";
	}

}
