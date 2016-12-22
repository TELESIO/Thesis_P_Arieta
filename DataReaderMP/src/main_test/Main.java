package main_test;
import java.util.concurrent.CopyOnWriteArrayList;

import data.manipulation.FilteringDataOperations;
import data.manipulation.NumericalMethod;
import data.manipulation.mp.Operators;
import data.manipulation.mp.WorkerType;
import data.model.atomicData.Time;
import data.model.enums.Axis;
import data.model.structure.DataContainer;
import reader.Reader;
import reader.mp.MPCsvFileReader;

public class Main {

    public static final char separator_comma = ',';
    public static final char separator_semicolon = ';';
    public static final String path_input_file = "file/VeryFast.csv";

    public static void main(String[] args) {
	Reader r = new MPCsvFileReader(path_input_file, separator_comma);
	r.read_file();
	DataContainer data = r.getDataFileReaderResult();

	CopyOnWriteArrayList<Time> times = data.getTimeData();

	Operators operator = new Operators();
	operator.set(data, WorkerType.Basic);

	// This BasicOperators do:
	// to subtract the acceleration of gravity (9,81) from the vertical
	// acceleration data.
	// to change the sign of the acceleration along the z axis, in such a
	// way as to have an effective correspondence between accelerated and
	// braked.
	// to change the values ​​and units of the recording time (millisecond
	// -> second).
	// to change the values ​​and the speed unit (km / h -> m / s)
	operator.work();
	FilteringDataOperations filteringDataOperations = new FilteringDataOperations();
	filteringDataOperations.simpleMovingAverageFilter(data);

	CopyOnWriteArrayList<Double> beforeAvg = data.getAxesAcceleration(Axis.y);

	// this Filter Worker type, apply some filter on the set of the data
	// like SMA Filter (Simple Moving Average)

	operator.set(data, WorkerType.Filter);
	operator.work();
	data = operator.getResult();

	// after this operation, all the operations of elementary
	// transformations
	// and all filters will be applied, and the data will be ready for
	// the processing and application of numerical analysis techniques
	// and only after for the calculation of IRI

	CopyOnWriteArrayList<Double> after = data.getAxesAcceleration(Axis.y);

	// this writer is used to generated a CSV file with the value of the
	// vertical acceleration
	// before and than the application of SMA filter

	WriterForAverage wrf = new WriterForAverage(beforeAvg, after, times);
	wrf.write();

	double deltaT = data.get(1).getTime().getTime() - data.get(0).getTime().getTime();

	NumericalMethod numericalMethod = new NumericalMethod();
	// integrate two times to find displacement
	CopyOnWriteArrayList<Double> eulerResult = numericalMethod.eulerForwardMethod(data.getAxesAcceleration(Axis.y),
		deltaT);

	// write on csv file the result of eulerForwardMethod
	WriterForEuler eulerCsvWriter = new WriterForEuler(eulerResult, times);
	eulerCsvWriter.write();

	// ResultantDataOperations res = new ResultantDataOperations();
	// res.calculateResult(data.getData());
	// FileCreator htmlCreator = new FileCreator(res.getResult());
	// htmlCreator.createFile();
    }

}
