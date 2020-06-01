package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.StringUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class ChangeEmail {

	protected Shell shell;
	private Text text_anwser;
	private UserInfoDao dao1=new UserInfoDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ChangeEmail window = new ChangeEmail();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws SQLException 
	 */
	public void open() throws SQLException {
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
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(30, 48, 90, 24);
		label.setText("密保问题：");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(30, 121, 90, 24);
		label_1.setText("答   案：");
		
		
		text_anwser = new Text(shell, SWT.BORDER);
		text_anwser.setBounds(126, 121, 280, 30);
		
		Button button = new Button(shell, SWT.NONE);
		//下一步
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangeEmail2 ce2=new ChangeEmail2();
				shell.dispose();
				ce2.open();
			}
		});
		button.setBounds(65, 178, 114, 34);
		button.setText("下一步");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(247, 178, 114, 34);
		button_1.setText("取消");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(126, 48, 280, 30);
		List<Map<String, Object>> list=dao1.Findquestion();
		for(Map<String, Object> map:list){
			combo.add(StringUtil.StringUtil(map.get("QUESTION")));
		}
		combo.select(0);

	}
}
