package com.uncal.dt.remote.sh;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class UncalSshChannel {

	private String user;
	private String password;
	private String host;
	private int port=22;
	private String remoteDir;
	private ArrayList<String> listLocal;
	private Session session;
	private Channel channel;
	private List<ModelFile> listModelFile;
	private String mode;
	private JSONArray keadaanSambungan;
	private String salah="";
	private String status="";
	private ChannelExec channelExec;
	public static void main(String[] args) {
		UncalSshChannel ssh = new UncalSshChannel();
		ssh.setHost("192.168.1.56");
		ssh.setPort(22);
		ssh.setUser("root");
		ssh.setPassword("56_Admin_156");
		ssh.setMode("shell");
		ssh.Sambungkan();
		ssh.kirimPerintah("cd /home\nls\nexit\n");
//		ssh.kirimPerintah("ls -ltr");
//		ssh.kirimPerintah("ping 192.168.1.39 -c 1 -s 1");
//		ssh.kirimPerintah("top\n");
//		ssh.kirimPerintah("hostname\ndf -h\nexit\n");
//		ssh.kirimPerintah("df -h\nexit\n");
//		ssh.kirimPerintah("ps -aux\nexit\n");
		ssh.keluar();
//		cd /home;ls -ltr
//		/home/uncal/SFTP
//		/opt/uncal_engine/UE.2.0/Uncal/Log/
//		ssh.Mulai();
	}
	public UncalSshChannel() {
		listModelFile = new ArrayList<ModelFile>();
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
	public void setHost(String ip) {
		this.host = ip;
	}
	public void setPort(int p) {
		this.port = p;
	}
	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}
	public void Mulai() {
		info();
		connect();
		ambilListFile();
	}
	void info() {
		/*log.setLog(log.logFormat(new String[] {
				"Host				: " + host,
				"Port				: " + port,
				"User				: " + user,
				"Password			: " + "xxxxxxxxxx",
				"Mode				: " + mode,
				"Remote Dir			: " + remoteDir}), LogLevel.INFO);*/
	}
	void connect() {
		java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
	    try {
	    	session = jsch.getSession(user, host, port);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    } catch (JSchException e) {
		}
	}
	void ambilListFile() {
	    try {
			channel=session.openChannel("exec"); // sftp, exec, shell
			String perintah = "cd "+remoteDir+";ls -ltr";
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setCommand(perintah);
	        channelExec.setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	    	channel.connect();
	    	getStreamReader(in);
	    	channel.disconnect();
	    	for(ModelFile mf : listModelFile) {
	    		channel=session.openChannel("exec"); // sftp, exec, shell
	    		channelExec = (ChannelExec) channel;
				perintah = ambilFile(mf.getFullPath());
				channelExec.setCommand(perintah);
				OutputStream out=channel.getOutputStream();
				in=channel.getInputStream();
				channel.connect();
				bacaInputStream(out, in);
				channel.disconnect();
			}
	    	channel.disconnect();
	        session.disconnect();
		} catch (JSchException | IOException e) {
		}
	}
	String ambilFile(String path) {
		return "scp -f "+path;
	}
	void getStreamReader(InputStream in) {
		System.getProperty("line.separator");
		List<String> listFile = new ArrayList<String>();
		List<String> listDirectory = new ArrayList<String>();
		List<String> listContent = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String[] kunci = new String[] {"FilePermission","HardLink","Owner","Group","Size","Datetime","FileName","FullPath"};
		try {
			for (String line; (line = reader.readLine()) != null; ) {
				listContent.add(line);
				if(line.startsWith("-"))
					listFile.add(line);
				if(line.startsWith("d"))
					listDirectory.add(line);
			}
			in.close();
			reader.close();
		}catch(IOException e) {
		}
		// pecah files
		int indeks = 0;
		for(String str: listFile) {
			Map<String,String> map = new HashMap<String,String>();
			String temp=str;
			int p=0;
			String tempStr = "";
			for(int i = 0; i < 9;i++) {
				if(i<5) {
					p = temp.indexOf(" ");
					if(p>0) 
						map.put(kunci[i], temp.substring(0,p));
					temp = temp.substring(p,temp.length()).trim();
				}else if(i >= 5 && i<=7){
					p = temp.indexOf(" ");
					if(p>0)
						tempStr +=temp.substring(0,p)+"-";
					temp = temp.substring(p,temp.length()).trim();
				}else {
					tempStr = tempStr.substring(0,tempStr.length()-1);
					map.put(kunci[5], tempStr);
					map.put(kunci[6], temp);
					map.put(kunci[7], remoteDir.endsWith("/")?remoteDir+temp:remoteDir+"/"+temp);
					map.put("index", String.valueOf(indeks));
					indeks++;
				}
			}
			listModelFile.add(new ModelFile(map));
		}
		for(String str:listDirectory) {
//			System.out.println(str);
			String[] splits = str.split(" ");
			for(String split:splits) {
//				System.out.println(split);
			}
		}
		System.out.println(listContent);
	}
	void bacaInputStream(OutputStream out, InputStream in) throws IOException {
		FileOutputStream fos=null;
		String pathtmp = new File("").getAbsolutePath()+File.separator;
		byte[] buf=new byte[1024];
		buf[0]=0; out.write(buf, 0, 1); out.flush();
		while(true){
			int c=checkAck(in);
			if(c!='C'){
				break;
			}
			// read '0644 '
			in.read(buf, 0, 5);
			long filesize=0L;
			while(true){
				if(in.read(buf, 0, 1)<0){
					// error
					break; 
				}
				if(buf[0]==' ')break;
				filesize=filesize*10L+(long)(buf[0]-'0');
			}
			String file=null;
			for(int i=0;;i++){
				in.read(buf, i, 1);
				if(buf[i]==(byte)0x0a){
					file=new String(buf, 0, i);
					break;
				}
			}
			// send '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();
			// read a content of lfile
			pathtmp = pathtmp + file;
//			log.setLog("pathTemp : "+ pathtmp, LogLevel.INFO);
			fos=new FileOutputStream(pathtmp);
//			listLocal.add(pathtmp);
			int foo;
			while(true){
				if(buf.length<filesize) foo=buf.length;
				else foo=(int)filesize;
				foo=in.read(buf, 0, foo);
				if(foo<0){
					// error 
					break;
				}
				fos.write(buf, 0, foo);
				filesize-=foo;
				if(filesize==0L) break;
			}
			fos.close();
			fos=null;

			if(checkAck(in)!=0){
				System.exit(0);
			}
			// send '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();
		}
	}
	static int checkAck(InputStream in) throws IOException{
		int b=in.read();
		// b may be 0 for success,
		//          1 for error,
		//          2 for fatal error,
		//          -1
		if(b==0) return b;
		if(b==-1) return b;
		if(b==1 || b==2){
			StringBuffer sb=new StringBuffer();
			int c;
			do {
				c=in.read();
				sb.append((char)c);
			}
			while(c!='\n');
			if(b==1){ // error
				System.out.print(sb.toString());
			}
			if(b==2){ // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getError() {
		return salah;
	}
	public String getStatus() {
		return status;
	}
	public void Sambungkan() {
		info();
		java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
	    try {
	    	session = jsch.getSession(user, host, port);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	status = "tersambung";
	    } catch (JSchException e) {
	    	salah = e.getMessage();
		}
	}
	public boolean periksa() {
		return session.isConnected();
	}
	public void keluar() {
		session.disconnect();
	}
	public void kirimPerintah(String perintah) {
		switch(mode) {
		case "exec":
			try {
				channel = session.openChannel("exec"); // sftp, exec, shell
	//			((ChannelExec)channel).setPty(true);
		    	channelExec = (ChannelExec) channel;
				channelExec.setCommand(perintah);
				InputStream in = channel.getInputStream();
		    	channel.connect();
		    	keadaanSambungan = ubahKeString(in);
		    	channel.disconnect();
		    	System.out.println(keadaanSambungan);
			}catch(IOException | JSchException e) {
				salah = e.getMessage();
			}
			break;
		case "shell":
			try {
				channel = session.openChannel("shell");
	            channel.setInputStream(new ByteArrayInputStream(perintah.getBytes(StandardCharsets.UTF_8)));
	            channel.setOutputStream(System.out);
	            InputStream in = channel.getInputStream();
	            StringBuilder outBuff = new StringBuilder();
	            int exitStatus = -1;
	            channel.connect();
	            keadaanSambungan = ubahKeString(in);
		    	channel.disconnect();
		    	System.out.println(keadaanSambungan);
	           /* while (true) {                
	                for (int c; ((c = in.read()) >= 0);) {
	                    outBuff.append((char) c);
	                }
	                
	                if (channel.isClosed()) {
	                    if (in.available() > 0) continue;
	                    exitStatus = channel.getExitStatus();
	                    break;
	                }
	            }*/
	            channel.disconnect();
//	            System.out.print (outBuff.toString());
	            // print exit status
	            System.out.print ("Exit status of the execution: " + exitStatus);
	            if ( exitStatus == 0 ) {
	                System.out.print (" (OK)\n");
	            } else {
	                System.out.print (" (NOK)\n");
	            }
			}catch(IOException | JSchException e) {
				salah = e.getMessage();
			}
			break;
		case "sftp":
			break;
		}
	}
	public JSONArray ambilKeadaan() {
		return keadaanSambungan;
	}
	JSONArray ubahKeString(InputStream in){
		JSONArray oray = new JSONArray();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try {
			for (String line; (line = reader.readLine()) != null; )
				oray.put(line);
			in.close();
			reader.close();
		}catch(IOException e) {
		}
		return oray;
		/*ByteArrayOutputStream result = new ByteArrayOutputStream();
		 byte[] buffer = new byte[1024*100];
		 for (int length; (length = in.read(buffer)) != -1; ) {
		     result.write(buffer, 0, length);
		 }
		 return result.toString("UTF-8");*/
	}
}
