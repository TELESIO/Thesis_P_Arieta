package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javafx.scene.layout.Border;
import utils.FileType;

public class FilePreview extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel btn_panel;
	private JPanel rd_btn_pnl;
	private JCheckBox simple;
	private JCheckBox critical;
	private JCheckBox iri;

	private JButton load_button;
	private JButton close_button;
	private JLabel load_file_label;
	private JLabel close_file_label;
	private JTextArea file_content;
	private JScrollPane scroll_pane;
	private GuiManager guiManager;

	public FilePreview(File file, int width, int height, GuiManager guiManager) throws IOException {
		this.guiManager = guiManager;
		initGui(file, width, height);

	}

	public void close() {
		this.dispose();
	}

	private void initGui(File file, int width, int height) throws IOException {

		file_content = new JTextArea();
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while (line != null) {
			file_content.append(line + "\n");
			line = in.readLine();
		}

		this.setLayout(new BorderLayout(5, 10));

		file_content.setLineWrap(true);
		file_content.setWrapStyleWord(true);
		file_content.setCaretPosition(file_content.getDocument().getStartPosition().getOffset());
		file_content.setEditable(false);
		scroll_pane = new JScrollPane(file_content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll_pane, BorderLayout.CENTER);
		btn_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		load_file_label = new JLabel("Load file: ");
		load_button = new JButton("Load it!");
		btn_panel.add(load_file_label);
		btn_panel.add(load_button);

		close_file_label = new JLabel("Close file: ");
		close_button = new JButton("Close");
		btn_panel.add(close_file_label);
		btn_panel.add(close_button);

		rd_btn_pnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		simple = new JCheckBox("Simple");
		simple.setSelected(false);
		simple.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				critical.setSelected(false);
				iri.setSelected(false);			
			}
		});
		
		
		critical = new JCheckBox("Critical");
		critical.setSelected(false);
		critical.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iri.setSelected(false);
				simple.setSelected(false);
			}
		});
		
		
		iri = new JCheckBox("IRI");
		iri.setSelected(false);
		
		iri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				simple.setSelected(false);
				critical.setSelected(false);			
			}
		});

		rd_btn_pnl.add(simple);
		rd_btn_pnl.add(critical);
		rd_btn_pnl.add(iri);

		this.add(rd_btn_pnl, BorderLayout.NORTH);
		this.add(btn_panel, BorderLayout.SOUTH);

		this.setTitle(file.getAbsolutePath());
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setResizable(false);
		this.pack();
		this.toFront();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		load_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (iri.isSelected() || simple.isSelected() || critical.isSelected()) {
				
					
					int returnval = JOptionPane.showConfirmDialog(scroll_pane,
							"Are you sure to load:  " + file.getName(), "  Load File", JOptionPane.YES_NO_OPTION);
					if (returnval == JOptionPane.YES_OPTION) {
						if(iri.isSelected())
							guiManager.setFileType(FileType.IRI);
						else if(simple.isSelected())
							guiManager.setFileType(FileType.SIMPLE);
						else if(critical.isSelected())
							guiManager.setFileType(FileType.CRITICAL);
						guiManager.setFilePathAndCloseGui(file.getAbsolutePath());
					}
				}
				else{
					JOptionPane.showConfirmDialog(null,
			                "Select an Index",
			                "Index",
			                JOptionPane.DEFAULT_OPTION,
			                JOptionPane.PLAIN_MESSAGE);	}
			}
		});

		close_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnval = JOptionPane.showConfirmDialog(scroll_pane,
						"Are you sure to close:  " + file.getName() + "  and open another file?", "Close File",
						JOptionPane.YES_NO_OPTION);
				if (returnval == JOptionPane.YES_OPTION) {
					guiManager.reopenStartFrame();
				}
			}
		});

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		};

		this.addWindowListener(exitListener);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
}
