package reader.mp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;

import data.model.RawData;
import data.model.structure.DataContainer;
import reader.Reader;

public class MPCsvFileReader extends Reader {

    private final static int N_THREAD_MAX = Runtime.getRuntime().availableProcessors();
    private CSVReader reader;

    public MPCsvFileReader(final String path_file, final char data_separator) {
	super(path_file, data_separator);
	try {
	    reader = new CSVReader(new FileReader(path_file), data_separator);

	} catch (FileNotFoundException e) {
	    System.out.println("Error while load file, the resource is not available or not exists in: " + path_file);
	}
    }

    @Override
    public void read_file() {

	List<String[]> file_content = null;

	try {
	    file_content = reader.readAll();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	int real_thread = N_THREAD_MAX;

	int slice = (file_content.size()) / real_thread;

	int start_range = 0;
	int end_range = slice;

	while (slice == 0) {
	    real_thread--;
	    slice = (file_content.size()) / real_thread;
	}

	ReaderBarrier barrier = new ReaderBarrier(real_thread);
	MPLineReader[] thread_readers = new MPLineReader[real_thread];

	for (int index = 0; index < real_thread; index++) {

	    List<String[]> slice_toRead = new ArrayList<String[]>();

	    if (index == real_thread - 1)
		end_range = file_content.size();
	    else
		end_range = slice * (index + 1);

	    for (int y = start_range; y < end_range; y++) {
		String[] split = (file_content.get(y));
		slice_toRead.add(split);
	    }

	    start_range += slice;
	    thread_readers[index] = new MPLineReader(slice_toRead, barrier, index);
	    thread_readers[index].start();
	}

	barrier.await();

	Arrays.sort(thread_readers, new Comparator<MPLineReader>() {
	    @Override
	    public int compare(MPLineReader reader1, MPLineReader reader2) {
		return Integer.compare(reader1.get_T_Id(), reader2.get_T_Id());
	    }
	});

	for (int i = 0; i < thread_readers.length; i++) {
	    for (RawData data : thread_readers[i].getResult())
		raw_data.put(data);
	}

    }

    @Override
    public DataContainer getDataFileReaderResult() {
	return raw_data;
    }

}
