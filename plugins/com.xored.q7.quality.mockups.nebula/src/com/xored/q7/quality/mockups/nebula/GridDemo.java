package com.xored.q7.quality.mockups.nebula;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridEditor;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/**
 * A Nebula Grid with an empty and a duplicated header column, a few items and text boxes.
 */
public class GridDemo extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(content);

		Grid grid = new Grid(content, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setHeaderVisible(true);

		GridColumn treeColumn = new GridColumn(grid, SWT.NONE);
		treeColumn.setWidth(120);
		treeColumn.setText("Name");
		treeColumn.setTree(true);

		GridColumn emptyHeaderColumn = new GridColumn(grid, SWT.CHECK);
		emptyHeaderColumn.setWidth(30);

		GridColumn duplicateHeaderColumn = new GridColumn(grid, SWT.NONE);
		duplicateHeaderColumn.setWidth(120);
		duplicateHeaderColumn.setText("Name");

		GridItem item = new GridItem(grid, SWT.NONE);
		item.setText("Item");
		GridItem editItem = new GridItem(grid, SWT.NONE);
		editItem.setText("Editable Item");

		GridItem childItem1 = new GridItem(item, SWT.NONE);
		childItem1.setText("Child 1");
		GridItem childItem2 = new GridItem(item, SWT.NONE);
		childItem2.setText("Child 2");

		GridEditor editor = new GridEditor(grid);
		editor.grabHorizontal = true;
		Text text = new Text(grid, SWT.NONE);
		text.setText("Editable");
		editor.setEditor(text, editItem, 2);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(grid);
		return content;
	}

}
