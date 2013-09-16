package com.xored.q7.quality.mockups.ui.treeviewer.menu;

import org.eclipse.swt.SWT;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class MenuContribution extends ExtensionContributionFactory {

	public MenuContribution() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
		CommandContributionItemParameter p = new CommandContributionItemParameter(
				serviceLocator, "","org.eclipse.ui.file.exit", SWT.PUSH);
		
		p.label = "Exit the application";
		CommandContributionItem item = new CommandContributionItem(p);
        item.setVisible(true);
	}
}
