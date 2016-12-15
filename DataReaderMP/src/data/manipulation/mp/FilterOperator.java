package data.manipulation.mp;

import java.util.HashMap;
import java.util.Map;

import data.manipulation.mp.worker.FilterWorker;
import data.model.RawData;
import data.model.structure.DataContainer;

public class FilterOperator {

	private final static int N_THREAD_MAX = Runtime.getRuntime().availableProcessors();
	private HashMap<Integer, RawData> data;
	private HashMap<Integer, RawData> result;

	public FilterOperator(HashMap<Integer, RawData> data) {
		this.data = data;
		this.result = new HashMap<Integer, RawData>();
	}

	public void work() {

		int real_thread = N_THREAD_MAX;

		int slice = (data.size()) / real_thread;

		int slice_counter = 0;
		int thread = 0;

		OperatorBarrier barrier = new OperatorBarrier(real_thread - 1);
		FilterWorker[] worker = new FilterWorker[real_thread];
		DataContainer[] container = new DataContainer[real_thread];

		for (int i = 0; i < real_thread; i++)
			container[i] = new DataContainer();

		for (Map.Entry<Integer, RawData> datas : data.entrySet()) {
			if (slice_counter < slice) {
				container[thread].put(datas.getValue());
				slice_counter++;
			}
			if (slice_counter == slice) {
				worker[thread] = new FilterWorker(container[thread].getData(), barrier);

				worker[thread].start();
				slice_counter = 0;
				thread++;

				if (thread == real_thread - 1)
					slice += data.size() - (slice * real_thread);
			}

		}

		barrier.await();

		int key = 0;
		for (int i = 0; i < worker.length; i++) {
			for (Map.Entry<Integer, RawData> dati : container[i].getData().entrySet()) {
				result.put(key, dati.getValue());
				key++;
			}
		}
	}

	public HashMap<Integer, RawData> getResult() {
		return result;
	}
}
