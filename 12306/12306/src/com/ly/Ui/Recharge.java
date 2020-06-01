package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Recharge {

	protected Shell shell;
	private Text text_balance;
	private UserInfoDao dao=new UserInfoDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Recharge window = new Recharge();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(44, 47, 90, 24);
		label.setText("充值金额：");
		
		text_balance = new Text(shell, SWT.BORDER);
		text_balance.setBounds(156, 47, 198, 30);
		
		Button button = new Button(shell, SWT.NONE);
		//确认充值
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String balance=text_balance.getText().trim();
				try {
					int i=dao.add(balance);
					if(i>0){
						swtUtil.showMessage(shell, "成功提示", "充值成功");

					}else{
						swtUtil.showMessage(shell, "错误提示", "所充值不能为零");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(140, 131, 114, 34);
		button.setText("确认充值");

	}

}
