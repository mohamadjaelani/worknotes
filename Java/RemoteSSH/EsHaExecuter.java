package com.uncal.dt.remote.sh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;

public class EsHaExecuter {

	private JSONArray oray;
	public static void main(String[] args) {
		EsHaExecuter sh = new EsHaExecuter();
		if(args.length==3)
			sh.shExecuter(args[0],args[1],args[2]);
		if(args.length==2)
			sh.shExecuter(args[0],args[1]);
		if(args.length==1)
			sh.shExecuter(args[0]);
		if(args.length==0)
			sh.shExecuter();
		System.out.println(sh.getOrray());
	}
	void shExecuter(String p1){
		oray = new JSONArray();
		try {
			System.out.println(p1);
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command(p1);
	        Process process = processBuilder.start();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	System.out.println("hasil : " + line);
	            	oray.put(line);
	            }
	        }
	        process.destroy();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	void shExecuter(String p1, String p2){
		oray = new JSONArray();
		try {
			System.out.println(p1 + " " + p2);
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command(p1,p2);
	        Process process = processBuilder.start();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	System.out.println("hasil : " + line);
	            	oray.put(line);
	            }
	        }
	        process.destroy();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	void shExecuter() {
		ProcessBuilder pb = new ProcessBuilder("top");
		pb.redirectError();
		try {
		    Process p = pb.start();
		    InputStream is = p.getInputStream();
		    int value = -1;
		    while ((value = is.read()) != -1) {
		        System.out.print(((char)value));
		    }
		    int exitCode = p.waitFor();
		    System.out.println("Top exited with " + exitCode);
		} catch (IOException exp) {
		    exp.printStackTrace();
		} catch (InterruptedException ex) {
		    ex.printStackTrace();
		}
	}
	void shExecuter(String p1, String p2, String p3){
		oray = new JSONArray();
		try {
			System.out.println(p1 + " " + p2 + " " + p3);
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command(p1,p2,p3);
	        Process process = processBuilder.start();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	System.out.println("hasil : " + line);
	            	oray.put(line);
	            }
	        }
	        process.destroy();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public JSONArray getOrray() {
		return oray;
	}
}
