import gui.GuiManager;
import utils.FileType;

public class MainTest {



	public static void main(String[] args) {

		GuiManager gw = new GuiManager();
		String path_file = null;
		FileType ft = null;
		try {
			path_file = gw.getFilePath();
			ft = gw.getFileType();
			System.out.println("Sono qua " + path_file);
			System.out.println(ft);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		HandlerFile hnd = new HandlerFile(path_file, ft);
		hnd.handle();
	}

}
