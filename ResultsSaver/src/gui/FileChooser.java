package gui;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private String fileName;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
	private JFileChooser chooser;
	private GuiManager gui_manager;

	public String getFileName() {
		return fileName;
	}

	public FileChooser(GuiManager gui_manager, int width, int height) {
		this.gui_manager = gui_manager;
		initGui(width,height);
	}

	@SuppressWarnings("serial")
	private void initGui(int width, int height) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		chooser = new JFileChooser() {
			@Override
			public void approveSelection() {

				if (!getSelectedFile().exists()) {
					int returnVal = JOptionPane.showConfirmDialog(this,
							"The file you are selected doesn't exist,	" + "Do you wanna close?", "File not found",
							JOptionPane.YES_NO_OPTION);
					if (returnVal != JOptionPane.YES_OPTION) {
						setSelectedFile(new File(""));
						return;
					}
				} else if (chooser.getSelectedFile().getAbsolutePath().endsWith(".csv")) {
					chooser.cancelSelection();
					gui_manager.showFilePreview(getSelectedFile());
				}

				super.approveSelection();
			}
		};
		chooser.setPreferredSize(new Dimension(width, height));
		chooser.setMaximumSize(new Dimension(width, height));
		chooser.setMinimumSize(new Dimension(width, height));
		chooser.setDialogTitle("Select File");
		chooser.setDialogType(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setFileFilter(filter);	
		chooser.setAcceptAllFileFilterUsed(false);
		int returned = chooser.showOpenDialog(null);

		if (returned == JFileChooser.CANCEL_OPTION)
			gui_manager.init();

	}
}
