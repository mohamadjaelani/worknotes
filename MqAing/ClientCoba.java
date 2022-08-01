package com.uncal.dt.h2.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientCoba {

	public static void main(String[] args) {
		Scanner scan;// = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost",8081);
			while(true) {
				scan = new Scanner(System.in);
				String inputString = scan.nextLine();
				System.out.println(inputString);
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();
				out.write(inputString.getBytes());
				out.flush();
				while(in.available()==0) {}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while(in.available()>0) {
					baos.write(in.read());
				}
				System.out.println("Response : " + baos.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
