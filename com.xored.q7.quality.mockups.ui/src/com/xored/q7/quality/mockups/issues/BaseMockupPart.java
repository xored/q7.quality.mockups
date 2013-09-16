package com.xored.q7.quality.mockups.issues;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;

public abstract class BaseMockupPart implements IQ7MockupPart {

	protected IViewSite site;

	public abstract Control construct(Composite parent);

	public abstract String getLabel();

	public void setSite(IViewSite site) {
		this.site = site;
	}
}
