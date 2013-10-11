package org.eclipse.form;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class FormWithSection extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		TableViewer viewer;

		GridLayoutFactory.swtDefaults().applyTo(parent);

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Eclipse Forms API Example");

		GridDataFactory.fillDefaults().grab(true, true).applyTo(form);
		GridLayoutFactory.swtDefaults().applyTo(form.getBody());

		Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.setText("Section 1");
		section.setDescription("This demonstrates the usage of section");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		Section section2 = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR);
		section2.setText("Section 2");
		section2.setDescription("Section2");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section2);
		Composite c2 = toolkit.createComposite(section2, SWT.WRAP);
		section2.setClient(c2);

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);
		Table t = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		t.setLayoutData(gd);
		toolkit.paintBordersFor(client);
		Button b = toolkit.createButton(client, "Do something", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		section.setClient(client);
		viewer = new TableViewer(t);

		viewer.setContentProvider(new ArrayContentProvider());

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		viewerColumn.getColumn().setWidth(100);
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return element.toString();
			};

			public Image getImage(Object element) {
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(ISharedImages.IMG_OBJ_ELEMENT);

			};
		});

		viewer.setInput(getViewSite());

		Section section3 = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR);
		section3.setText("Section 2");
		section3.setDescription("Section2_1");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section3);
		Composite c3 = toolkit.createComposite(section3, SWT.WRAP);
		section3.setClient(c3);

		Section section4 = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR);
		section4.setText("");
		section4.setDescription("No named section");
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section4);
		Composite c4 = toolkit.createComposite(section4, SWT.WRAP);
		section4.setClient(c4);

		return null;
	}

	public String[] getViewSite() {

		String[] s = new String[] { "One", "Two", "Three" };
		return s;

	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Section Test";
	}

}
