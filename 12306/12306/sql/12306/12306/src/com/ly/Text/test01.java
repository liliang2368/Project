package com.ly.Text;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;

public class test01 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			test01 window = new test01();
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
		shell.setSize(632, 401);
		shell.setText("SWT Application");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.CALENDAR);
		System.out.println(dateTime.toString());
		System.out.println(dateTime.getData("DateTime"));
		System.out.println(dateTime.getToolTipText());
		
		//System.out.println(dateTime.getData().toString());
		dateTime.setBounds(89, 90, 294, 186);
		
		DateTime dateTime_1 = new DateTime(shell, SWT.BORDER);
		dateTime_1.setBounds(186, 31, 110, 28);
		System.out.println(dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay());
		System.out.println(dateTime.getYear());
		System.out.println(dateTime.getDay());
		System.out.println(dateTime.getMonth());
		System.out.println(dateTime.getMinutes());

	}
}
