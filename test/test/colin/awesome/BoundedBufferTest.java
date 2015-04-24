package test.colin.awesome;

import colin.awesome.BoundedBuffer;
import junit.framework.TestCase;

public class BoundedBufferTest extends TestCase {

	public void testIsEmptyWhenConstructed() {
		BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
		assertTrue(boundedBuffer.isEmpty());
		assertFalse(boundedBuffer.isFull());
	}
	
	public void testIsFullAfterPuts() throws InterruptedException {
		BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
		for (int i = 0; i < 10; i++) {
			boundedBuffer.put(i);
		}
		assertTrue(boundedBuffer.isFull());
		assertFalse(boundedBuffer.isEmpty());
	}
	
	public void testTakeBlocksWhenEmpty() {
		final BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					boundedBuffer.take();
					fail(); // 程序不应该执行到这里
				} catch (Exception e) {
				}
			}
		};
		try {
			taker.start();
			Thread.sleep(1000);
			taker.interrupt();
			taker.join(1000);
			assertFalse(taker.isAlive());
		} catch (Exception e) {
			fail();
		}
	}
	
}
