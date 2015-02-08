package org.eclipse.swt.list;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/*
 *  Mockup to test events on SWT List control
 */
public class ListEventsMockup extends BaseMockupPart {

	private List list;
	private Text txt;

	@Override
	public Control construct(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		list = new List(container, SWT.FILL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		list.add("l1");
		list.add("l2");
		list.add("l3");

		list.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String outString = "Select" + "\n";
				txt.append(outString);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String outString = "Select" + "\n";
				txt.append(outString);
			}
		});
		list.addMouseMoveListener(new MouseMoveListener() {
			@Override
			public void mouseMove(MouseEvent e) {
				String outString = "MouseMove" + "\n";
				txt.append(outString);
			}
		});
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String outString = "MouseDoubleClick" + "\n";
				txt.append(outString);
			}

			@Override
			public void mouseDown(MouseEvent e) {
				String outString = "MouseDown" + "\n";
				txt.append(outString);
			}

			@Override
			public void mouseUp(MouseEvent e) {
				String outString = "MouseUp" + "\n";
				txt.append(outString);
			}
		});

		txt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		txt.setFocus();
		txt.setText("");

		return null;
	}

}
