package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ly.dao.addbianDao;
import com.ly.dao.railwayDao;
import com.ly.util.StringUtil;

public class Site extends Composite {
	private Table table;
	private railwayDao dao=new railwayDao();
	private addbianDao bian=new addbianDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Site(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 988, 623);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(127);
		tblclmnNewColumn.setText("车次");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(853);
		tableColumn.setText("站点信息");
		
		
	
		try {
			List<Map<String,Object>> list=bian.FindALL();
			TableItem item=null;
			for(Map<String,Object>map:list){
				StringBuffer sb=new StringBuffer();
				sb.append("起始站");
				item=new TableItem(table,SWT.NONE);
				List<Map<String, Object>> list01=dao.FindStation1(StringUtil.toObjectString(map.get("TID")), StringUtil.toObjectString(map.get("BASC")));
				for(Map<String, Object> map01:list01){
					sb.append("->"+StringUtil.toObjectString(map01.get("S_STATNAME")));
					item.setText(new String []{StringUtil.toObjectString(map.get("TID")),sb.toString()});
					
				}
				
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
