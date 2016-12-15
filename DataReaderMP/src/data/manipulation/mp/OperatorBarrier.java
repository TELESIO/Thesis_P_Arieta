package data.manipulation.mp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OperatorBarrier {

	private int slice_processed;
	private Lock lock;
	private Condition condition;
	private int threadMax;

	public OperatorBarrier(final int threadMax) {
		this.threadMax = threadMax;
		this.slice_processed = 0;
		this.lock = new ReentrantLock();
		this.condition = lock.newCondition();
	}

	public void done() {
		lock.lock();
		if (slice_processed == threadMax)
			condition.signalAll();

		slice_processed++;
		lock.unlock();
	}

	public void await() {
		lock.lock();
		while (slice_processed < threadMax) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
}
