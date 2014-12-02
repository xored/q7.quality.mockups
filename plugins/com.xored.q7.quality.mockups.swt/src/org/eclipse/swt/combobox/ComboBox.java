package org.eclipse.swt.combobox;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ComboBox extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Editable ReadOnly ComboBox");

		Combo combo = new Combo (composite, SWT.READ_ONLY);
		combo.setItems (new String [] {"Alpha", "Bravo", "Charlie"});
//		combo.addListener(eventType, listener)
		Rectangle clientArea = composite.getClientArea ();
		combo.setBounds (clientArea.x, clientArea.y, 200, 300);
		
		Label label2 = new Label(composite, SWT.NONE);
		label2.setText("Editable ComboBox With Default Value");

		Combo combo2 = new Combo (composite, SWT.READ_ONLY);
		combo2.setItems (new String [] {"Alpha", "Bravo", "Charlie", "Paris", "London"});
		combo2.select (2);
		Rectangle clientArea2 = composite.getClientArea ();
		combo2.setBounds (clientArea2.x, clientArea2.y, 400, 300);
		
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("Editable ComboBox");

		Combo combo3 = new Combo (composite, SWT.NONE);
		combo3.setItems (new String [] {"Alpha", "Bravo", "Charlie", "Paris", "London"});
		combo3.select (1);
		Rectangle clientArea3 = composite.getClientArea ();
		combo3.setBounds (clientArea3.x, clientArea3.y, 400, 300);
		
		return null;
	}

}
