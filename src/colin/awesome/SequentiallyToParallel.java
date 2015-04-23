package colin.awesome;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 串行算法的并行化
 * 
 * @author Colin Wang Created on Apr 23, 2015
 */
public class SequentiallyToParallel {

	// 串行的迭代
	public void processSequentially(List<Object> objects) {
		for (Object object : objects) {
			process(object);
		}
	}

	// 并行的迭代
	// 如果循环中的操作都是独立的，并且不需要等待所有的迭代操作都完成再继续执行
	// 那么就可以使用Executor将串行循环转化为并行循环
	public void processInParallel(Executor exec, List<Object> objects) {
		for (final Object object : objects) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					process(object);
				}
			});
		}
	}

	private void process(Object o) {
		System.out.println("Processing");
	}
}
