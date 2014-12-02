package org.eclipse.swt.colorDialog;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Color_Dialog extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
				
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		
		Color color = new Color(composite.getDisplay(), new RGB(0, 255, 0));
		
		final Label colorLabel = new Label(composite, SWT.NONE);
	    colorLabel.setText("            ");
	    colorLabel.setBackground(color);

	    Button button = new Button(composite, SWT.PUSH);
	    button.setText("Color...");
	    
	    button.addSelectionListener(new SelectionAdapter() {
	    
	    	public void widgetSelected(SelectionEvent event) {
	        // Create the color-change dialog
	        ColorDialog dlg = new ColorDialog(shell);

	        // Set the selected color in the dialog from
	        // user's selected color
	        dlg.setRGB(colorLabel.getBackground().getRGB());

	        // Change the title bar text
	        dlg.setText("Choose a Color");

	        Color color = new Color(shell.getDisplay(), new RGB(0, 255, 0));
	        
	        // Open the dialog and retrieve the selected color
	        RGB rgb = dlg.open();
	        if (rgb != null){
	          // Dispose the old color, create the
	          // new one, and set into the label
	          color.dispose();
	          color = new Color(shell.getDisplay(), rgb);
	          colorLabel.setBackground(color);
	        }
	      }
	    
	    });
		return null;
	    
	}
}
