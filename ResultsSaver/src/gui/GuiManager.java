package gui;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import utils.FileType;

public class GuiManager{

	
	@SuppressWarnings("unused")
	private FileChooser file_chooser;
	private FilePreview file_preview;
	private FileType fileType;
	


	private StartFrame startFrame;
	private String path_file;
	private int width;
	private int height;
	private Lock lock = new ReentrantLock();
	private boolean file_choose = false;
	private Condition condition = lock.newCondition();
	
	public GuiManager() {
		width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2;
		height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2;
		init();
	}
	
	protected void init(){
		startFrame = new StartFrame(width, height, this);
	}
	
	
	protected void openFileChooser() {
		startFrame.dispose();
		file_chooser = new FileChooser(this, width, height);
	}	


	protected void showFilePreview(File file) {
		try {
			file_preview = new FilePreview(file, width, height, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	protected void setFilePathAndCloseGui(String absolutePath) {
		lock.lock();
		path_file = absolutePath;
		file_preview.dispose();
		file_choose = true;
		condition.signalAll();
		lock.unlock();
		
	}


	protected void reopenStartFrame() {
		file_preview.dispose();
		startFrame = new StartFrame(width, height, this);
	}
	

	public String getFilePath() throws InterruptedException{
		lock.lock();
		try {
		while(!file_choose)
			condition.await();
		return path_file;
		} finally {
			lock.unlock();
		}
	}
	
	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

}
