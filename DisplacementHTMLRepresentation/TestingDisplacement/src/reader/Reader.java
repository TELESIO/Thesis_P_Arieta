package reader;

import java.util.ArrayList;

import data.Data;

public abstract class Reader {
    protected String path_file;
    protected char data_separator;
    protected ArrayList<Data> container;

    public Reader(final String path_file, final char data_separator) {
		this.path_file = path_file;
		this.data_separator = data_separator;
		this.container = new ArrayList<Data> ();
    }

    public abstract void read_file();

    public abstract ArrayList<Data> getDataFileReaderResult();

}
