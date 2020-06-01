package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewUserInfo extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button button;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ViewUserInfo(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(185, 164, 90, 24);
		label.setText("账户名：");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(297, 161, 239, 30);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(185, 243, 90, 24);
		label_1.setText("邮  箱：");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(297, 243, 239, 30);
		
		button = new Button(this, SWT.NONE);
		//修改邮箱
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangeEmail ce=new ChangeEmail();
				try {
					ce.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(598, 238, 114, 34);
		button.setText("修改邮箱");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(185, 320, 90, 24);
		label_2.setText("余  额：");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(297, 314, 239, 30);
		
		Button button_1 = new Button(this, SWT.NONE);
		//充值
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Recharge recharge=new Recharge();
				recharge.open();
			}
		});
		button_1.setBounds(598, 310, 114, 34);
		button_1.setText("充值");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(360, 415, 73, 30);
		


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
