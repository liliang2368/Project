package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.util.StringUtil;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OrderQuery extends Composite {
	private Table table;
	private zhuanhuantime zhuan=new zhuanhuantime();
	private rraletime rale=new rraletime();
	private railwayDao rail=new railwayDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OrderQuery(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(26, 111, 1039, 612);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(114);
		tableColumn.setText("订单号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("乘客");
		
		TableColumn tblclmnChengcherqi = new TableColumn(table, SWT.NONE);
		tblclmnChengcherqi.setWidth(103);
		tblclmnChengcherqi.setText("乘车日期");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出发地");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("目的地");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("车次");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("座位号");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("票价");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(108);
		tableColumn_7.setText("票种");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(102);
		tableColumn_2.setText("状态");
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		dateTime.setBounds(171, 46, 155, 33);
		Button btn_inqu = new Button(this, SWT.NONE);
		//查询
		btn_inqu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String calender=zhuan.zhuanCalder(dateTime);
				try {
					List<Map<String,Object>> list=rale.Query(calender);
					TableItem item=null;
					for(Map<String,Object>map:list){
						Map<String, Object> map01=rail.fandBian(StringUtil.toObjectString(map.get("S_LOCLATION")), StringUtil.toObjectString(map.get("S_GETLOC")));
						item=new TableItem(table, SWT.NONE);
						item.setText(new String[]{StringUtil.toObjectString(map.get("KID")),
								                   StringUtil.toObjectString(map.get("PID")),
								                   StringUtil.toObjectString(map.get("CALENDER")),
								                   StringUtil.toObjectString(map01.get("起点站")),
								                   StringUtil.toObjectString(map.get("终点站")),
								                   StringUtil.toObjectString(map.get("R_ID")),
								                   StringUtil.toObjectString(map.get("SEAT")),
								                   StringUtil.toObjectString(map.get("TICKET_SPECIES")),
								                   StringUtil.toObjectString(map.get("TICKET_PRICE")),
								                   StringUtil.toObjectString(map.get("STATUS"))});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_inqu.setBounds(406, 45, 114, 34);
		btn_inqu.setText("查询");
		

		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(26, 51, 90, 29);
		label_2.setText("乘车日期：");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
