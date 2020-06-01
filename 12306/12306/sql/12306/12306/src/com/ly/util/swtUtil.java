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
		MessageBox mb=new MessageBox(shell);//�������Ӧ
		mb.setMessage(message);
		mb.setText(title);
		mb.open();
	}
		public static void imageSize(Composite composite){
			//��ȡ������ͼƬ
			Image image=composite.getBackgroundImage();
			//��Ӽ���
			//ͼƬ����
			composite.addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					Point size=composite.getSize();//���»���ͼƬ
					Point location=composite.getLocation();//��ȡ��λ��
					e.gc.drawImage(image, 0, 0, 650, 433, location.x, location.y, size.x, size.y);
					
				}
			});
		}
}
