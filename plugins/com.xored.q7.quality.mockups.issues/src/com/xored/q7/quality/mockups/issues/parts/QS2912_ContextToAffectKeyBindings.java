package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.core.commands.contexts.ContextManagerEvent;
import org.eclipse.core.commands.contexts.IContextManagerListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2912_ContextToAffectKeyBindings extends BaseMockupPart {
	private static final String CONTEXT_ID = "com.xored.q7.quality.mockups.issues.nodelete";
	private static final String INITIAL_TEXT = "Initial text";

	@Override
	public Control construct(Composite parent) {
		final IContextService contextService = (IContextService) PlatformUI
				.getWorkbench().getService(IContextService.class);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(parent);
		new Label(parent, SWT.NONE).setText("No context");
		new Label(parent, SWT.NONE).setText("Nodelete context");
		new Label(parent, SWT.NONE).setText("Active contexts");
		GridDataFactory grab = GridDataFactory.fillDefaults().grab(true, false);
		Text text1 = new Text(parent, SWT.NONE);
		text1.setText(INITIAL_TEXT);
		grab.applyTo(text1);
		Text text2 = new Text(parent, SWT.NONE);
		text2.setText(INITIAL_TEXT);
		grab.applyTo(text2);
		text2.addFocusListener(new FocusListener() {

			private IContextActivation activation;

			void free() {
				if (activation != null) {
					contextService.deactivateContext(activation);
					activation = null;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				free();
			}

			@Override
			public void focusGained(FocusEvent e) {
				free();
				activation = contextService.activateContext(CONTEXT_ID);
			}
		});
		final ListViewer activeContextsViewer = new ListViewer(parent, SWT.READ_ONLY);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(activeContextsViewer.getControl());
		activeContextsViewer.setContentProvider(new ArrayContentProvider());
		activeContextsViewer.setLabelProvider(new LabelProvider());
		activeContextsViewer.setInput(contextService.getActiveContextIds());

		contextService.addContextManagerListener(new IContextManagerListener() {

			@Override
			public void contextManagerChanged(
					ContextManagerEvent contextManagerEvent) {
				if (!activeContextsViewer.getControl().isDisposed())
					activeContextsViewer.setInput(contextService
						.getActiveContextIds());
			}
		});

		return null;
	}

}
