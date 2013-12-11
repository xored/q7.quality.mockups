package com.xored.q7.quality.mockups.swt.table;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeContentProvider;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;
import com.xored.quality.mockups.jface.celleditors.GCheckboxCellEditor;
import com.xored.quality.mockups.jface.celleditors.GComboBoxCellEditor;

public class CustomComboCellEditPart extends BaseMockupPart {

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

		createCol1(viewer);

		createCol2(viewer);

		createCol3(viewer);

		final int colCount = viewer.getTable().getColumnCount();
		for (int i = colCount; i <= 10; ++i) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.setLabelProvider(new ColumnLabelProvider() {
				public String getText(Object element) {
					return "";
				}
			});
			column.getColumn().setText("column" + i);
			column.getColumn().setWidth(100);
		}

		addMouseListener(viewer, colCount);

		reg.put("sans", new FontData[] { new FontData("sans", 26, 0) });
		// viewer.getTree().setFont(reg.get("sans"));
		viewer.getTable().setHeaderVisible(true);
		viewer.setInput(SampleTreeNode.createSample());
		return viewer.getControl();
	}

	protected void addMouseListener(final TableViewer viewer, final int colCount) {
	}

	private void createCol2(final TableViewer viewer) {
		TableViewerColumn col2 = new TableViewerColumn(viewer, SWT.NONE);
		col2.getColumn().setText("column2");
		col2.setEditingSupport(new EditingSupport(viewer) {

			protected void setValue(Object element, Object value) {
				if (value instanceof String) {
					((SampleTreeNode) element).column2 = (String) value;
				}
				else if( value instanceof Integer) {
					((SampleTreeNode) element).column2 = "" + (Integer) value;
				}
				
				viewer.update(element, null);
			}

			protected Object getValue(Object element) {
				return ((SampleTreeNode) element).column2;
			}

			protected CellEditor getCellEditor(final Object element) {
				final GComboBoxCellEditor editor = new GComboBoxCellEditor(
						viewer.getTable(), new String[] { "One", "Two", "Tree" }, SWT.SIMPLE | SWT.DROP_DOWN);
				editor.addListener(new ICellEditorListener() {

					public void editorValueChanged(boolean oldValidState,
							boolean newValidState) {
					}

					public void cancelEditor() {
					}

					public void applyEditorValue() {
						if (!editor.isDirty()) {
							setValue(element, editor.getValue());
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
	}

	private void createCol1(final TableViewer viewer) {
		viewer.setContentProvider(new SampleTreeContentProvider());
		TableViewerColumn col1 = new TableViewerColumn(viewer, SWT.NONE);
		col1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column1;
			}
		});
		col1.getColumn().setText("column1");
		col1.getColumn().setWidth(200);
	}

	private void createCol3(final TableViewer viewer) {
		viewer.setContentProvider(new SampleTreeContentProvider());
		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column3;
			}
		});
		col.getColumn().setText("column3");
		col.getColumn().setWidth(100);
		col.setEditingSupport(new EditingSupport(viewer) {
			@Override
			protected void setValue(Object element, Object value) {
				((SampleTreeNode) element).column3 = (String) value;
				viewer.update(element, null);
			}

			@Override
			protected Object getValue(Object element) {
				return ((SampleTreeNode) element).column3;
			}

			@Override
			protected CellEditor getCellEditor(final Object element) {
				final GCheckboxCellEditor editor = new GCheckboxCellEditor(viewer.getTable(),
						((SampleTreeNode) element).column3);
				editor.addListener(new ICellEditorListener() {
					@Override
					public void editorValueChanged(boolean oldValidState, boolean newValidState) {
					}

					@Override
					public void cancelEditor() {
					}

					@Override
					public void applyEditorValue() {
						setValue(element, editor.getValue());
					}
				});
				return editor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
	}
}
