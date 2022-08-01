package com.uncal.dt.h2.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceCoba {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8081);
			while(true) {
				Socket socket = ss.accept();
				new clientHandler(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class clientHandler extends Thread{
	private Socket socket;
	public clientHandler(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		while(true) {
			try {
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos.write(in.read());
				if(in.available()==0) {
					Thread.sleep(10);
					continue;
				}
				while(in.available()>0) {
					baos.write(in.read());
				}
				System.out.println("Message "+baos.toString());
				out.write(("Request di terima pada "+System.currentTimeMillis()).getBytes());
				out.flush();
				Thread.sleep(100);
			}catch(IOException | InterruptedException e) {
				try {
					socket.close();
					System.out.println("Keluar");
					break;
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
	}
}
