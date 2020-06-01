package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.PassengerInfoDao;
import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;

import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddUserUI {

	protected Shell shell;
	private Text text_name;
	private Text text_card;
	private Text text_tel;
	private PassengerInfoDao dao=new PassengerInfoDao();
	private Combo combo_sex;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddUserUI window = new AddUserUI();
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
		shell.setImage(SWTResourceManager.getImage(AddUserUI.class, "/images/timg.jpg"));
		shell.setSize(658, 493);
		shell.setText("添加联系人");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Label label_name = new Label(composite, SWT.NONE);
		label_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_name.setBounds(62, 64, 86, 24);
		label_name.setText("姓    名：");
		
		text_name = new Text(composite, SWT.BORDER);
		text_name.setBounds(164, 61, 214, 30);
		
		Label label_sex = new Label(composite, SWT.NONE);
		label_sex.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_sex.setBounds(62, 136, 61, 24);
		label_sex.setText("性   别：");
		
		Label label_card = new Label(composite, SWT.NONE);
		label_card.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_card.setBounds(62, 207, 96, 24);
		label_card.setText("身份证号码：");
		
		text_card = new Text(composite, SWT.BORDER);
		text_card.setBounds(164, 207, 214, 30);
		
		Label label_telnumber = new Label(composite, SWT.NONE);
		label_telnumber.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_telnumber.setBounds(62, 278, 90, 24);
		label_telnumber.setText("电话号码：");
		
		text_tel = new Text(composite, SWT.BORDER);
		text_tel.setBounds(164, 278, 214, 30);
		
		Button button_2 = new Button(composite, SWT.NONE);
		//添加
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String pname=text_name.getText().trim();
				String sex=combo_sex.getText().trim();
				String cardID=text_card.getText().trim();
				String tel=text_tel.getText().trim();
//				System.out.println(pname+"\t"+sex+"\t"+cardID+"\t"+tel);
				try {
					int i=dao.add(pname,sex,cardID,tel);
					if(i>0){
						swtUtil.showMessage(shell,"温馨提示","添加成功");
						return;
					}else{
						swtUtil.showMessage(shell, "错误提示", "添加失败");
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(224, 358, 114, 34);
		button_2.setText("添加");
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setBounds(400, 207, 198, 24);
		label_5.setText("请输入十八位身份证号码");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setBounds(400, 64, 198, 24);
		label_4.setText("请输入中文姓名");
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setBounds(400, 278, 198, 24);
		label_6.setText("请输入11位手机号码");
		
		combo_sex = new Combo(composite, SWT.NONE);
		combo_sex.add("男");
		combo_sex.add("女");
		combo_sex.setBounds(164, 136, 204, 24);
		
		

	}
}
