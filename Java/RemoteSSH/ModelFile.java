package com.uncal.dt.remote.sh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class ModelFile {

	private String FilePermission;
	private String HardLink;
	private String Owner;
	private String Group;
	private String Size;
	private Date Datetime;
	private String FileName;
	private String FullPath;
	private int indeks;
	public String getFullPath() {
		return FullPath;
	}
	private String[] kunci = new String[] {"FilePermission","HardLink","Owner","Group","Size","Datetime","FileName","FullPath"};
	private String prefix;
	public ModelFile() {}
	public ModelFile(Map<String,String> map) {
		FilePermission = map.get(kunci[0]);
		HardLink = map.get(kunci[1]);
		Owner = map.get(kunci[2]);
		Group = map.get(kunci[3]);
		Size = map.get(kunci[4]);
		Datetime =  tgl(map.get(kunci[5]));
		FileName = map.get(kunci[6]);
		FullPath= map.get(kunci[7]);
		this.prefix = map.get("Prefix");
		indeks = Integer.valueOf(map.get("index"));
	}
	public ModelFile(String filePermission, String hardLink, String owner, String group, String size, String datetime, String fileName, int index, String prefix) {
		super();
		FilePermission = filePermission;
		HardLink = hardLink;
		Owner = owner;
		Group = group;
		Size = size;
		Datetime = tgl(datetime);
		FileName = fileName;
		indeks = index;
		this.prefix = prefix;
	}
	public String getPrefix() {
		return this.prefix;
	}
	public int getIndex() {
		return indeks;
	}
	public void setIndeks(int index) {
		indeks = index;
	}
	Date tgl(String strd) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy");
		try {
			return sdf.parse(strd);
		} catch (ParseException e) {
			return null;
		}
		
	}
	public String getFilePermission() {
		return FilePermission;
	}
	public void setFilePermission(String filePermission) {
		FilePermission = filePermission;
	}
	public String getHardLink() {
		return HardLink;
	}
	public void setHardLink(String hardLink) {
		HardLink = hardLink;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getGroup() {
		return Group;
	}
	public void setGroup(String group) {
		Group = group;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public Date getDatetime() {
		return Datetime;
	}
	public void setDatetime(String datetime) {
		Datetime = tgl(datetime);
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
}
