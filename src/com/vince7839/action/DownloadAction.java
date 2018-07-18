package com.vince7839.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class DownloadAction extends BaseAction {
	String type;
	String fileName;
	InputStream inputStream;
	public String query() {
		String path = "/resource/" + type;
		String dirPath = ServletActionContext.getServletContext().getRealPath(path);
		System.out.println(dirPath);
		File dir = new File(dirPath);
		String[] fileList = dir.list();
		System.out.println(fileList);
		buildJson(true, NO_ERROR, fileList);
		return FINISH;
	}
	
	public String file() {
		String path = "/resource/" + type +  "/" + fileName;
		String filePath = ServletActionContext.getServletContext().getRealPath(path);
		System.out.println(filePath);
		try {
			FileInputStream in;
			in = new FileInputStream(filePath);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] bytes = new byte[2048];
			while( in.read(bytes) > 0){
				out.write(bytes);
			}
			out.close();
			in.close();
			inputStream = new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("filename", fileName);
		return STREAM;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
