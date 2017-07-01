package utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {

    private int slice;
    private Lock lock;
    private Condition condition;
    private int threadMax;

    public Barrier(final int threadMax) {
	this.threadMax = threadMax;
	this.slice = 0;
	this.lock = new ReentrantLock();
	this.condition = lock.newCondition();
    }

    public void done() {
	lock.lock();
	slice++;
	if (slice == threadMax)
	    condition.signalAll();
	lock.unlock();
    }

    public void await() {
	lock.lock();
	while (slice < threadMax)
	    try {
		condition.await();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	lock.unlock();
    }
}
