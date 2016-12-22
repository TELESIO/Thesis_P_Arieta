package reader.mp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderBarrier {

    private int slice_readed;
    private Lock lock;
    private Condition condition;
    private int threadMax;

    public ReaderBarrier(final int threadMax) {
	this.threadMax = threadMax;
	this.slice_readed = 0;
	this.lock = new ReentrantLock();
	this.condition = lock.newCondition();
    }

    public void done() {
	lock.lock();
	slice_readed++;
	if (slice_readed == threadMax)
	    condition.signalAll();
	lock.unlock();
    }

    public void await() {
	lock.lock();
	while (slice_readed < threadMax)
	    try {
		condition.await();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	lock.unlock();
    }
}
