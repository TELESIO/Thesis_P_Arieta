package data.manipulation.mp;

import java.util.concurrent.CopyOnWriteArrayList;

import data.manipulation.mp.worker.Worker;
import data.manipulation.mp.worker.WorkerFactory;
import data.model.RawData;
import data.model.structure.DataContainer;

public class Operators {

    private final static int N_THREAD_MAX = Runtime.getRuntime().availableProcessors();
    private DataContainer dataToHandle;
    private DataContainer result;
    private WorkerFactory workerFactory;

    public Operators() {
    }

    public void set(DataContainer data, WorkerType type) {
	this.dataToHandle = data;
	this.result = new DataContainer();
	this.workerFactory = new WorkerFactory(type);
    }

    public void work() {

	CopyOnWriteArrayList<RawData> data = dataToHandle.getData();
	int real_thread = N_THREAD_MAX;
	int slice = (data.size()) / real_thread;
	int slice_counter = 0;
	int thread = 0;

	OperatorBarrier barrier = new OperatorBarrier(real_thread - 1);

	Worker[] worker = new Worker[real_thread];
	DataContainer[] container = new DataContainer[real_thread];

	for (int i = 0; i < real_thread; i++)
	    container[i] = new DataContainer();

	for (RawData datas : data) {
	    if (slice_counter < slice) {
		container[thread].put(datas);
		slice_counter++;
	    }

	    if (slice_counter == slice) {

		worker[thread] = workerFactory.getWorker();
		worker[thread].set(container[thread], barrier);
		worker[thread].start();
		slice_counter = 0;
		thread++;

		if (thread == real_thread - 1)
		    slice += data.size() - (slice * real_thread);
	    }

	}

	barrier.await();

	for (int i = 0; i < worker.length; i++) {
	    for (RawData dati : container[i].getData())
		result.put(dati);
	}
    }

    public void changeWorkerType(WorkerType type) {
	this.workerFactory.setType(type);
	this.result.clear();
	this.dataToHandle.clear();
    }

    public DataContainer getResult() {
	return result;
    }

}
