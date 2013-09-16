package com.xored.q7.quality.mockups.issues;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;

public interface IQ7MockupPart {
	public Control construct(Composite parent);
	
	public String getLabel();

	public void setSite(IViewSite site);
}
