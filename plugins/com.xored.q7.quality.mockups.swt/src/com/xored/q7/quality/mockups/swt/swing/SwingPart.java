package com.xored.q7.quality.mockups.swt.swing;

import javax.swing.JScrollPane;
import javax.swing.JTree;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SwingPart extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.EMBEDDED);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
	    
	    java.awt.Frame fileTableFrame = SWT_AWT.new_Frame(composite);
	    java.awt.Panel panel = new java.awt.Panel(new java.awt.BorderLayout());
	    fileTableFrame.add(panel);
	    JTree fileTable = new JTree();
	    fileTable.setDoubleBuffered(true);
	    JScrollPane scrollPane = new JScrollPane(fileTable);
	    panel.add(scrollPane);
		
		return composite;
	}

	@Override
	public String getLabel() {
		return "Swing component in SWT control";
	}

}
