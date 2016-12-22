package data.manipulation.mp.worker;

import java.util.concurrent.CopyOnWriteArrayList;

import data.manipulation.mp.OperatorBarrier;
import data.model.RawData;
import data.model.structure.DataContainer;

public abstract class Worker extends Thread {

    @Override
    public void run() {
	super.run();
    }

    public abstract void set(DataContainer range, OperatorBarrier barrier);

    public abstract CopyOnWriteArrayList<RawData> getResult();
}
