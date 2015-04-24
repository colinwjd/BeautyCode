/**
 * 
 */
package colin.awesome;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁包装Map，使它能在多个读线程之间安全共享，并且避免读写、写写冲突。
 * 适用于对另一种Map实现提供并发性更高的访问。但是如果仅仅是需要一个并发的Map，
 * 使用ConcurrentHashMap是一个很好的选择。
 * @author Colin Wang
 * Created on Apr 24, 2015
 */
public class ReadWriteMap<K,V> {

	private final Map<K, V> map;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();
	
	// 对传入的Map进行包装
	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
	}
	
	public V put(K key, V value) {
		// 写操作需要取得写锁
		writeLock.lock();
		try {
			return map.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}
	
	public V get(K key) {
		readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}
}
