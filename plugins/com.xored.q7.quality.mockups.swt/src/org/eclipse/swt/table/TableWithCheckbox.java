package org.eclipse.swt.table;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableWithCheckbox extends BaseMockupPart {
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
//	    GridLayout layout = new GridLayout();
		
		Table table = new Table (composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		TableColumn column1 = new TableColumn(table, SWT.NONE);
	    column1.setText("Column with checkbox ");
	    column1.setWidth(100);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
	    column2.setText("Description");
	    column2.setWidth(100);

		for (int i=0; i<12; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText ("Item " + i);
		}
		Rectangle clientArea = composite.getClientArea ();
		table.setBounds (clientArea.x, clientArea.y, 100, 100);
		table.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
				System.out.println (event.item + " " + string);
			}
		});
		
		return null;
		
	}
			
	@Override
	public String getLabel() {
	// TODO Auto-generated method stub
		return "ComboBox Test";
	}

}
