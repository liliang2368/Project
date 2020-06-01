package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ly.dao.UserInfoDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class incha {

	protected Shell shell;
	private Table table_1;
	private rraletime rra=new rraletime();
	private railwayDao dao=new railwayDao();
	private UserInfoDao info=new UserInfoDao();
	private double price=98.6;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			incha window = new incha();
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
		shell.setSize(999, 496);
		shell.setText("付款");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//付款
				try {
					double sum=rra.afterPay(Integer.parseInt(text.getText()));//获取到了价格
					//这里也要获取到所有的用户名的价格
					Map<String, Object> map=info.getBal(MeunUi.userName);
					if(Double.parseDouble(StringUtil.toObjectString(map.get("BALANCE")))>sum){
						//经行购票操作
						int result= info.payTicket("李阳",price );
						if(result == 1 ){
							swtUtil.showMessage(shell, "成功", "付款成功");
							return;
						}
						
					}
					
					
					
					
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(362, 37, 98, 30);
		btnNewButton.setText("付款");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(143, 38, 49, 20);
		label_1.setText("订单号:");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(198, 37, 127, 26);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				TableItem t = table_1.getItem(table_1.getSelectionIndex());
				text.setText(t.getText(1));
			}
		});
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(71);
		tableColumn_9.setText("车次");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("订单号");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(63);
		tableColumn_11.setText("乘客");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(113);
		tableColumn_12.setText("发车时间");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(89);
		tableColumn_13.setText("出发地");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(87);
		tableColumn_14.setText("目的地");
		
		TableColumn tableColumn_15 = new TableColumn(table_1, SWT.NONE);
		tableColumn_15.setWidth(78);
		tableColumn_15.setText("票种");
		
		TableColumn tableColumn_16 = new TableColumn(table_1, SWT.NONE);
		tableColumn_16.setWidth(100);
		tableColumn_16.setText("席别");
		
		TableColumn tableColumn_20 = new TableColumn(table_1, SWT.NONE);
		tableColumn_20.setWidth(100);
		tableColumn_20.setText("车厢");
		
		TableColumn tableColumn_17 = new TableColumn(table_1, SWT.NONE);
		tableColumn_17.setText("座位");
		tableColumn_17.setWidth(92);
		
		TableColumn tableColumn_18 = new TableColumn(table_1, SWT.NONE);
		tableColumn_18.setWidth(71);
		tableColumn_18.setText("票价");
		
		TableColumn tableColumn_19 = new TableColumn(table_1, SWT.NONE);
		tableColumn_19.setWidth(100);
		tableColumn_19.setText("状态");
		List<Map<String, Object>> list=rra.getAllstatus(0);//查询出所有未支付的账单
		//System.out.println("更新的票数信息"+list);
		TableItem item1=null;
		String status="";
		for(Map<String, Object> map:list){

		item1=new TableItem(table_1, SWT.NONE);
		
		
		//根据编号来查出站台的信息
		//初始站
			Map<String, Object> map1=dao.Findbian2(Integer.parseInt(map.get("S_LOCLATION").toString()));//起点站
			Map<String, Object> map2=dao.Findbian2(Integer.parseInt(map.get("S_GETLOC").toString()));//终点站
			if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==0){
				status="待付款";
			}
			
			item1.setText(new String []{StringUtil.toObjectString(map.get("R_ID")),StringUtil.toObjectString(map.get("KID")),StringUtil.toObjectString(map.get("PNAME")),
					StringUtil.toObjectString(map.get("CALENDER")),StringUtil.toObjectString(map1.get("RAILWAY_STATION")),StringUtil.toObjectString(map2.get("RAILWAY_STATION")),
					StringUtil.toObjectString(map.get("TICKET_SPECIES")),StringUtil.toObjectString(map.get("SOFTSEATP")),
					StringUtil.toObjectString(map.get("CARRIAGE")),StringUtil.toObjectString(map.get("SEAT")),StringUtil.toObjectString(map.get("TICKET_PRICE")),
					status});
	}
		//tableColumn.setWidth(100);
		sashForm.setWeights(new int[] {74, 222});

	}
}
