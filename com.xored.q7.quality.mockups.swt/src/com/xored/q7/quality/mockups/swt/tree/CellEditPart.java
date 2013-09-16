package com.xored.q7.quality.mockups.swt.tree;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.progress.UIJob;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeContentProvider;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;

public class CellEditPart extends BaseMockupPart {
	private final class CompositeCellEditor extends CellEditor {
		Text t;
		ComboViewer cc;
		@SuppressWarnings("unused")
		private Object element;

		private CompositeCellEditor(Composite parent, Object element) {
			super(parent);
			this.element = element;
		}

		protected Control createControl(Composite parent) {
			Composite c = new Composite(parent, SWT.NONE);
			GridLayoutFactory.fillDefaults().spacing(0, 0).margins(0, 0)
					.numColumns(5).applyTo(c);

			t = new Text(c, SWT.BORDER);
			t.setText("qweqwewqe");
			GridDataFactory.fillDefaults().hint(50, SWT.DEFAULT)
					.grab(true, true).applyTo(t);

			cc = new ComboViewer(c, SWT.DROP_DOWN);
			cc.setLabelProvider(new LabelProvider());
			cc.setContentProvider(new IStructuredContentProvider() {

				public void inputChanged(Viewer viewer, Object oldInput,
						Object newInput) {
				}

				public void dispose() {
				}

				public Object[] getElements(Object inputElement) {
					return new String[] { "A", "B", "C" };
				}
			});
			cc.setInput("");
			GridDataFactory.fillDefaults().grab(true, true)
					.hint(100, SWT.DEFAULT).applyTo(cc.getControl());
			Button b = new Button(c, SWT.PUSH);
			b.setText("...");
			GridDataFactory.fillDefaults().grab(false, true).applyTo(b);
			final Job applyJob = new UIJob("Deleyed apply") {

				public IStatus runInUIThread(IProgressMonitor monitor) {
					apply();
					return Status.OK_STATUS;
				}
			};
			FocusListener l = new FocusListener() {

				public void focusGained(FocusEvent e) {
					applyJob.cancel();
				}

				public void focusLost(FocusEvent e) {
					applyJob.schedule(100);
				}
			};
			t.addFocusListener(l);
			cc.getControl().addFocusListener(l);
			return c;
		}

		public void apply() {
			fireApplyEditorValue();
			deactivate();
		}

		protected Object doGetValue() {
			return t.getText() + ":" + cc.getCombo().getText();
		}

		protected void doSetFocus() {
			t.setFocus();
		}

		protected void doSetValue(Object value) {
			if (value instanceof String) {
				String v = (String) value;
				if (v.indexOf(":") == -1) {
					t.setText(v);
				} else {
					t.setText(v.substring(0, v.indexOf(":")));
					cc.getCombo().setText(v.substring(v.indexOf(":") + 1));
				}
			}
		}
	}

	FontRegistry reg = new FontRegistry();

	public String getLabel() {
		return "CellEdit Tree Test";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);

		final TreeViewer viewer = new TreeViewer(section);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getControl());

		viewer.setContentProvider(new SampleTreeContentProvider());
		viewer.addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection sel = (IStructuredSelection) event
							.getSelection();
					if (!sel.isEmpty()) {
						viewer.editElement(sel.getFirstElement(), 0);
					}
				}
			}
		});
		TreeViewerColumn col1 = new TreeViewerColumn(viewer, SWT.NONE);
		col1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column1;
			}
		});
		col1.getColumn().setText("column1");
		col1.getColumn().setWidth(200);

		TreeViewerColumn col2 = new TreeViewerColumn(viewer, SWT.NONE);
		col2.getColumn().setText("column2");
		col2.setEditingSupport(new EditingSupport(viewer) {

			protected void setValue(Object element, Object value) {
				((SampleTreeNode) element).column2 = (String) value;
				viewer.update(element, null);
			}

			protected Object getValue(Object element) {
				return ((SampleTreeNode) element).column2;
			}

			protected CellEditor getCellEditor(Object element) {
				return new CompositeCellEditor(viewer.getTree(), element);
			}

			protected boolean canEdit(Object element) {
				return true;
			}
		});
		col2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column2;
			}
		});
		col2.getColumn().setWidth(300);

		viewer.getTree().addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
			}
		});

		reg.put("sans", new FontData[] { new FontData("sans", 26, 0) });
		// viewer.getTree().setFont(reg.get("sans"));
		viewer.getTree().setHeaderVisible(true);
		viewer.setInput(SampleTreeNode.createSample());
		return viewer.getControl();
	}
}
