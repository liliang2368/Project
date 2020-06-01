package com.ly.util;



import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class swtUtil {
	public static void showMessage(Shell shell,String title,String message){
		MessageBox mb=new MessageBox(shell);//面板自适应
		mb.setMessage(message);
		mb.setText(title);
		mb.open();
	}
		public static void imageSize(Composite composite){
			//获取到背景图片
			Image image=composite.getBackgroundImage();
			//添加监听
			//图片自适
			composite.addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					Point size=composite.getSize();//重新绘制图片
					Point location=composite.getLocation();//获取到位置
					e.gc.drawImage(image, 0, 0, 650, 433, location.x, location.y, size.x, size.y);
					
				}
			});
		}
}
