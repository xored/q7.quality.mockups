package com.xored.q7.quality.mockups.swt.tree;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class TreeStyledTextLabelProvider extends StyledCellLabelProvider {
	private boolean enableStyle = true;
	private boolean underline = false;
	private boolean strikeout = false;
	private int start = 0;
	private int length = 5;
	private int borderStyle = SWT.None;
	private int underlineStyle = SWT.UNDERLINE_SINGLE;
	private RGB textColor = new RGB(0, 0, 0);
	private RGB strikeoutColor = new RGB(0, 0, 0);
	private RGB borderColor = new RGB(0, 0, 0);
	private RGB backgroundColor = new RGB(255, 255, 255);
	private RGB underlineColor = new RGB(0, 0, 0);

	public void setStart(int start) {
		this.start = start;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setUnderlineStyle(int style) {
		this.underlineStyle = style;
	}

	public int getUnderlineStyle() {
		return underlineStyle;
	}

	public boolean isUnderline() {
		return underline;
	}

	public void setUnderline(boolean enabled) {
		this.underline = enabled;
	}

	public void setBorderStyle(int style) {
		this.borderStyle = style;
	}

	public int getBorderStyle() {
		return borderStyle;
	}

	public boolean isStrikeout() {
		return strikeout;
	}

	public void setStrikeout(boolean strikeout) {
		this.strikeout = strikeout;
	}

	public RGB getTextColor() {
		return textColor;
	}

	public void setTextColor(RGB textColor) {
		this.textColor = textColor;
	}

	public RGB getStrikeoutColor() {
		return strikeoutColor;
	}

	public void setStrikeoutColor(RGB strikeoutColor) {
		this.strikeoutColor = strikeoutColor;
	}

	public RGB getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(RGB borderColor) {
		this.borderColor = borderColor;
	}

	public RGB getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(RGB backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public RGB getUnderlineColor() {
		return underlineColor;
	}

	public void setUnderlineColor(RGB underlineColor) {
		this.underlineColor = underlineColor;
	}

	public boolean isEnableStyle() {
		return enableStyle;
	}

	public void setEnableStyle(boolean enableStyle) {
		this.enableStyle = enableStyle;
	}

	public void update(ViewerCell cell) {
		cell.setText(cell.getElement().toString());
		if (enableStyle) {
			StyleRange range = new StyleRange();
			Display display = cell.getItem().getDisplay();

			range.start = start;
			range.length = length;
			range.underline = isUnderline();
			range.underlineStyle = getUnderlineStyle();
			range.strikeout = isStrikeout();
			range.borderStyle = getBorderStyle();
			range.foreground = new Color(display, textColor);
			range.background = new Color(display, backgroundColor);
			range.borderColor = new Color(display, borderColor);
			range.strikeoutColor = new Color(display, strikeoutColor);
			range.underlineColor = new Color(display, underlineColor);
			cell.setStyleRanges(new StyleRange[] { range });
		} else {
			cell.setStyleRanges(null);
		}
		super.update(cell);
	}
}