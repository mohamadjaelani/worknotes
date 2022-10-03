package com.pe.ge.pe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsPendenge {

	public void Mulai(){
		String method = "GET";
		String path = "https://uncal-dt.com";
//		payload yg sudah di ubah ke bytearray
		byte[] payload = "ini adalah payload".getBytes();
		try {
			SSLContext sslctx = SSLContext.getInstance("TLS");
			TrustManager[ ] certs = new TrustManager[ ] {
					new X509TrustManager() {
						public X509Certificate[ ] getAcceptedIssuers() { return null; }
						public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
						public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
					}
			};
			sslctx.init(null, certs, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslctx.getSocketFactory());
			URL url = new URL(path);
			String hostname = url.getHost();
			HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
			httpsConn.setDoInput(true);
			httpsConn.setDoOutput(true);
			httpsConn.setConnectTimeout(10000); // boleh pake atau tidak
			httpsConn.setRequestMethod(method);
			httpsConn.setRequestProperty("User-Agent", "Safira v.1.0");
			httpsConn.setRequestProperty("Content-Length", payload.length+"");
			httpsConn.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String host, SSLSession sess) {
					if (host.equals(hostname)) return true;
					else return false;
				}
			});
			DataOutputStream out = new DataOutputStream(httpsConn.getOutputStream());
			InputStream in = httpsConn.getInputStream();
//			kirim payload 
			if(payload.length>0) {
				out.write(payload);
				out.write("\r\n".getBytes());
				out.flush();
			}
//			ambil response
			InputStreamReader input = new InputStreamReader(in);
			BufferedReader buffer = new BufferedReader(input);
			StringBuffer sb = new StringBuffer();
			String line = "";
			while((line=buffer.readLine())!=null) {
				sb.append(line);
			}
			String responseStr = sb.toString();
			System.out.println("response : " +responseStr);
		}catch(KeyManagementException | NoSuchAlgorithmException | IOException e) {
//			ambil error message
			System.out.println(e.getMessage());
		}
	}
}
