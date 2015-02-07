package org.eclipse.jface.viewer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/**
 * Mockup to test events on List Viewer Control
 */
public class ListViewerEventsMockup extends BaseMockupPart {

	private ListViewer listViewer;
	private Text txt;

	@Override
	public Control construct(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		listViewer = new ListViewer(container);
		listViewer.setContentProvider(new ObjectsContentProvider());
		listViewer.setLabelProvider(new ObjectsLabelProvider());
		listViewer.setInput(new ObjectsList());

		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			/*
			 * Mockup to test events on SWT List control
			 */@Override
			public void selectionChanged(SelectionChangedEvent event) {
				String outString = "Select" + "\n";
				txt.append(outString);
			}
		});

		txt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		txt.setFocus();
		txt.setText("");

		return null;
	}

	private class ObjectsLabelProvider implements ILabelProvider {

		@Override
		public Image getImage(Object element) {
			return null;
		}

		@Override
		public String getText(Object element) {
			return ((SimpleOBJ) element).getName();
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
	}

	private class SimpleOBJ {
		private String name;

		public SimpleOBJ(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private class ObjectsList {
		private java.util.List<SimpleOBJ> objects;

		public ObjectsList() {
			objects = new ArrayList<SimpleOBJ>();
			objects.add(new SimpleOBJ("name 1"));
			objects.add(new SimpleOBJ("name 2"));
			objects.add(new SimpleOBJ("name 3"));
		}

		public java.util.List<SimpleOBJ> getObjects() {
			return objects;
		}
	}

	private class ObjectsContentProvider implements IStructuredContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return ((ObjectsList) inputElement).getObjects().toArray();
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

}
