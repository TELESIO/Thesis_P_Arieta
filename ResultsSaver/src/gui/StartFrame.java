package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton open_button;
	private JLabel open_label;
	private GuiManager guiManager;
	
	
	public StartFrame(int width, int height, GuiManager guiManager) {
		this.guiManager = guiManager;
		
		this.setTitle("ApplicationName");
		this.setLayout(new BorderLayout(20,10));
		panel =new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		open_label = new JLabel("Open file: ");
		open_button = new JButton("Open!");
		open_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StartFrame.this.guiManager.openFileChooser();
			}
	
		});
		panel.add(open_label);
		panel.add(open_button);
		
		this.add(panel, BorderLayout.NORTH);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setResizable(false);
		this.pack();
		this.toFront();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
		this.addWindowListener(exitListener);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
}
