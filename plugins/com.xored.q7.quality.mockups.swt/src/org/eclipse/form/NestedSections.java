package org.eclipse.form;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class NestedSections extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
        GridLayoutFactory.swtDefaults().applyTo(parent);
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("Nested Sections Mockup");

	    
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(form);	    
	    GridLayoutFactory.swtDefaults().applyTo(form.getBody());
	    
	    Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
	        | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
	    section.setText("Section 1");
	    section.setDescription("Parent Section");
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(section);
	    
	    Composite client = toolkit.createComposite(section, SWT.WRAP);
	    section.setClient(client);
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    layout.marginWidth = 2;
	    layout.marginHeight = 2;
	    client.setLayout(layout);
		
	    Section section2 = toolkit.createSection(client, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
	    section2.setText("Section2");
	    section2.setDescription("Child section2");
	    
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(section2);
	    Composite client2 = toolkit.createComposite(section2, SWT.WRAP);
	    section2.setClient(client2);
	    client2.setLayout(layout);
	    
	    Section section3 = toolkit.createSection(client, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE);
	    section3.setText("Section3");
	    section3.setDescription("Child section3");
	    
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(section3);
	    Composite client3 = toolkit.createComposite(section3, SWT.WRAP);
	    section3.setClient(client3);
	    client3.setLayout(layout);
	    
	    Section section4 = toolkit.createSection(client3, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE);
	    section4.setText("Section4");
	    section4.setDescription("Child section4");
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(section4);
	    
	    Composite client4 = toolkit.createComposite(section4, SWT.WRAP);
	    section4.setClient(client4);
	    client4.setLayout(layout);
	    
	    
	    
		
		return null;
	}

}
