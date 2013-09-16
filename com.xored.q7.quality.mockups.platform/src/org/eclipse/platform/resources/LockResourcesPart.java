package org.eclipse.platform.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class LockResourcesPart extends BaseMockupPart {
	private Map<IFile, InputStream> files = new HashMap<IFile, InputStream>();
	private DataBindingContext dbc = new DataBindingContext();
	private WritableValue value = new WritableValue("", String.class);

	public String getLabel() {
		return "Resource Locking";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);
		Composite client = new Composite(section, SWT.NONE);

		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);

		Button lockResources = new Button(client, SWT.PUSH);
		Button unlockResources = new Button(client, SWT.PUSH);
		lockResources.setText("Lock resources");
		lockResources.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				lockResources();
			}
		});
		unlockResources.setText("Unlock resources");
		unlockResources.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				unlockResources();
			}
		});
		client.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				unlockResources();
			}
		});
		Label msg = new Label(client, SWT.NONE);
		dbc.bindValue(value, SWTObservables.observeText(msg));
		value.setValue("Unlocked");
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1)
				.applyTo(msg);
		return client;
	}

	protected void unlockResources() {
		if (files.size() > 0) {
			for (InputStream stream : files.values()) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		files.clear();
		value.setValue("Unlocked");
	}

	protected void lockResources() {
		if (files.size() == 0) {
			try {
				ResourcesPlugin.getWorkspace().getRoot()
						.accept(new IResourceVisitor() {

							public boolean visit(IResource resource)
									throws CoreException {
								if (resource.isAccessible()
										&& resource.getType() == IResource.FILE) {
									files.put((IFile) resource,
											((IFile) resource).getContents());
								}
								return resource.isAccessible();
							}
						});
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if (files.size() > 0) {
				value.setValue("Locked:" + files.size());
			}
		}
	}
}
