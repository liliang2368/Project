package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ly.dao.PassengerInfoDao;
import com.ly.util.StringUtil;

public class PassengerTable extends Composite {
	private Table table_passenger;
	private PassengerInfoDao info=new PassengerInfoDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public PassengerTable(Composite parent, int style) throws SQLException {
		super(parent, style);
		
		table_passenger = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_passenger.setBounds(10, 10, 975, 597);
		table_passenger.setHeaderVisible(true);
		table_passenger.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table_passenger, SWT.NONE);
		tableColumn.setWidth(170);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table_passenger, SWT.NONE);
		tableColumn_1.setWidth(186);
		tableColumn_1.setText("乘客姓名");
		
		TableColumn tableColumn_2 = new TableColumn(table_passenger, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("性别");
		
		TableColumn tableColumn_3 = new TableColumn(table_passenger, SWT.NONE);
		tableColumn_3.setWidth(180);
		tableColumn_3.setText("身份证号码");
		
		TableColumn tableColumn_4 = new TableColumn(table_passenger, SWT.NONE);
		tableColumn_4.setWidth(251);
		tableColumn_4.setText("手机号码");
		//获取所有的联系人
		List<Map<String, Object>> list=info.FindaLL();
		System.out.println(list);
		TableItem item=null;
		for(Map<String, Object> map:list){
			item=new TableItem(table_passenger, SWT.NONE);
			item.setText(new String []{StringUtil.toObjectString(map.get("PID")),StringUtil.toObjectString(map.get("PNAME")),StringUtil.toObjectString(map.get("SEX"))
					,StringUtil.toObjectString(map.get("CARID")),StringUtil.toObjectString(map.get("TEL"))});
			
			
		}
		
		
		
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
