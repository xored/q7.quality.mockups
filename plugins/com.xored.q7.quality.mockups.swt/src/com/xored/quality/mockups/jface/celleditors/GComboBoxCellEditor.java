package com.xored.quality.mockups.jface.celleditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;



public class GComboBoxCellEditor extends ComboBoxCellEditor {

    public GComboBoxCellEditor(Composite parent, String[] items,int style) {
        super(parent, items,style);
        
    }

    @Override
    protected Control createControl(Composite parent) {
    	GCombo combo = (GCombo) super.createControl(parent);	
    	combo.setIncrementalSearch(true);
    	int visibleItemCount = 15;
    	// Set the visible count to a max of 1/3 of the monitor height or 15
    	if ((getStyle() & SWT.SIMPLE) == 0) {
    		int itemHeight = combo.getItemHeight();
    		if (itemHeight != 0) {
    			int maxHeight = combo.getMonitor().getClientArea().height / 3;
    			visibleItemCount = Math.max(visibleItemCount, maxHeight / itemHeight);
    		}
    	}
    	combo.setVisibleItemCount(visibleItemCount);
    	
    	return combo;
    }

}

