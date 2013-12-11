package com.xored.q7.quality.mockups.swt.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class DialogCellEditMouseListenerPart extends DialogCellEditPart {
	@Override
	protected void addMouseListener(final TableViewer viewer, final int colCount) {
		viewer.getTable().addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				ViewerCell cell = viewer.getCell(new Point(e.x, e.y));
				if (cell == null)
					return;
				if (cell.getColumnIndex() < colCount)
					return;
				cell.setText(e.x + ", " + e.y);
			}
		});
	}

	@Override
	public String getLabel() {
		return super.getLabel() + " mouse listener";
	}
}
