package com.xored.q7.quality.mockups.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Q7QualityPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addStandaloneView("com.xored.q7.quality.mockups.category.view",
				false, 0, 0, layout.getEditorArea());
		layout.setEditorAreaVisible(false);
	}

}
