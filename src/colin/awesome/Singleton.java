/**
 * 
 */
package colin.awesome;

/**
 * 延迟加载的单例模式，线程安全，比双检锁的实现更容易理解，并且能够带来同样的优势。
 * 
 * @author Colin Wang Created on Apr 25, 2015
 */
public class Singleton {

	private Singleton() {
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		public static Singleton instance = new Singleton();
	}
}
