package com.fileupload.action;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.fileupload.model.UploadFileModel;
import com.fileupload.services.UploadFileService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * UploadFileAction is action class for fileupload Using File Interceptor , file
 * , file name and file content type are determined
 * 
 * @author Deepa Ramchandani
 *
 */
public class UploadFileAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UploadFileAction.class);

	private File file;
	private String contentType;
	private String filename;
	UploadFileService service = new UploadFileService();
	UploadFileModel model = new UploadFileModel();

	private List<UploadFileModel> fileList;

	public List<UploadFileModel> getFileList() {
		return fileList;
	}

	public void setFileList(List<UploadFileModel> fileList) {
		this.fileList = fileList;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public UploadFileAction() {
		// TODO Auto-generated constructor stub
	}

	public String display() {
		return NONE;
	}

	public String execute() {

		try {
			fileList = service.Readfile(file);
			addActionMessage(filename + " File uploaded successfully");
		} catch (Exception e) {
			log.error("Error inside execute method of UploadFileAction::" + e);
			addActionError("Error occurred during uploading of file");
			return INPUT;
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (file == null) {
			addActionError("You must select a file");
		}

		else if (!(contentType.equalsIgnoreCase("text/plain"))) {
			addActionError("This type of file not allowed");

		}

	}

}
