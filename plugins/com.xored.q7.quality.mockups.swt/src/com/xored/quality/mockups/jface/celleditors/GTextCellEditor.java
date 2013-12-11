package com.xored.quality.mockups.jface.celleditors;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

public class GTextCellEditor extends TextCellEditor {

    private Object original;

    public GTextCellEditor() {
        super();
    }

    public GTextCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    public GTextCellEditor(Composite parent) {
        super(parent);
    }

    @Override
    protected void doSetValue(Object value) {
        if (value instanceof Integer)
            value = value.toString();
        if (value instanceof Double)
            value = value.toString();
        if (value == null)
            value = "";
        original = value;
        super.doSetValue(value.toString());
    }

    @Override
    protected void fireApplyEditorValue() {
        Object value = getValue();
        if (!value.equals(original))
            super.fireApplyEditorValue();
        else 
            super.fireCancelEditor();
    }
}
