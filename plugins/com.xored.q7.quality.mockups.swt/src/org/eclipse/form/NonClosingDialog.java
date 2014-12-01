package org.eclipse.form;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class NonClosingDialog extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		
		parent.setLayout(new FillLayout());
		// Sets up the toolkit.
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		// Creates a form instance.
		Form form = toolkit.createForm(parent);
		// form.setLayoutData(new GridData(GridData.FILL_BOTH));

		form.getBody().setLayout(new GridLayout());

		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.EXPANDED);
		section.setText("Section");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		section.setClient(client);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);
		
		Button b = new Button(client, SWT.PUSH);
		b.setText("Open Dialog");
		
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageDialog mb = new MessageDialog(shell, "My Title", null,
					    "My message", MessageDialog.ERROR, new String[] { "First",
						  "Second", "Third" }, 0)
				
				{
					
					
					public void handleShellCloseEvent(){
						
						
					}
					
					
				}
				
				;
				
				mb.open();
				
				

			}
			
			
			
		});
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
