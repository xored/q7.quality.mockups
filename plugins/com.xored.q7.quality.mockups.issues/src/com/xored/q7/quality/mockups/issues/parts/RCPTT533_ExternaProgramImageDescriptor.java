package com.xored.q7.quality.mockups.issues.parts;

import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.misc.ExternalProgramImageDescriptor;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT533_ExternaProgramImageDescriptor extends BaseMockupPart {
	@Override
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.ON_TOP);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		final Button test = new Button(composite, SWT.NONE);
		test.setText("Test");
		
		final Text text = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		test.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createImage();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				createImage();
			}
			private void createImage() {
				final PrintStream outOld = System.out;
				final ByteArrayOutputStream stream = new ByteArrayOutputStream();
				final PrintStream out = new PrintStream(stream);
				System.setOut(out);
				for (Program program : Program.getPrograms()) {
					new ExternalProgramImageDescriptor(program).createImage();
				}
				System.setOut(outOld);
				text.setText(stream.toString());
			}
		});

		return composite;
	}
}
