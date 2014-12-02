package org.eclipse.jface.viewer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ComboViewerMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Label l1 = new Label(parent, SWT.NONE);
		l1.setText("Name");
		Text name = new Text(parent, SWT.BORDER);
		name.setBounds(10, 10, 200, 30);

		Group g1 = new Group(parent, SWT.NONE);
		g1.setText("Month of Birth");
		g1.setLayout(new FillLayout());

		// ComboViewer cv_d = new ComboViewer(g1, SWT.BORDER | SWT.DROP_DOWN |
		// SWT.READ_ONLY | SWT.TRAVERSE_ARROW_NEXT );
		// cv_d.setContentProvider(new ArrayContentProvider());
		// cv_d.setLabelProvider(new LabelProvider() {
		// @Override
		// public String[] getText(Object element) {
		// String[] dd;
		// for (int i = 1; i < 32; i++) {
		// dd[i] = Integer.toString(i);
		// }
		// return (String[]) dd;
		// }
		// });

		ComboViewer cv_m = new ComboViewer(g1, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY | SWT.TRAVERSE_ARROW_NEXT);
		cv_m.setContentProvider(new ArrayContentProvider());
		cv_m.setInput(new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" });

		// ComboViewer cv_y = new ComboViewer(g1, SWT.BORDER | SWT.DROP_DOWN |
		// SWT.READ_ONLY | SWT.TRAVERSE_ARROW_NEXT );
		// cv_y.setContentProvider(new ArrayContentProvider());
		// cv_y.setInput(new String[]{"test1", "test2", "test3"});

		Label l2 = new Label(parent, SWT.NONE);
		l2.setText("City");

		ComboViewer cv_c = new ComboViewer(parent, SWT.BORDER | SWT.DROP_DOWN);
		cv_c.setContentProvider(new ArrayContentProvider());
		cv_c.setInput(new String[] { "Abakan", "Voronezh", "Moscow", "Novosibirsk", "Omsk", "Pskov" });

		final Text t1 = new Text(parent, SWT.READ_ONLY);
		t1.setText("City");

		cv_c.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				String sel_s = sel.toString();
				t1.setText(sel_s);
			}
		});

		return null;
	}
}
