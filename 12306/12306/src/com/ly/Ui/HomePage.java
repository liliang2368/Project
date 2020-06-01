package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.util.swtUtil;

import org.eclipse.swt.SWT;

public class HomePage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HomePage(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		composite.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/7.jpg"));
		composite.setBounds(0, 0, 1115, 718);
		swtUtil.imageSize(composite);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
