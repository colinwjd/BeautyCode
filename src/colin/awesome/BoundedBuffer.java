/**
 * 
 */
package colin.awesome;

import java.util.concurrent.Semaphore;

/**
 * 基于信号量实现的有界缓存
 * @author Colin Wang
 * Created on Apr 24, 2015
 */
public class BoundedBuffer<E> {

	private final Semaphore availableItems, availableSpace;
	private final E[] items;
	private int putPosition = 0, takePosition = 0;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(int capacity) {
		availableItems = new Semaphore(0);
		availableSpace = new Semaphore(capacity);
		items = (E[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return availableItems.availablePermits() == 0;
	}
	
	public boolean isFull() {
		return availableSpace.availablePermits() == 0;
	}
	
	public void put(E e) throws InterruptedException {
		// 获取一个信号量，该处可能会发生竞争
		availableSpace.acquire();
		// 这里会获取一个锁，同样可能会发生竞争
		doInsert(e);
		availableItems.release();
	}

	private synchronized void doInsert(E e) {
		int i = putPosition;
		items[i] = e;
		// 如果缓存满了，将putPosition复位
		putPosition = (++i == items.length) ? 0 : 1;
	}
	
	public E take() throws InterruptedException {
		availableItems.acquire();
		E item = doExtract();
		availableSpace.release();
		return item;
	}

	private synchronized E doExtract() {
		int i = takePosition;
		E item = items[i];
		items[i] = null;
		// 如果取到了最后一个元素，将takePosition复位
		takePosition = (++i == items.length) ? 0 : 1;
		return item;
	}
	
}
