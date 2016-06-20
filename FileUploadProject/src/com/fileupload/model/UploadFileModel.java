package com.fileupload.model;

/**
 * UploadFileModel acts as Form Object to store various values read from file
 * 
 * @author Deepa Ramchandani
 *
 */
public class UploadFileModel {

	private String fileDate;
	private int fileRow;
	private String fileValue;
	private String fileDesc;

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public int getFileRow() {
		return fileRow;
	}

	public void setFileRow(int fileRow) {
		this.fileRow = fileRow;
	}

	public String getFileValue() {
		return fileValue;
	}

	public void setFileValue(String fileValue) {
		this.fileValue = fileValue;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	@Override
	public String toString() {
		return "\nDate=" + getFileDate() + ":: Row=" + getFileRow() + "::Value" + getFileValue() + "::Desc "
				+ getFileDesc();
	}

}
