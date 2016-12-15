package data.manipulation.mp;

import java.util.HashMap;
import java.util.Map;

import data.manipulation.mp.worker.BasicOperatorWorker;
import data.model.RawData;
import data.model.structure.DataContainer;

public class BasicOperators {

	private final static int N_THREAD_MAX = Runtime.getRuntime().availableProcessors();
	private DataContainer dataToHandle;
	private DataContainer result;

	public BasicOperators(DataContainer data) {
		this.dataToHandle = data;
		this.result = new DataContainer();
	}

	public void work() {

		HashMap<Integer, RawData> data = dataToHandle.getData();
		int real_thread = N_THREAD_MAX;

		int slice = (data.size()) / real_thread;

		int slice_counter = 0;
		int thread = 0;

		OperatorBarrier barrier = new OperatorBarrier(real_thread - 1);
		BasicOperatorWorker[] worker = new BasicOperatorWorker[real_thread];
		DataContainer[] container = new DataContainer[real_thread];

		for (int i = 0; i < real_thread; i++)
			container[i] = new DataContainer();

		for (Map.Entry<Integer, RawData> datas : data.entrySet()) {

			if (slice_counter < slice) {
				container[thread].put(datas.getValue());
				slice_counter++;
			}
			if (slice_counter == slice) {
				worker[thread] = new BasicOperatorWorker(container[thread], barrier);

				worker[thread].start();
				slice_counter = 0;
				thread++;

				if (thread == real_thread - 1)
					slice += data.size() - (slice * real_thread);
			}

		}

		barrier.await();

		dataToHandle.clear();
		for (int i = 0; i < worker.length; i++) {
			for (Map.Entry<Integer, RawData> dati : container[i].getData().entrySet())
				result.put(dati.getValue());
		}
	}

	public DataContainer getResult() {
		return result;
	}

}
