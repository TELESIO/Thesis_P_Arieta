package data.manipulation;

import java.util.concurrent.CopyOnWriteArrayList;

public class NumericalMethod {

    public NumericalMethod() {
    }

    public CopyOnWriteArrayList<Double> eulerForwardMethod(CopyOnWriteArrayList<Double> verticalAcceleration,
	    double deltaTimes) {
	CopyOnWriteArrayList<Double> eulerResult = new CopyOnWriteArrayList<Double>();
	double v0 = 0; // initial velocity condition
	double x0 = 0; // initial displacement condition
	double deltaT = deltaTimes; // time
	int nSteps = verticalAcceleration.size();
	double velocity[] = new double[nSteps];
	velocity[0] = v0;
	double displacement[] = new double[nSteps];
	displacement[0] = x0;

	for (int i = 0; i < nSteps - 1; i++) {

	    velocity[i + 1] = velocity[i] + verticalAcceleration.get(i) * deltaT;
	    displacement[i + 1] = displacement[i] + velocity[i] * deltaT;
	    eulerResult.add(displacement[i]);
	}

	return eulerResult;
    }

}
