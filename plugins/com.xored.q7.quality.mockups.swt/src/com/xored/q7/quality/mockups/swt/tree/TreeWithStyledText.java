package com.xored.q7.quality.mockups.swt.tree;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TreeWithStyledText extends BaseMockupPart {
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		Tree tree = new Tree(composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tree.setHeaderVisible(true);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).span(2, 1)
				.grab(true, true).applyTo(tree);

		final TreeViewer viewer = new TreeViewer(tree);
		TreeColumn column1 = new TreeColumn(tree, SWT.NONE);
		column1.setText("Column with checkbox");
		column1.setWidth(200);
		TreeColumn column2 = new TreeColumn(tree, SWT.NONE);
		column2.setText("Column without checkbox");
		column2.setWidth(200);

		final TreeStyledTextLabelProvider styleProvider = new TreeStyledTextLabelProvider();
		viewer.setLabelProvider(styleProvider);

		viewer.setContentProvider(new TreeStyledTextContentProvider());
		String[] text = new String[2];
		for (int i = 0; i < text.length; i++) {
			text[i] = "Sample text " + i;
		}
		viewer.setInput(text);

		createTreePropertiesControls(viewer, composite);

		return null;
	}

	private void createTreePropertiesControls(final TreeViewer viewer, final Composite composite) {
		final TreeStyledTextLabelProvider styleProvider = (TreeStyledTextLabelProvider) viewer.getLabelProvider();

		VerifyListener digitVerifyListener = new VerifyListener() {
			@Override
			public void verifyText(final VerifyEvent event) {
				switch (event.keyCode) {
				case SWT.BS:
				case SWT.DEL:
				case SWT.HOME:
				case SWT.END:
				case SWT.ARROW_LEFT:
				case SWT.ARROW_RIGHT:
					return;
				}
				if (!Character.isDigit(event.character)) {
					event.doit = false;
				}
			}
		};

		final Button enableStyleBtn = new Button(composite, SWT.CHECK);
		enableStyleBtn.setText("Enable style");
		enableStyleBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				styleProvider.setEnableStyle(enableStyleBtn.getSelection());
				viewer.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		enableStyleBtn.setSelection(true);
		GridDataFactory.swtDefaults()
				.grab(true, false).span(2, 1).applyTo(enableStyleBtn);

		final Text startPosText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		startPosText.setText("0");
		startPosText.addVerifyListener(digitVerifyListener);
		GridDataFactory.swtDefaults().hint(50, SWT.DEFAULT)
				.grab(false, false).applyTo(startPosText);
		Button updateStartBtn = new Button(composite, SWT.NONE);
		updateStartBtn.setText("Set style start index");
		updateStartBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int start = Integer.parseInt(startPosText.getText());
					styleProvider.setStart(start);
					viewer.refresh();
				} catch (Throwable t) {
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		GridDataFactory.swtDefaults()
				.grab(true, false).applyTo(updateStartBtn);

		final Text lengthText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		lengthText.setText("5");
		lengthText.addVerifyListener(digitVerifyListener);
		GridDataFactory.swtDefaults().hint(50, SWT.DEFAULT)
				.grab(false, false).applyTo(lengthText);
		Button updateLengthBtn = new Button(composite, SWT.NONE);
		updateLengthBtn.setText("Set style length");
		updateLengthBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int length = Integer.parseInt(lengthText.getText());
					styleProvider.setLength(length);
					viewer.refresh();
				} catch (Throwable t) {
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		GridDataFactory.swtDefaults()
				.grab(true, false).applyTo(updateLengthBtn);

		final Button enableUnderlineBtn = new Button(composite, SWT.CHECK);
		enableUnderlineBtn.setText("Enable underline");
		enableUnderlineBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				styleProvider.setUnderline(enableUnderlineBtn.getSelection());
				viewer.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button toggleUnderlineBtn = new Button(composite, SWT.NONE);
		toggleUnderlineBtn.setText("Change underline style");
		toggleUnderlineBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (styleProvider.getUnderlineStyle()) {
				case SWT.UNDERLINE_SINGLE:
					styleProvider.setUnderlineStyle(SWT.UNDERLINE_DOUBLE);
					break;
				case SWT.UNDERLINE_DOUBLE:
					styleProvider.setUnderlineStyle(SWT.UNDERLINE_ERROR);
					break;
				case SWT.UNDERLINE_ERROR:
					styleProvider.setUnderlineStyle(SWT.UNDERLINE_SQUIGGLE);
					break;
				case SWT.UNDERLINE_SQUIGGLE:
					styleProvider.setUnderlineStyle(SWT.UNDERLINE_LINK);
					break;
				case SWT.UNDERLINE_LINK:
					styleProvider.setUnderlineStyle(SWT.UNDERLINE_SINGLE);
					break;
				}
				viewer.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		final Button enableStrikeoutBtn = new Button(composite, SWT.CHECK);
		enableStrikeoutBtn.setText("Enable strikeout");
		enableStrikeoutBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				styleProvider.setStrikeout(enableStrikeoutBtn.getSelection());
				viewer.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		GridDataFactory.swtDefaults()
				.span(2, 1).applyTo(enableStrikeoutBtn);

		Button toggleBorderBtn = new Button(composite, SWT.NONE);
		toggleBorderBtn.setText("Change border style");
		toggleBorderBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (styleProvider.getBorderStyle()) {
				case SWT.BORDER_SOLID:
					styleProvider.setBorderStyle(SWT.BORDER_DASH);
					break;
				case SWT.BORDER_DASH:
					styleProvider.setBorderStyle(SWT.BORDER_DOT);
					break;
				case SWT.BORDER_DOT:
					styleProvider.setBorderStyle(SWT.NONE);
					break;
				case SWT.NONE:
					styleProvider.setBorderStyle(SWT.BORDER_SOLID);
					break;
				}
				viewer.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		GridDataFactory.swtDefaults()
				.span(2, 1).applyTo(toggleBorderBtn);

		Button genForeColorBtn = new Button(composite, SWT.NONE);
		genForeColorBtn.setText("Change foreground color...");
		genForeColorBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(composite.getShell());
				dlg.setText("Choose foreground color");
				dlg.setRGB(styleProvider.getTextColor());
				RGB rgb = dlg.open();
				if (rgb != null) {
					styleProvider.setTextColor(rgb);
					viewer.refresh();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button genBackColorBtn = new Button(composite, SWT.NONE);
		genBackColorBtn.setText("Change background color...");
		genBackColorBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(composite.getShell());
				dlg.setText("Choose background color");
				dlg.setRGB(styleProvider.getBackgroundColor());
				RGB rgb = dlg.open();
				if (rgb != null) {
					styleProvider.setBackgroundColor(rgb);
					viewer.refresh();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button genStrikeoutColorBtn = new Button(composite, SWT.NONE);
		genStrikeoutColorBtn.setText("Change strikeout color...");
		genStrikeoutColorBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(composite.getShell());
				dlg.setText("Choose strikeout color");
				dlg.setRGB(styleProvider.getStrikeoutColor());
				RGB rgb = dlg.open();
				if (rgb != null) {
					styleProvider.setStrikeoutColor(rgb);
					viewer.refresh();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button genBorderBtn = new Button(composite, SWT.NONE);
		genBorderBtn.setText("Change border color...");
		genBorderBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(composite.getShell());
				dlg.setText("Choose border color");
				dlg.setRGB(styleProvider.getBorderColor());
				RGB rgb = dlg.open();
				if (rgb != null) {
					styleProvider.setBorderColor(rgb);
					viewer.refresh();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button genUnderlineBtn = new Button(composite, SWT.NONE);
		genUnderlineBtn.setText("Change underline color...");
		genUnderlineBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(composite.getShell());
				dlg.setText("Choose underline color");
				dlg.setRGB(styleProvider.getUnderlineColor());
				RGB rgb = dlg.open();
				if (rgb != null) {
					styleProvider.setUnderlineColor(rgb);
					viewer.refresh();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
}
