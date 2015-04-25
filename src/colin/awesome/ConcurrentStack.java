/**
 * 
 */
package colin.awesome;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞栈
 * @author Colin Wang
 * Created on Apr 25, 2015
 */
public class ConcurrentStack<E> {

	// 使用原子引用保存当前栈顶元素的引用
	AtomicReference<Node<E>> top = new AtomicReference<>();
	
	public void push(E e) {
		// 创建一个新的结点
		Node<E> newHead = new Node<E>(e);
		// 存储旧的栈顶
		Node<E> oldHead;
		do {
			// 获取当前的栈顶
			oldHead = top.get();
			// 新结点的next域指向当前的栈顶
			newHead.next = oldHead;
			// 使用CAS更新当前栈顶，如果失败就进行重试。
			// 如果当前栈顶为oldHead，则更新为newHead，操作成功。
			// 如果当前栈顶值不是oldHead，表示其他线程已经对栈顶进行了修改，操作失败并重试。
		} while (!top.compareAndSet(oldHead, newHead));
	}
	
	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			// 取出当前栈顶
			oldHead = top.get();
			if (oldHead == null) {
				// 如果当前栈顶为null则返回null
				return null;
			}
			// 新的栈顶指向当前栈顶的下一个元素
			newHead = oldHead.next;
			// 尝试使用新的栈顶替换旧的栈顶，失败则重试
		} while (!top.compareAndSet(oldHead, newHead));
		// 返回当前栈顶的元素值
		return oldHead.value;
	}
	
	// 栈中的元素
	private static class Node<T> {
		private T value;
		public Node<T> next;
		
		public Node(T value) {
			this.value = value;
		}
	}
}
