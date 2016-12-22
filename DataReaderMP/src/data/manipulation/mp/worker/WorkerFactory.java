package data.manipulation.mp.worker;

import data.manipulation.mp.WorkerType;

public class WorkerFactory {

    private WorkerType type;

    public WorkerFactory(WorkerType type) {
	this.type = type;
    }

    public void setType(WorkerType type) {
	this.type = type;
    }

    public Worker getWorker() {
	switch (type) {
	case Basic:
	    return new BasicOperatorWorker();
	case Filter:
	    return new FilterWorker();
	}
	return null;
    }

}
