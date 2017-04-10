import java.util.ArrayList;
import data.Data;
import reader.CsvFileReader;
import reader.Reader;

public class MainTest {

	

    public static final char separator_comma = ',';
    public static final char separator_semicolon = ';';
    public static final String path_input_file = "file/dav.csv";

    public static void main(String[] args) {
    	Reader r = new CsvFileReader(path_input_file, separator_comma);
    	r.read_file();
    	ArrayList<Data> data = r.getDataFileReaderResult();

    	ArrayList<Data> first1000 = new ArrayList<Data> ();
    	
    	for (int i = 20000; i < 30000; i++)
    	{
    		first1000.add(data.get(i));
    		
    	}

    	FileCreator f = new FileCreator(data);
    	f.createFile();
    }
	
}
