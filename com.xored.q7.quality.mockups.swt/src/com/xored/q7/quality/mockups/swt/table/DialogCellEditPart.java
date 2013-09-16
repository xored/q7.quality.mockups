package com.xored.q7.quality.mockups.swt.table;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.progress.UIJob;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeContentProvider;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;

public class DialogCellEditPart extends BaseMockupPart {
	private final class CompositeCellEditor extends CellEditor {
		Button b;
		private String value;
		private Text text;
		private Dialog dialog;

		private CompositeCellEditor(Composite parent) {
			super(parent);
		}

		protected Control createControl(Composite parent) {
			Composite c = new Composite(parent, SWT.NONE);
			GridLayoutFactory.fillDefaults().spacing(0, 0).margins(0, 0)
					.numColumns(2).applyTo(c);
			GridDataFactory.fillDefaults().grab(true, true).applyTo(c);
			text = new Text(c, SWT.BORDER);

			if (value != null) {
				text.setText(value);
			}
			text.addModifyListener(new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					b.getDisplay().asyncExec(new Runnable() {

						public void run() {
							value = text.getText();
						}
					});
				}
			});
			GridDataFactory.fillDefaults().grab(true, true).applyTo(text);
			b = new Button(c, SWT.PUSH);
			b.setText("...");

			final Job applyJob = new UIJob("Deleyed apply") {

				public IStatus runInUIThread(IProgressMonitor monitor) {
					apply();
					return Status.OK_STATUS;
				}
			};

			b.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					applyJob.cancel();
					dialog = new Dialog(b.getShell()) {

						protected Control createContents(Composite parent) {
							Label l = new Label(parent, SWT.NONE);
							l.setText("Please presse OK to add #");
							return super.createContents(parent);
						}

						protected void okPressed() {
							applyJob.cancel();
							value = value + "#";
							text.setText(value);
							super.okPressed();
						}
					};
					dialog.open();
				}
			});
			GridDataFactory.swtDefaults().hint(20, SWT.DEFAULT)
					.align(SWT.RIGHT, SWT.CENTER).grab(true, true).applyTo(b);

			FocusListener l = new FocusListener() {

				public void focusGained(FocusEvent e) {
					applyJob.cancel();
				}

				public void focusLost(FocusEvent e) {
					applyJob.schedule(100);
				}
			};
			b.addFocusListener(l);
			text.addFocusListener(l);
			return c;
		}

		public void apply() {
			fireApplyEditorValue();
			// deactivate();
		}

		public void deactivate() {
			if (!isDialogActive()) {
				super.deactivate();
			}
		}

		protected Object doGetValue() {
			return value;
		}

		protected void doSetFocus() {
			b.setFocus();
		}

		protected void doSetValue(Object value) {
			if (value instanceof String) {
				this.value = (String) value;
				if (this.value != null && text != null && !text.isDisposed()) {
					text.getDisplay().asyncExec(new Runnable() {

						public void run() {
							text.setText(CompositeCellEditor.this.value);
						}
					});
				}
			}
		}

		public boolean isDialogActive() {
			return dialog != null && dialog.getShell() != null
					&& !dialog.getShell().isDisposed()
					&& dialog.getShell().isVisible();
		}
	}

	FontRegistry reg = new FontRegistry();

	public String getLabel() {
		return "Dialog CellEdit Table Test";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);

		final TableViewer viewer = new TableViewer(section, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getControl());

		viewer.setContentProvider(new SampleTreeContentProvider());
		TableViewerColumn col1 = new TableViewerColumn(viewer, SWT.NONE);
		col1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column1;
			}
		});
		col1.getColumn().setText("column1");
		col1.getColumn().setWidth(200);

		TableViewerColumn col2 = new TableViewerColumn(viewer, SWT.NONE);
		col2.getColumn().setText("column2");
		col2.setEditingSupport(new EditingSupport(viewer) {

			protected void setValue(Object element, Object value) {
				((SampleTreeNode) element).column2 = (String) value;
				viewer.update(element, null);
			}

			protected Object getValue(Object element) {
				return ((SampleTreeNode) element).column2;
			}

			protected CellEditor getCellEditor(final Object element) {
				final CompositeCellEditor editor = new CompositeCellEditor(
						viewer.getTable());
				editor.addListener(new ICellEditorListener() {

					public void editorValueChanged(boolean oldValidState,
							boolean newValidState) {
					}

					public void cancelEditor() {
					}

					public void applyEditorValue() {
						if (!editor.isDialogActive()) {
							setValue(element, editor.doGetValue());
							editor.deactivate();
						}
					}
				});
				return editor;
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

		for (int i = 3; i <= 10; ++i) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.setLabelProvider(new ColumnLabelProvider() {
				public String getText(Object element) {
					return "";
				}
			});
			column.getColumn().setText("column" + i);
			column.getColumn().setWidth(100);
		}

		viewer.getTable().addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				ViewerCell cell = viewer.getCell(new Point(e.x, e.y));
				if (cell == null)
					return;
				if (cell.getColumnIndex() < 2)
					return;
				cell.setText(e.x + ", " + e.y);
			}
		});

		reg.put("sans", new FontData[] { new FontData("sans", 26, 0) });
		// viewer.getTree().setFont(reg.get("sans"));
		viewer.getTable().setHeaderVisible(true);
		viewer.setInput(SampleTreeNode.createSample());
		return viewer.getControl();
	}
}
