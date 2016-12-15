package data.reader;

import data.model.structure.DataContainer;

public abstract class Reader {
    protected String path_file;
    protected char data_separator;
    protected DataContainer raw_data;

    public Reader(final String path_file, final char data_separator) {

	this.path_file = path_file;
	this.data_separator = data_separator;
	this.raw_data = new DataContainer();

    }

    public abstract void read_file();

    public abstract DataContainer getDataFileReaderResult();

}
