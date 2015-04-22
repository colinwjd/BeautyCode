package colin.awesome;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现的有界阻塞容器
 * 
 * @author Colin Wang Created on Apr 22, 2015
 */
public class BoundedHashSet<E> {
	// 使用final关键字，可以防止引用逃逸造成的影响
	// 代码可读性更强，可以使程序后期的维护者更容易弄清楚程序的意图
	private final Set<E> set;
	private final Semaphore sem;

	public BoundedHashSet(int bound) {
		set = Collections.synchronizedSet(new HashSet<E>());
		sem = new Semaphore(bound);
	}

	public boolean add(E e) throws InterruptedException {
		// 申请一个信号量许可，当没有许可可用时，该方法会阻塞等待
		sem.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(e);
			return wasAdded;
		} finally {
			if (!wasAdded) {
				// 添加失败则释放一个许可
				sem.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved) {
			sem.release();
		}
		return wasRemoved;
	}
	
}
