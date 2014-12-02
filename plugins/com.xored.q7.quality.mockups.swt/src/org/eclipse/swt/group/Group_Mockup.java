package org.eclipse.swt.group;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Group_Mockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.BORDER);	
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		    .grab(true, true).applyTo(composite);
		
		
		Device d = Display.getCurrent();
		
		Group g1 = new Group(composite, SWT.NONE);
		g1.setText("Group1");
		g1.setLayout(new FillLayout());
		
		for (int i = 0; i < 5; i++) {
		    Button button = new Button(g1, SWT.PUSH);
		    button.setText("Button" + (i + 1));
		    button.setToolTipText("Button " + (i+1));
		}
		
		Group g2 = new Group(composite, SWT.SHADOW_ETCHED_IN);
		g2.setLayout(new FillLayout());
		g2.setText("Group2");
		Text t = new Text(g2, SWT.NONE);
		t.setText("SHADOW_ETCHED_IN group");
		
		Group g3 = new Group(composite, SWT.SHADOW_ETCHED_OUT);
		g3.setLayout(new FillLayout());
		g3.setText("Group3");
		for (int i = 0; i < 5; i++) {
		    Button button = new Button(g3, SWT.PUSH);
		    button.setText("Button" + (i + 1));
		    button.setToolTipText("Button " + (i+1));
		}
		
		Group g4 = new Group(composite, SWT.SHADOW_IN);
		g4.setLayout(new FillLayout());
		//g4.setText("Group2");
		Text t2 = new Text(g4, SWT.NONE);
		t2.setText("SHADOW_IN nonamed group");
		
		Group g5 = new Group(composite, SWT.SHADOW_IN);
		g5.setLayout(new FillLayout());
		g5.setText("Group5");
		Text t3 = new Text(g5, SWT.NONE);
		t3.setText("SHADOW_OUT");
		
		
		FontData fontData = g5.getFont().getFontData()[0];
		Font font = new Font(d, new FontData(fontData.getName(), fontData
			    .getHeight(), SWT.ITALIC));
		g5.setFont(font);
		
		return null;
	}
}
