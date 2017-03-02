package org.eclipse.swt.text;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RangeSplit extends BaseMockupPart {
	
	private final class Ranger {
		private int rangeStart = 0;
		private StyledText text;
		

		public Ranger(StyledText text) {
			super();
			if (text == null)
				throw new NullPointerException();
			this.text =  text;
		}

		private void createStyleRange(int length) {
			StyleRange styleRange = new StyleRange();
			styleRange.start = rangeStart;
			styleRange.length = length;
			styleRange.fontStyle = SWT.BOLD;
			styleRange.foreground = view.getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			text.setStyleRange(styleRange);
			rangeStart += length;
		}
	}

	@Override
	public Control construct(Composite parent) {
		StyledText t1 = new StyledText(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(t1);
		t1.setText("This\n text contains multiple style ranges with same parameters. On some platforms such style ranges are squashed. For consistency, RCPTT have to explicitly squash them every time.");
		Ranger r = new Ranger(t1);
		r.createStyleRange(2);
		r.createStyleRange(3);
		r.createStyleRange(4);
		r.createStyleRange(5);
		r.createStyleRange(6);
		r.rangeStart = 30;
		r.createStyleRange(2);
		r.createStyleRange(3);
		r.createStyleRange(4);
		r.createStyleRange(5);
		r.createStyleRange(6);
		return t1;
	}
	

}
