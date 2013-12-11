package com.xored.quality.mockups.jface.celleditors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MultiLineCellEditor extends CellEditor {
	Shell shell;

	private FocusListener focus;

	DisposeListener dispose;

	private boolean isOpen;

	Rectangle rectangle;

	private String value;

	public MultiLineCellEditor(Composite parent, String value) {
		super(parent);
		this.value = value;
		focus = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				deactivate();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		};
		dispose = new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				shell.removeDisposeListener(dispose);
			}
		};
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite cell = new Composite(parent, SWT.NONE);
		cell.setLayout(new DialogCellLayout());
		return cell;
	}

	@Override
	protected Object doGetValue() {
		return value;
	}

	@Override
	protected void doSetFocus() {
	}

	@Override
	protected void focusLost() {
		super.focusLost();
	}

	@Override
	public void activate() {
		if (rectangle != null && !isOpen) {
			isOpen = true;
			shell = new Shell(getControl().getShell(), SWT.NONE);
			shell.setBounds(rectangle);
			shell.setLayout(new FillLayout());
			Text text = new Text(shell, SWT.MULTI);
			if (value != null) {
				text.setText(value);
			}
			shell.update();
			shell.layout();
			shell.addDisposeListener(dispose);
			text.addFocusListener(focus);
			shell.open();
			super.activate();
		}
	}

	@Override
	public void deactivate() {
		if (isOpen) {
			isOpen = false;
			Display.getCurrent().syncExec(new Runnable() {

				@Override
				public void run() {
					if (shell != null && !shell.isDisposed()) {
						setValue();
						fireApplyEditorValue();
						if (!shell.isDisposed())
							shell.close();
					}
				}

			});
		}
		super.deactivate();
	}

	void setValue() {
		if (shell != null && !shell.isDisposed()) {
			Text text = (Text) shell.getChildren()[0];
			String str = text.getText();
			value = str.replaceAll("\\\\r\\\\n", "\n");
		}
	}

	@Override
	public void dispose() {
		if (isOpen) {
			deactivate();
		}
		if (shell != null && !shell.isDisposed()) {
			shell.dispose();
		}
		super.dispose();
	}

	@Override
	protected void doSetValue(Object value) {
	}

	class DialogCellLayout extends Layout {

		@Override
		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point real = editor.toDisplay(bounds.x, bounds.y);
			rectangle = new Rectangle(real.x, real.y, bounds.width, 55);
			activate();
		}

		@Override
		public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}
			return new Point(SWT.DEFAULT, SWT.DEFAULT);
		}
	}

}
