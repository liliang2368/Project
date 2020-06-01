package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class chaxun extends Composite {
	private Table table;
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public chaxun(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(35, 23, 55, 20);
		label.setText("出发地");
		
		text = new Text(composite, SWT.BORDER);
		text.setText("深圳");
		text.setBounds(96, 20, 73, 26);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("目的地");
		label_1.setBounds(205, 23, 55, 20);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setText("深圳");
		text_1.setBounds(256, 20, 73, 26);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(364, 23, 55, 20);
		label_2.setText("日期");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(523, 23, 76, 20);
		label_3.setText("发车时间");
		
		Button button = new Button(composite, SWT.RADIO);
		button.setBounds(605, 18, 66, 26);
		button.setText("成人");
		
		Button button_1 = new Button(composite, SWT.RADIO);
		button_1.setText("学生");
		button_1.setBounds(696, 20, 66, 26);
		
		Button button_2 = new Button(composite, SWT.RADIO);
		button_2.setText("儿童");
		button_2.setBounds(774, 20, 66, 26);
		
		Button button_3 = new Button(composite, SWT.RADIO);
		button_3.setText("军残");
		button_3.setBounds(855, 20, 66, 26);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("席别");
		label_4.setBounds(42, 86, 48, 20);
		
		Button btnNewButton = new Button(composite, SWT.CHECK);
		btnNewButton.setBounds(89, 81, 98, 30);
		btnNewButton.setText("全部席别");
		
		Button button_4 = new Button(composite, SWT.CHECK);
		button_4.setText("软卧");
		button_4.setBounds(205, 81, 98, 30);
		
		Button button_5 = new Button(composite, SWT.CHECK);
		button_5.setText("软座");
		button_5.setBounds(332, 81, 98, 30);
		
		Button button_6 = new Button(composite, SWT.CHECK);
		button_6.setText("硬座");
		button_6.setBounds(462, 81, 98, 30);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DateTime dateTime = new DateTime(composite, SWT.BORDER | SWT.CALENDAR);
				dateTime.setBounds(425, -23, 259, 255);
			}
		});
		btnNewButton_1.setBounds(406, 23, 98, 30);
		//通过格式化时间
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String str=format.format(date);
		btnNewButton_1.setText(str);
		
	
	
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("出发地");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("到达地");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("历时");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("出发时间");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("到达时间");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("软卧");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("软座");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("硬座");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {48, 246, 0});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
