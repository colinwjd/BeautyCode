/**
 * 
 */
package colin.awesome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Java NIO 实现的文件复制
 * @author Colin Wang Created on Apr 22, 2015
 */
public class CopyFileWithNIO {

	public static void fileCopy(File in, File out) throws IOException {
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		try {
			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			long size = inChannel.size();
			long position = 0;
			while (position < size) {
				position += inChannel
						.transferTo(position, maxCount, outChannel);
			}
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
