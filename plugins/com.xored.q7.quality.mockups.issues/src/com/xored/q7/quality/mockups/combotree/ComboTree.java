package com.xored.q7.quality.mockups.combotree;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ComboTree extends Composite implements ISelectionProvider {
	private Text text;
	private Shell popup;
	private Button arrow;

	public ComboTree(Composite parent, int style) {
		super(parent, style | SWT.BORDER);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(this);

		text = new Text(this, SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(text);

		arrow = new Button(this, SWT.ARROW | SWT.DOWN);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(arrow);

		arrow.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				arrowPressed();
			}
		});

		arrow.addListener(SWT.FocusIn, new Listener() {
			@Override
			public void handleEvent(Event event) {
				arrowFocused();
			}
		});
	}

	protected void arrowPressed() {
		createPopup();
	}

	protected void arrowFocused() {
		// ???
	}

	private void createPopup() {
		hidePopup(false);
		popup = new Shell(getShell(), SWT.RESIZE);

		// putting popup right under our widget
		Rectangle bounds = getDisplay().map(this.getParent(), null, getBounds());
		bounds.y += bounds.height;
		bounds.height = 300;
		popup.setBounds(bounds);

		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(popup);

		viewer = new TreeViewer(popup);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getTree());

		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(input);
		viewer.setSelection(selection);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				hidePopup(true);
			}
		});
		viewer.getTree().addListener(SWT.FocusOut, new Listener() {
			@Override
			public void handleEvent(Event event) {
				hidePopup(false);
			}
		});
		popup.open();
	}

	private void hidePopup(boolean apply) {
		if (popup == null || popup.isDisposed()) {
			return;
		}
		if (apply) {
			updateSelection(viewer.getSelection());
		}
		popup.close();
		popup.dispose();
		popup = null;
		viewer = null;
	}

	private TreeViewer viewer;
	private IContentProvider contentProvider;
	private ILabelProvider labelProvider;
	private Object input;

	public void setContentProvider(IContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	private ISelection selection;
	private List<ISelectionChangedListener> selectionListeners = new CopyOnWriteArrayList<ISelectionChangedListener>();

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionListeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionListeners.add(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	protected void updateSelection(ISelection selection) {
		SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
		for (ISelectionChangedListener listener : selectionListeners) {
			listener.selectionChanged(event);
		}
		setSelection(selection);

		// update a text field
		if (selection.isEmpty()) {
			text.setText("");
			return;
		}
		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first == null) {
				text.setText("");
				return;
			}

			text.setText(labelProvider.getText(first));
		}
	}

}
