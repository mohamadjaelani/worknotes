package com.uncal.dt.h2.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Scanner;

public class ClientCoba2 {

	public static void main(String[] args) {
		Scanner scan;// = new Scanner(System.in);
		try {
			Socket socket = new Socket("74.125.200.100",80);
//			while(true) {
				scan = new Scanner(System.in);
				String inputString = scan.nextLine();
				System.out.println(inputString);
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();
				String data = URLEncoder.encode("pesan", "UTF-8") + "=" + URLEncoder.encode(inputString, "UTF-8");
				System.out.println(data);
				header(out,data);
				out.write(data.getBytes());
				out.flush();
				while(in.available()==0) {}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while(in.available()>0) {
					baos.write(in.read());
				}
				System.out.println("Response : " + baos.toString());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void header(OutputStream out,String msg) throws IOException {
		int l = msg.length();
		out.write("POST /app HTTP/1.1\r\n".getBytes());
		out.write("Host: localhost/app\r\n".getBytes());
		out.write("Content-Type: application/x-www-form-urlencoded; charset=utf-8\r\n".getBytes());
		out.write(("Content-Length: " + l + "\r\n").getBytes());
		out.write("User-Agent: Simple Http Client\r\n".getBytes());
		out.write("Accept: text/html\r\n".getBytes());
		out.write("Accept-Language: en-US\r\n".getBytes());
		out.write("Connection: close\r\n".getBytes());
		out.write("\r\n".getBytes());
	}
}
