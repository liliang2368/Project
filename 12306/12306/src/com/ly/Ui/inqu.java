package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class inqu extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Cps_buyTickets cps=new Cps_buyTickets();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public inqu(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(composite, SWT.BORDER);
		
		
		
		text.setText("正在为您占座。。。。。。。。。。。。。。。。。。。。。"+"\n");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("车厢");
		label.setBounds(80, 50, 54, 20);
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(182, 50, 73, 26);
		text_1.setText(cps.carrige);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(75, 119, 59, 20);
		label_1.setText("座位号");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(203, 116, 73, 26);
		text_2.setText(cps.seatof);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setBounds(320, 68, 98, 30);
		button.setText("点击购买");
		sashForm.setWeights(new int[] {102, 217});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
