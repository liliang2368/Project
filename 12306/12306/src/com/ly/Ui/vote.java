package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.railwayDao;
import com.ly.util.StringUtil;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class vote extends Composite {
	private Text text;
	private Table table;
	private railwayDao rail=new railwayDao();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public vote(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(30, 48, 56, 20);
		label.setText("出发地");
		
		Combo combo_start = new Combo(composite, SWT.NONE);
		combo_start.setBounds(110, 45, 92, 28);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(317, 48, 67, 20);
		label_1.setText("目的地");
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(424, 45, 92, 28);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(577, 48, 45, 20);
		label_2.setText("出发日");
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(662, 45, 110, 28);
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(881, 38, 98, 30);
		button.setText("查询");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(composite_1, SWT.BORDER);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(72);
		tableColumn.setText("车次");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(107);
		tblclmnNewColumn.setText("出发站");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("到达站");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("历时");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(114);
		tableColumn_3.setText("出发时间");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(135);
		tableColumn_4.setText("到达时间");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(114);
		tableColumn_5.setText("软卧");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(117);
		tblclmnNewColumn_1.setText("软座");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(109);
		tblclmnNewColumn_2.setText("硬座");
		sashForm.setWeights(new int[] {86, 37, 609});
		//先到最下面来获取所有的表格信息
		String start=combo_start.getText();//获取到初始站
		String end= combo.getText();//终点站
		//获取所有的列车车次
		try {
			List<Map<String, Object>> list=rail.FindAllBy(start, end);
			//获取到所有的信息了
			TableItem item=null;
			for(Map<String, Object> map:list){
				//获取所有的信息
				item=new TableItem(table, SWT.NONE);
				item.setText(new String []{StringUtil.toObjectString(map.get("TID")),
						start,end,
						StringUtil.toObjectString(map.get("S_TIME2")),
						StringUtil.toObjectString(map.get("S_TIME1")),
						StringUtil.toObjectString(map.get("软卧")),
						StringUtil.toObjectString(map.get("软座")),
						StringUtil.toObjectString(map.get("硬座")),
				
				
				});
				
				
				
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
