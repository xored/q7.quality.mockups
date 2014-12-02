package com.xored.q7.quality.mockups.issues.parts;

import static org.eclipse.swt.SWT.FILL;
import static org.eclipse.swt.SWT.NONE;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.QualityIssuesPlugin;

public class SUP251_HandWrittenCheckboxTree extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		checked = QualityIssuesPlugin.getImageDescriptor("icons/checked.gif")
				.createImage();
		unchecked = QualityIssuesPlugin.getImageDescriptor(
				"icons/unchecked.gif").createImage();

		Composite content = new Composite(parent, NONE);
		GridDataFactory.swtDefaults().grab(true, true).align(FILL, FILL)
				.applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(content);

		final Tree tree = new Tree(content, SWT.NONE);

		GridDataFactory.swtDefaults().grab(true, true).align(FILL, FILL)
				.applyTo(tree);

		TreeItem parentItem = new TreeItem(tree, NONE);
		parentItem.setText("parent");
		parentItem.setImage(unchecked);
		TreeItem childItem = new TreeItem(parentItem, NONE);
		childItem.setText("child");
		childItem.setImage(unchecked);

		tree.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event e) {
				Point point = new Point(e.x, e.y);
				TreeItem item = tree.getItem(point);
				if (item == null) {
					return;
				}

				if (item.getImageBounds(0).contains(point)) {
					item.setImage(item.getImage() == checked ? unchecked
							: checked);
				}
			}
		});
		return content;
	}

	private Image checked = null;
	private Image unchecked = null;

}
