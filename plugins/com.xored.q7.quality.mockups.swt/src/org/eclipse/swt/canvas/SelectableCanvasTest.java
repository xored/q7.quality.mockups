package org.eclipse.swt.canvas;


import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SelectableCanvasTest extends BaseMockupPart {

    @Override
    public Control construct(Composite parent) {

        // Composite composite = new Composite(parent, SWT.NONE);
        SelectableCanvas canvas = new SelectableCanvas(parent, SWT.NONE);
        Text logText = new Text(parent, SWT.NONE);
        logText.setText("unchecked");
       
       canvas.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (canvas.isChecked){
                    logText.setText("checked");
                } else {
                    logText.setText("unchecked");
                }
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        return null;
    }

    private class SelectableCanvas extends Canvas {

        private static final String COLOR_WHITE = "ColorWhite"; //$NON-NLS-1$
        private static final String COLOR_BLACK = "ColorBlack"; //$NON-NLS-1$

        private boolean gotMouseDown = false;
        private boolean isChecked = false;

        public SelectableCanvas(final Composite parent, final int styles) {
            super(parent, styles);
            createListeners();
            JFaceResources.getColorRegistry().put(COLOR_WHITE, new RGB(0, 0, 0));
            JFaceResources.getColorRegistry().put(COLOR_BLACK, new RGB(255, 255, 255));
        }

        public void addSelectionListener(final SelectionListener listener) {
            addListener(SWT.Selection, new TypedListener(listener));
        }

        private void createListeners() {
            addPaintListener(e -> {
                final GC gc = e.gc;
                Color backgroundColor;
                if (getEnabled()) {
                    backgroundColor = getBackground();
                    if (isChecked) {
                        backgroundColor = JFaceResources.getColorRegistry().get(COLOR_BLACK);
                    } else {
                        backgroundColor = JFaceResources.getColorRegistry().get(COLOR_WHITE);
                    }
                } else {
                    backgroundColor = getBackground();
                }

                final Rectangle clientArea = this.getClientArea();
                gc.setBackground(backgroundColor);
                gc.fillRectangle(clientArea);
            });

            addMouseListener(new MouseListener() {
                @Override
                public void mouseDown(final MouseEvent e) {
                    setFocus();
                    gotMouseDown = true;
                }

                @Override
                public void mouseDoubleClick(final MouseEvent e) {
                }

                @Override
                public void mouseUp(final MouseEvent e) {
                    if (gotMouseDown) {
                        final Rectangle ca = getClientArea();
                        if (e.x >= ca.x && e.x < ca.x + ca.width && e.y >= ca.y && e.y < ca.height && e.button == 1) {
                            isChecked = !isChecked;
                            redraw();
                            notifyListeners(SWT.Selection, new Event());

                        }
                    }
                    gotMouseDown = false;
                }
            });
        }

    }

}
