package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.QualityIssuesPlugin;

public class QS3160BundleResourceImage extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(content);
		Label bundleresource = new Label(parent, SWT.NONE);
		ImageDescriptor res = ImageDescriptor.createFromURL(Platform.getBundle(QualityIssuesPlugin.PLUGIN_ID)
				.getResource("icons/q7.png"));
		bundleresource.setText("bundleresource");
		final Image img = res.createImage();
		bundleresource.setImage(img);

		Label bundleentry = new Label(parent, SWT.NONE);
		ImageDescriptor entryDescriptor = ImageDescriptor.createFromURL(Platform.getBundle(
				QualityIssuesPlugin.PLUGIN_ID).getEntry("icons/q7.png"));
		bundleentry.setText("bundleentry");
		final Image entryImg = entryDescriptor.createImage();
		bundleentry.setImage(entryImg);

		Label file = new Label(parent, SWT.NONE);
		ImageDescriptor fileDescriptor = ImageDescriptor.createFromFile(getClass(), "/icons/q7.png");
		file.setText("file");
		final Image fileImg = fileDescriptor.createImage();
		file.setImage(fileImg);
		content.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				img.dispose();
				entryImg.dispose();
				fileImg.dispose();
			}
		});
		return content;
	}

	@Override
	public String getLabel() {
		return null;
	}
}
