package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT304_RadioButtonTwiceAction extends BaseMockupPart {

	private Composite composite = null;
	private Text text = null;
	private Button btnDisabled = null;
	private Button btnEnabled = null;

	private LdapConfig ldapConfig = new LdapConfig();

	@Override
	public Control construct(Composite parent) {
		createComposit(parent);
		addRadioButtons(composite);
		addTextField(composite);
		return null;
	}

	private void addTextField(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void addRadioButtons(Composite composite) {
		btnDisabled = new Button(composite, SWT.RADIO);
		btnDisabled.setText("Disabled");
		btnDisabled.addSelectionListener(new RadioButtonSelectionListener());

		btnEnabled = new Button(composite, SWT.RADIO);
		btnEnabled.setText("Enabled");
		btnEnabled.addSelectionListener(new RadioButtonSelectionListener());
	}

	private void createComposit(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
	}

	private class RadioButtonSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			log("RadioButtonSelectionListener.SelectionEvent." + e.getSource());

			if (ldapConfig.isIsEnabled() && e.getSource().equals(btnDisabled)) {
				log("RadioButtonSelectionListener > first");
				if (statusChangeWarning()) {
					updateLdapStatus(false);
				} else {
					refresh();
				}
			}
			else if (!ldapConfig.isIsEnabled() && e.getSource().equals(btnEnabled)) {
				log("RadioButtonSelectionListener > second");
				if (statusChangeWarning()) {
					updateLdapStatus(true);
				} else {
					refresh();
				}
			}
		}
	}

	public void setData() {
		log("Set data");
		boolean isEnabled = this.ldapConfig.isIsEnabled();
		btnEnabled.setSelection(isEnabled);
		btnDisabled.setSelection(!isEnabled);
	}

	private class LdapConfig {
		private boolean isEnabled = false;

		public boolean isIsEnabled() {
			return isEnabled;
		}

		public void enabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}
	}

	public boolean statusChangeWarning() {
		return MessageDialog.openConfirm(composite.getShell(), "Change LDAP Status",
				"Changing the LDAP status to enabled will disable any other enabled Profile. " +
						"\nThis or changing an enabled Profile to disabled will invalidate the " +
						"\nsession(s) of all users currently logged into the system, requiring all " +
						"\nusers to log back in. Do you wish to continue?");
	}

	public void refresh() {
		composite.redraw();
	}

	public void updateLdapStatus(boolean status) {
		ldapConfig.enabled(status);
	}

	public void log(String string) {
		text.setText(text.getText() + string + "\n");
	}
}
