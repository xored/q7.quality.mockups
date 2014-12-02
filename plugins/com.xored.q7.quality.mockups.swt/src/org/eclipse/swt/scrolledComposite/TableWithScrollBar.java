package org.eclipse.swt.scrolledComposite;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableWithScrollBar extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		Table table = new Table (composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL );
	    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		.grab(true, true).applyTo(table);
//	    table.setSize(200, 200);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
	    String[] titles = { " ", "C", "!", "Description", "Resource", "In Folder", "Location", "Date", "Test", "Test1", "Test2" };
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	      column.setWidth(100);
	    }
	    for (int i = 0; i < 100; i++) {
	      TableItem item = new TableItem(table, SWT.NONE);
	      item.setText(0, "x");
	      item.setText(1, "y");
	      item.setText(2, "!");
	      item.setText(3, "test description " + i);
	      item.setText(4, "almost everywhere");
	      item.setText(5, "some.folder_" + i);
	      item.setText(6, "line " + i + " in nowhere");
	      item.setText(7, "02.04.2013");
	      item.setText(8, "test line for scrolling (1)");
	      item.setText(9, "test line for scrolling (2)");
	      item.setText(10, "test line for scrolling (3)");
	    }
	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }     
			    
		return null;
			
		}
		
}
