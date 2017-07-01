package reader;

import java.util.ArrayList;

import model.PointOnMap;
import utils.color.ColorGenerator;

public abstract class Reader {
    protected String path_file;
    protected char data_separator;
    protected ArrayList<PointOnMap> container;

    public Reader(final String path_file, final char data_separator) {
		this.path_file = path_file;
		this.data_separator = data_separator;
		this.container = new ArrayList<PointOnMap> ();
    }

    public abstract void read_file(ColorGenerator color_generator);

    public abstract ArrayList<PointOnMap> getDataFileReaderResult();

}
