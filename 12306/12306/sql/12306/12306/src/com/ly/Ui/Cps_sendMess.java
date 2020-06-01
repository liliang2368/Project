package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.util.mailTest02;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Cps_sendMess extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Cps_sendMess(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(102, 79, 129, 17);
		label.setText("在此输入您的反馈信息");
		
		text = new Text(this, SWT.BORDER | SWT.MULTI);
		text.setBounds(102, 112, 725, 426);
		
		Button button = new Button(this, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String article = text.getText();
				mailTest02 mail = new mailTest02();
				mail.send(article);
				swtUtil.showMessage(getShell(), "发送成功","感谢您的反馈");
			}
		});
		button.setBounds(428, 573, 80, 27);
		button.setText("发送");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
