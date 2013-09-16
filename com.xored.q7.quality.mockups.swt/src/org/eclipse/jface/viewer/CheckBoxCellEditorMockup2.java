package org.eclipse.jface.viewer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CheckBoxCellEditorMockup2 extends BaseMockupPart {

	public static final String NAME = "Name";
	public static final String MALE = "Male?";
	public static final String AGE = "Age Range";
	public static final String SHIRT_COLOR = "Shirt Color";
	public static final String[] PROPS = { NAME, MALE, AGE, SHIRT_COLOR };
	@SuppressWarnings("rawtypes")
	private java.util.List studentList = new ArrayList();

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new GridLayout(1, false));
		Button newPerson = new Button(parent, SWT.PUSH);
		newPerson.setText("Create New Person");

		final TableViewer tv = new TableViewer(parent, SWT.FULL_SELECTION);
		tv.setContentProvider(new PersonContentProvider());
		tv.setLabelProvider(new StudentLabelProvider());
		tv.setInput(studentList);
		Table table = tv.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		new TableColumn(table, SWT.CENTER).setText(NAME);
		new TableColumn(table, SWT.CENTER).setText(MALE);
		new TableColumn(table, SWT.CENTER).setText(AGE);
		new TableColumn(table, SWT.CENTER).setText(SHIRT_COLOR);

		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		newPerson.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent event) {
				Student p = new Student();
				p.setName("Name");
				p.setMale(true);
				p.setAgeRange(Integer.valueOf("0"));
				p.setShirtColor(new RGB(255, 0, 0));
				studentList.add(p);
				tv.refresh();
			}
		});

		CellEditor[] editors = new CellEditor[4];
		editors[0] = new TextCellEditor(table);
		editors[1] = new CheckboxCellEditor(table);
		editors[2] = new ComboBoxCellEditor(table, AgeRange.INSTANCES, SWT.READ_ONLY);
		editors[3] = new ColorCellEditor(table);

		tv.setColumnProperties(PROPS);
		tv.setCellModifier(new StudentCellModifier(tv));
		tv.setCellEditors(editors);

		return null;
	}

	class StudentCellModifier implements ICellModifier {
		private Viewer viewer;

		public StudentCellModifier(Viewer viewer) {
			this.viewer = viewer;
		}

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			Student p = (Student) element;
			if (CheckBoxCellEditorMockup2.NAME.equals(property))
				return p.getName();
			else if (CheckBoxCellEditorMockup2.MALE.equals(property))
				return Boolean.valueOf(p.isMale());
			else if (CheckBoxCellEditorMockup2.AGE.equals(property))
				return p.getAgeRange();
			else if (CheckBoxCellEditorMockup2.SHIRT_COLOR.equals(property))
				return p.getShirtColor();
			else
				return null;
		}

		public void modify(Object element, String property, Object value) {
			if (element instanceof Item)
				element = ((Item) element).getData();

			Student p = (Student) element;
			if (CheckBoxCellEditorMockup2.NAME.equals(property))
				p.setName((String) value);
			else if (CheckBoxCellEditorMockup2.MALE.equals(property))
				p.setMale(((Boolean) value).booleanValue());
			else if (CheckBoxCellEditorMockup2.AGE.equals(property))
				p.setAgeRange((Integer) value);
			else if (CheckBoxCellEditorMockup2.SHIRT_COLOR.equals(property))
				p.setShirtColor((RGB) value);

			viewer.refresh();
		}
	}

	class PersonContentProvider implements IStructuredContentProvider {
		@SuppressWarnings("rawtypes")
		public Object[] getElements(Object inputElement) {

			return ((ArrayList) inputElement).toArray();
			// return (List)inputElement.toArray();

		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class Student {
		private String name;
		private boolean male;
		private Integer ageRange;
		private RGB shirtColor;

		public Integer getAgeRange() {
			return ageRange;
		}

		public void setAgeRange(Integer ageRange) {
			this.ageRange = ageRange;
		}

		public boolean isMale() {
			return male;
		}

		public void setMale(boolean male) {
			this.male = male;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public RGB getShirtColor() {
			return shirtColor;
		}

		public void setShirtColor(RGB shirtColor) {
			this.shirtColor = shirtColor;
		}
	}

	class StudentLabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Student person = (Student) element;
			switch (columnIndex) {
			case 0:
				return person.getName();
			case 1:
				return Boolean.toString(person.isMale());
			case 2:
				return AgeRange.INSTANCES[person.getAgeRange().intValue()];
			case 3:
				return person.getShirtColor().toString();
			}
			return null;
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return "Check Box Cell Editor Test";
	}

}

class AgeRange {
	public static final String NONE = "";
	public static final String BABY = "0 - 3";
	public static final String TODDLER = "4 - 7";
	public static final String CHILD = "8 - 12";
	public static final String TEENAGER = "13 - 19";
	public static final String ADULT = "20 - ?";

	public static final String[] INSTANCES = { NONE, BABY, TODDLER, CHILD,
			TEENAGER, ADULT };
}
