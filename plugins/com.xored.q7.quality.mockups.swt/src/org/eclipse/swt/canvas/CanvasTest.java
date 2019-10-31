package org.eclipse.swt.canvas;


import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CanvasTest extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {

		// Composite composite = new Composite(parent, SWT.NONE);
		Canvas canvas = new Canvas(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(canvas);

		// canvas.setBounds(20, 20, 1000, 1000);
		view.getViewSite().getActionBars().getStatusLineManager().setMessage(null);
		canvas.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				view.getViewSite().getActionBars().getStatusLineManager().setMessage(null);
			}
		});

		Button button = new Button(canvas, SWT.PUSH);
		button.setBounds(10, 10, 200, 40);
		button.setText("You can place widgets on a canvas");

		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent event) {
				view.getViewSite().getActionBars().getStatusLineManager().setMessage(
						"Click on Canvas. MouseButton: " + event.button + ". StateMask: " + event.stateMask);
			}

			@Override
			public void mouseDoubleClick(MouseEvent event) {
				view.getViewSite().getActionBars().getStatusLineManager().setMessage(
						"Double-click on Canvas. MouseButton: " + event.button + ". StateMask: " + event.stateMask);
			}
		});

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// Do some drawing
				Rectangle rect = ((Canvas) e.widget).getBounds();
				e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
				e.gc.drawFocus(5, 5, rect.width - 10, rect.height - 10);
				e.gc.drawText("You can draw text directly on a canvas", 60, 60);
				e.gc.drawPolygon(new int[] { 125, 105, 145, 145, 105, 145 });

				e.gc.drawRectangle(205, 205, 290, 245);
				e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_CYAN));
				e.gc.fillRectangle(206, 206, 289, 244);

			}
		});

		return null;
	}
}
