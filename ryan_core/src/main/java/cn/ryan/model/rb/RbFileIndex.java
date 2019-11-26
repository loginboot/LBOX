package cn.ryan.model.rb;

import java.io.Serializable;

import cn.ryan.model.AbstractEntity;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-11-10
 * @description 文件索引
 *
 */
public class RbFileIndex extends AbstractEntity implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String pkey;

	private int fileType;

	private String fileName;

	private String fileIndex;

	private String fileUpdateDate;

	private long fileSize;

	private int readTimes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}

	public String getFileUpdateDate() {
		return fileUpdateDate;
	}

	public void setFileUpdateDate(String fileUpdateDate) {
		this.fileUpdateDate = fileUpdateDate;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}

}
