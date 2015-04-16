/**
 * 
 */
package colin.algorithm.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Colin Wang Created on Apr 16, 2015
 */
public final class ThreadPool {
	// 线程池实例引用，单例
	private volatile static ThreadPool threadPool;
	// 待处理任务队列
	private BlockingQueue<Runnable> taskQueue;
	// 执行者们
	private Worker[] workers;
	private static final int TASK_NUMBER = 10;
	private static final int POOL_SIZE = 5;

	private ThreadPool() {
		this(TASK_NUMBER);
	}

	private ThreadPool(int maxTaskNumber) {
		taskQueue = new ArrayBlockingQueue<>(maxTaskNumber);
		workers = new Worker[POOL_SIZE];
		for (int i = 0; i < workers.length; i++) {
			// 创建并启动执行者线程，用于执行任务队列中的任务
			workers[i] = new Worker();
			new Thread(workers[i]).start();
		}
	}

	// 单例双检锁实现
	public static ThreadPool getThreadPoolInstance(int maxTaskNumber) {
		if (threadPool == null) {
			synchronized (ThreadPool.class) {
				if (threadPool == null) {
					threadPool = new ThreadPool(maxTaskNumber);
				}
			}
		}
		return threadPool;
	}

	public boolean addTask(Runnable task) {
		return taskQueue.add(task);
	}

	public boolean addTasks(Runnable[] tasks) {
		boolean flag = false;
		for (Runnable task : tasks) {
			flag = addTask(task);
			if (flag == false) {
				return false;
			}
		}
		return flag;
	}

	public void destory() {
		for (int i = 0; i < workers.length; i++) {
			if (workers[i].isRunning()) {
				workers[i].stopWorker();
			}
			workers[i] = null;
		}
		taskQueue.clear();
	}

	// 执行者线程
	private class Worker implements Runnable {

		private boolean isRunning = true;

		@Override
		public void run() {
			while (isRunning) {
				// 从任务队列中取出任务
				Runnable task = taskQueue.poll();
				if (task != null) {
					// 执行任务
					task.run();
				}
			}
		}

		// 用于停止该工作线程
		public void stopWorker() {
			this.isRunning = false;
		}

		public boolean isRunning() {
			return isRunning;
		}

	}

}
