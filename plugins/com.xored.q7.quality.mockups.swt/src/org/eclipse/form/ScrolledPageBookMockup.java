package org.eclipse.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ScrolledPageBookMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		final ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		form.setText("Form with subpages");
		form.setFocus();
		form.getBody().setLayout(new GridLayout());

		ScrolledPageBook pageBook = toolkit.createPageBook(form.getBody(),
				SWT.V_SCROLL | SWT.H_SCROLL);
		// pageBook.showEmptyPage();

		Composite page1 = pageBook.createPage("First Page");
		Text text1 = toolkit.createText(page1, "First Page", SWT.MULTI
				| SWT.WRAP);
		page1.setLayout(new GridLayout());
		text1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 0;
		pageBook.setLayoutData(gd);

		Composite page2 = pageBook.createPage("Second Page");
		Text text2 = toolkit.createText(page1, "Second Page", SWT.MULTI
				| SWT.WRAP);
		page2.setLayout(new GridLayout());
		text2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		pageBook.showPage("First Page");
		// pageBook.showPage(page2);

		pageBook.getCurrentPage();

		return null;
	}

}
