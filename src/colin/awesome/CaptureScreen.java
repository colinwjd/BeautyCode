/**
 * 
 */
package colin.awesome;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 抓屏程序
 * 
 * @author Colin Wang Created on Apr 22, 2015
 */
public class CaptureScreen {

	public void captureScreen(String fileName) throws AWTException, IOException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File(fileName));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaptureScreen cs = new CaptureScreen();
		String fileName = "D:\\test.png";
		try {
			cs.captureScreen(fileName);
			System.out.println("图片已经保存至：" + fileName);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
