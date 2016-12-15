import java.util.HashMap;

import data.manipulation.ResultantDataOperations;
import data.manipulation.mp.BasicOperators;
import data.manipulation.mp.FilterOperator;
import data.model.RawData;
import data.model.ResultData;
import data.model.structure.DataContainer;
import data.reader.Reader;
import data.reader.mp.MPCsvFileReader;

public class Main {
    public static void main(String[] args) {
	Reader r = new MPCsvFileReader("file/Fourth.csv", ',');
	r.read_file();
	DataContainer data = r.getDataFileReaderResult();

	BasicOperators operator = new BasicOperators(data);
	operator.work();

	FilterOperator f_operator = new FilterOperator(operator.getResult().getData());
	f_operator.work();

	HashMap<Integer, RawData> filteredData = new HashMap<Integer, RawData>();
	filteredData = f_operator.getResult();

	ResultantDataOperations res = new ResultantDataOperations();
	res.calculateResult(filteredData);
	HashMap<Integer, ResultData> result = res.getResult();

	FileCreator htmlCreator = new FileCreator(result);
	htmlCreator.createFile();
    }
}
