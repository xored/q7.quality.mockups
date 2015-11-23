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

		GridColumn emptyHeaderColumn = new GridColumn(grid, SWT.NONE);
		GridColumn duplicateHeaderColumn1 = new GridColumn(grid, SWT.NONE);
		GridColumn duplicateHeaderColumn2 = new GridColumn(grid, SWT.NONE);
		emptyHeaderColumn.setWidth(120);
		duplicateHeaderColumn1.setWidth(120);
		duplicateHeaderColumn2.setWidth(120);
		duplicateHeaderColumn1.setText("Duplicated name");
		duplicateHeaderColumn2.setText("Duplicated name");

		GridItem item = new GridItem(grid, SWT.NONE);
		item.setText("Item");
		GridItem editItem = new GridItem(grid, SWT.NONE);
		editItem.setText("Editable Item");

		GridEditor editor = new GridEditor(grid);
		editor.grabHorizontal = true;
		Text text = new Text(grid, SWT.NONE);
		text.setText("Editable");
		editor.setEditor(text, editItem, 1);

		GridEditor editor2 = new GridEditor(grid);
		editor2.grabHorizontal = true;
		Text text2 = new Text(grid, SWT.NONE);
		text2.setText("Editable 2");
		editor2.setEditor(text2, editItem, 2);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(grid);
		return content;
	}

}
