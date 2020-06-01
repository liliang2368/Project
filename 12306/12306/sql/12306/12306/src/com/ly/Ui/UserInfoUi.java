package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ly.util.DataDis;


import org.eclipse.swt.widgets.Menu;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UserInfoUi {

	protected Shell shell;
	private Composite composite;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserInfoUi window = new UserInfoUi();
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
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.setLocation((display.getClientArea().width-shell.getSize().x)/2,
				(display.getClientArea().height-shell.getSize().y)/2);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @throws SQLException 
	 * @throws SQLException 
	 * Create contents of the window.
	 * @throws  
	 */
	protected void createContents() throws SQLException {
		shell = new Shell();
		shell.setSize(1163, 732);
		shell.setText("个人信息管理");
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		//查看个人信息
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DataDis.stackLayout.topControl=DataDis.cps_userinfo;
				composite.layout();
			}
		});
		menuItem.setText("查看个人信息");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		//常用联系人
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DataDis.stackLayout.topControl=DataDis.passenger;
				composite.layout();
			}
		});
		menuItem_2.setText("常用联系人");
		
		MenuItem menuItem_3 = new MenuItem(menu, SWT.NONE);
		//添加联系人
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddUserUI adduserui=new AddUserUI();
				adduserui.open();
			}
		});
		menuItem_3.setText("添加联系人");
		//返回首页
		MenuItem menuItem_4 = new MenuItem(menu, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		menuItem_4.setText("返回首页");
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 1141, 646);
		 Cps_userInfo cps_userinfo = new Cps_userInfo(composite,SWT.NONE);
	      cps_userinfo.setSize(1141, 646);
	      DataDis.cps_userinfo = cps_userinfo;
	     //设置堆栈式布局
        DataDis.orderquery = new OrderQuery(composite,SWT.NONE);
		
		DataDis.passenger = new PassengerTable(composite,SWT.NONE);
		
        composite.setLayout(DataDis.stackLayout);
      				
	}

}
