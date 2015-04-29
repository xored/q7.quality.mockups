package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT531_BrowserDialogWithTabFolder extends BaseMockupPart {
	
	@Override
	public Control construct(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.ON_TOP);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		final Button openShell = new Button(composite, SWT.NONE);
		openShell.setText("Open shell");
		
		openShell.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_openShell(parent);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				_openShell(parent);
			}
		});
		return null;
	}
	
	private void _openShell(Composite parent) {
		final Shell shell = new Shell(parent.getShell(), SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(shell);
		
		final CTabFolder folder = new CTabFolder(shell, SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(folder);
		
		final String[] tabsText = new String[] {"Application Interface", "Application Route"};
		for (final String tabText : tabsText) {
			final CTabItem tab = new CTabItem(folder, SWT.NONE);
			tab.setText(tabText);
			
			final TableViewer tableViewer = new TableViewer(
				folder,
					SWT.DOUBLE_BUFFERED | SWT.BORDER | 
					SWT.H_SCROLL | SWT.V_SCROLL |
					SWT.SINGLE | SWT.FULL_SELECTION
			);
			
			final Table table = tableViewer.getTable();
			table.setLinesVisible (true);
			table.setHeaderVisible (true);
			
		    for (int i = 0; i < 3; ++i) {
				final TableColumn tc = new TableColumn(table, SWT.LEFT);
			    tc.setText("Column " + i);
			    tc.setWidth(100);
		    }
		    
			tab.setControl(tableViewer.getControl());
		}
		
		shell.pack();

		shell.open();
	}

}

