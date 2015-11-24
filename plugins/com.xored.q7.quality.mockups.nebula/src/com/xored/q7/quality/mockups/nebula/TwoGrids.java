package com.xored.q7.quality.mockups.nebula;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/**
 * Two Nebula Grids next to each other.
 */
public class TwoGrids extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.FILL).grab(true, true).applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(content);

		Grid grid1 = new Grid(content, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid1.setHeaderVisible(true);

		GridColumn treeColumn = new GridColumn(grid1, SWT.NONE);
		treeColumn.setWidth(120);
		treeColumn.setText("Name");
		treeColumn.setTree(true);

		GridItem item = new GridItem(grid1, SWT.NONE);
		item.setText("Item");
		GridItem childItem1 = new GridItem(item, SWT.NONE);
		childItem1.setText("Child 1");
		GridItem childItem2 = new GridItem(item, SWT.NONE);
		childItem2.setText("Child 2");

		Grid grid2 = new Grid(content, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid2.setHeaderVisible(true);

		GridColumn emptyHeaderColumn = new GridColumn(grid2, SWT.CHECK);
		emptyHeaderColumn.setWidth(30);

		GridItem item2 = new GridItem(grid2, SWT.NONE);
		item2.setText("Item in Grid 2");

		GridDataFactory.fillDefaults().grab(true, true).applyTo(grid1);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(grid2);
		return content;
	}

}
