package org.eclipse.form;

import java.net.URL;

import javax.swing.event.HyperlinkEvent;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class FormWithLinks extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {

		final Text tt;

		parent.setLayout(new FillLayout());
		// Sets up the toolkit.
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		// Creates a form instance.
		Form form = toolkit.createForm(parent);
		// form.setLayoutData(new GridData(GridData.FILL_BOTH));

		form.getBody().setLayout(new GridLayout());

		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.EXPANDED);
		section.setText("Section With Links");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		section.setClient(client);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		tt = toolkit.createText(client, "  ");
		tt.setVisible(false);

		Hyperlink hyperlink = toolkit.createHyperlink(client, "This is a hyperlink to Eclipse.org", SWT.NULL);
		hyperlink.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				tt.setVisible(true);
				tt.setText("This is a hyperlink to Eclipse.org");
				tt.setSize(400, 20);

			}
		});

		ImageHyperlink imageHyperlink = toolkit.createImageHyperlink(client, SWT.NULL);
		imageHyperlink.setText("");
		imageHyperlink.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		imageHyperlink.setImage(
				new Image(parent.getDisplay(), getClass().getResourceAsStream("icons/icon.png")));
		imageHyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			@SuppressWarnings("unused")
			public void linkActivated(HyperlinkEvent e) {
				System.out.println("Image hyperlink activated.");
			}
		});
		imageHyperlink.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				tt.setVisible(true);
				tt.setText("Image hyperlink activated.");
				tt.setSize(400, 20);
			}
		});

		Hyperlink hyperlink_q = toolkit.createHyperlink(client, "This is a hyperlink with ?", SWT.NULL);
		hyperlink_q.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				tt.setVisible(true);
				tt.setText("This is a hyperlink with ?");
				tt.setSize(400, 20);
			}
		});
		Hyperlink hyperlink_symbol = toolkit.createHyperlink(client, "This_is_a_hyperlink_with ([{<@$%^&,!*>}])",
				SWT.NULL);
		hyperlink_symbol.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				tt.setVisible(true);
				tt.setText("This_is_a_hyperlink_with ([{<@$%^&,!*>}])");
				tt.setSize(400, 20);
			}
		});

		HyperlinkGroup group = new HyperlinkGroup(parent.getDisplay());
		group.add(hyperlink);
		group.add(imageHyperlink);
		group.add(hyperlink_q);
		group.add(hyperlink_symbol);

		group.setActiveBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		group.setActiveForeground(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
		group.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

		Hyperlink hyperlink2 = toolkit.createHyperlink(client, "This is a hyperlink to Eclipse.org", SWT.NULL);
		hyperlink2.addListener(SWT.PUSH, new Listener() {
			public void handleEvent(Event event) {
				tt.setVisible(true);
				tt.setText("This is a hyperlink to Eclipse.org - second link");
				tt.setSize(400, 20);

			}
		});
		
		final FormText rtext = toolkit.createFormText(client, true);
		
	   String data = "<form><p>This is a link to "+"<a href='http://google.com'>web site</a> lol lol </p></form>";
       rtext.setText(data, true, false);	    
       rtext.addHyperlinkListener(new IHyperlinkListener(){

		@Override
		public void linkActivated(
				org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
			
			//System.out.println("Link activated!");
		
			
			
			IWorkbenchBrowserSupport support=PlatformUI.getWorkbench().getBrowserSupport();
			
			
			try {
			IWebBrowser browser=support.getExternalBrowser();
			
			
			
			if (browser != null) {
			URL url=new URL(rtext.getSelectedLinkHref().toString());
			support.createBrowser("myId").openURL(url);
			//browser.openURL(url);
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}

		@Override
		public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    		   
    		  
    		   });
		
       final FormText rtext2 = toolkit.createFormText(client, true);
		
	   String data2 = "<form><p>This link opens a "+"<a href='http://google.com'> dialog </a>lol</p></form>";
      rtext2.setText(data2, true, false);	    
      rtext2.addHyperlinkListener(new IHyperlinkListener(){
    	  public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
    		  Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    		  MessageDialog dialog = new MessageDialog(shell, "My Title", null,
    				    "My message", MessageDialog.ERROR, new String[] { "First",
    				  "Second", "Third" }, 0);
    				dialog.open();
    		  
    	  } 
    	  
    	  
    	  @Override
  		public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
  			// TODO Auto-generated method stub
  			
  		}

  		@Override
  		public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent arg0) {
  			// TODO Auto-generated method stub
  			
  		}
    	  
      });
      

      

      
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
