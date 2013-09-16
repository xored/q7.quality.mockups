package org.eclipse.jface.viewer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ListViewerMockup extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		final Display display = Display.getCurrent();
		ListViewer listViewer;

		listViewer = new ListViewer(parent, SWT.H_SCROLL | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);

		Month[] model = createModel();

		listViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				return (Month[]) inputElement;
			}

			public void dispose() {
				System.out.println("Disposing ...");
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			}

		});

		listViewer.setLabelProvider(new LabelProvider() {
			public Image getImage(Object element) {
				return null;
			}

			public String getText(Object element) {
				return ((Month) element).name;
			}

		});

		listViewer.setInput(model);

		return null;
	}

	public Month[] createModel() {

		Month[] elements = new Month[12];

		for (int i = 0; i < 12; i++) {

			elements[i] = new Month(i);

		}

		return elements;

	}

	public class Month {

		int num;
		String name;

		String[] names = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		Month(int i) {
			this.num = i + 1;
			this.name = names[i];

		}

	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "List Viewer Test";
	}

}
