package org.eclipse.form;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SectionWithButtons extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		
		GridLayoutFactory.swtDefaults().applyTo(parent);
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("Eclipse Forms API Example");

	    
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(form);	    
	    GridLayoutFactory.swtDefaults().applyTo(form.getBody());
	    
	    Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
	        | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
	    section.setText("Section 1");
	    section.setDescription("Section with buttons");
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(section);
	    
	    Composite client = toolkit.createComposite(section, SWT.WRAP);
	    section.setClient(client);
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    layout.marginWidth = 2;
	    layout.marginHeight = 2;
	    client.setLayout(layout);
	    	    
	    Button b1 = new Button(client, SWT.CHECK);
	    b1.setText("Check button");
	    
	    Button b2 = new Button(client, SWT.RADIO);
	    b2.setText("Radio button");
	    
	    Button b3 = new Button(client, SWT.PUSH);
	    b3.setText("Push button");
	    b3.setToolTipText("This is a push button");
	    
	    Button b4 = new Button(client, SWT.RADIO);
	    b4.setText("Radio button 2");
		
	    Button b5 = new Button(client, SWT.RADIO);
	    b5.setText("Radio button");
		
		
		
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Section With Buttons";
	}

}
