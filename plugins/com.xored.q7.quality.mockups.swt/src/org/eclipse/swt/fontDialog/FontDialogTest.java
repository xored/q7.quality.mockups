package org.eclipse.swt.fontDialog;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class FontDialogTest  extends BaseMockupPart  {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		final Text t  = new Text(composite, SWT.BORDER | SWT.NONE);
	    t.setText("Example text");
		t.setBounds(80, 2, 300, 20);
	    		
	    Button button = new Button(composite, SWT.PUSH);
	    button.setText("Change Font");
	    button.setBounds(2, 30, 100, 30);
	    
	    button.addSelectionListener(new SelectionAdapter() {
	      @SuppressWarnings("deprecation")
		public void widgetSelected(SelectionEvent e) {
	        FontDialog fd = new FontDialog(shell, SWT.NONE);
	        fd.setText("Select Font");
	        fd.setRGB(new RGB(0, 0, 255));
	        FontData defaultFont = new FontData("Courier", 10, SWT.BOLD);
	        fd.setFontData(defaultFont);
	        FontData newFont = fd.open();
	        if (newFont == null)
	          return;
	        t.setFont(new Font(shell.getDisplay(), newFont));
	        t.setForeground(new Color(shell.getDisplay(), fd.getRGB()));
	 
	      }
	    });
    
		return null;
		
	}
	
}
