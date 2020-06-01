package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class Cps_dailyInquire extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Cps_dailyInquire(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(71, 63, 61, 17);
		label.setText("测试");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(62, 117, 80, 27);
		btnNewButton.setText("New Button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
