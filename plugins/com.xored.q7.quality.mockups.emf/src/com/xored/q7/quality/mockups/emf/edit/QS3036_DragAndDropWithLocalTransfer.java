package com.xored.q7.quality.mockups.emf.edit;

import java.util.ArrayList;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS3036_DragAndDropWithLocalTransfer extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(parent);
		Label label = new Label(parent, SWT.NONE);
		label.setText("Drag different items from right to left.");
		GridDataFactory.fillDefaults().span(2, 1).applyTo(label);
		final ListViewer tree1 = new ListViewer(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tree1.getControl());
		tree1.setContentProvider(new ArrayContentProvider());
		tree1.setLabelProvider(new LabelProvider());
		tree1.addDragSupport(DND.DROP_MOVE,
	            new Transfer[] { LocalTransfer.getInstance() }, new DragSourceAdapter() {
	                @Override
	                public void dragSetData(DragSourceEvent event) {
	                    event.data = ((IStructuredSelection) tree1.getSelection()).toList();
	                }});
		
		final String[] sourceData = new String[]{"First", "Second", "Third"};
		tree1.setInput(sourceData);
		
		final ListViewer tree2 = new ListViewer(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tree2.getControl());
		tree2.setContentProvider(new ArrayContentProvider());
		tree2.setLabelProvider(new LabelProvider());
		final ArrayList<String> targetData = new ArrayList<String>();
		tree2.setInput(targetData);
		tree2.addDropSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] {LocalTransfer.getInstance() }, new ViewerDropAdapter(tree2) {

            @Override
            public boolean performDrop(Object data) {
            	Iterable<?> dataList = (Iterable<?>) data;
            	for (Object datum: dataList) {
            		targetData.add((String)datum);
            	}
            	tree2.refresh();
                return true;
            }

            @Override
            public boolean validateDrop(Object target, int operation, TransferData transferType) {
                return true;
            }
		});
		return parent;
	}

}
