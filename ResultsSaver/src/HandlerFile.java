import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import handler.mt.FileHandlerWorker;
import handler.mt.NearestPointHandler;
import model.NearPoint;
import model.PointOnMap;
import persistence.PointOnMapJDBC;
import persistence.TableTypeSelector;
import persistence.dao.PointsOnMapManager;
import reader.Reader;
import reader.ReaderClass;
import reader.ReaderType;
import reader.mt.MPLineReader;
import utils.Barrier;
import utils.FileType;
import utils.color.ColorGenerator;
import utils.color.ColorGeneratorClass;

public class HandlerFile {

	private final static int N_THREAD_MAX = Runtime.getRuntime().availableProcessors();

	public static final char separator_comma = ',';
	public static final char separator_semicolon = ';';
	String path_file;
	FileType ft;
	String table_selector;
	ColorGenerator color_generator;
	PointsOnMapManager databaseType;

	public void handle() {

		// ************************************************************************************
		// lettura e creazione del colore per ogni punto.
		Reader r = ReaderClass.getInstance().getReader(ReaderType.MULTI_THREAD, path_file, separator_comma);
		r.read_file(color_generator);
		ArrayList<PointOnMap> to_insert = r.getDataFileReaderResult();
		// ************************************************************************************

		int previous_size = to_insert.size();
		NearestPointHandler np = new NearestPointHandler(to_insert, color_generator);
		to_insert = np.handle();
		int current_size = 0;
		while (current_size != previous_size) {
			NearestPointHandler np2 = new NearestPointHandler(to_insert, color_generator);
			to_insert = np2.handle();
			previous_size = current_size;
			current_size = to_insert.size();
		}

		if (databaseType.getNumberOfPoints() == 0)
			databaseType.insertPointsOnMap(to_insert);
		else {

			int real_thread = N_THREAD_MAX;
			int slice = (to_insert.size()) / real_thread;
			int start_range = 1;
			int end_range = slice;

			while (slice == 0) {
				real_thread--;
				slice = (to_insert.size()) / real_thread;
			}

			Barrier barrier = new Barrier(real_thread);
			FileHandlerWorker[] worker = new FileHandlerWorker[real_thread];

			for (int index = 0; index < real_thread; index++) {
				ArrayList<PointOnMap> slice_toRead = new ArrayList<PointOnMap>();
				if (index == real_thread - 1)
					end_range = to_insert.size();
				else
					end_range = slice * (index + 1);

				for (int y = start_range; y < end_range; y++) {
					slice_toRead.add(to_insert.get(y));
				}

				start_range += slice;
				worker[index] = new FileHandlerWorker(slice_toRead, barrier, color_generator, databaseType);
				worker[index].start();
			}

			barrier.await();

			// System.out.println("finito");
			// System.out.println("to" + to_insert.size());
			// for (int i = 0; i < worker.length; i++) {
			//
			// for (PointOnMap insert : worker[i].getTo_insert())
			// databaseType.insertPointOnMap(insert);
			// System.out.println("toinsert");
			//
			// System.out.println(worker[i].getTo_update().size());
			// int index = 0;
			// for (PointOnMap update : worker[i].getTo_update()) {
			// if(index!=148)
			// databaseType.updatePoint(update.getLatitude(),
			// update.getLongitude(), update.getDate(),
			// update.getDisplacement(), update.getColor());
			// index++;
			// System.out.println("aggiornato: " + index);
			// }
			// System.out.println("toupdate");
			// }

		}
	}

	public HandlerFile(String path_file, FileType ft) {
		this.path_file = path_file;
		this.ft = ft;
		TableTypeSelector t = new TableTypeSelector();
		table_selector = t.getTableSelector(ft);
		databaseType = new PointOnMapJDBC(table_selector);
		color_generator = ColorGeneratorClass.getInstance().getColorGenerator(ft);

	}

}
