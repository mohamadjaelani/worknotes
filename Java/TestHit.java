package nu.aing.tea.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class TestHit {
	
	private HttpsURLConnection httpsConn;
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 20; i++) {
			new TestHit(i);
		}
	}
	
	public TestHit(int index) throws IOException {
		String uri = "https://asia-uat-indonesia.apis.allianz.com/credit-life/oauth2/token?grant_type=client_credentials&oe_id=id-lh";
		URL url = new URL(uri);
		httpsConn = (HttpsURLConnection) url.openConnection();
		String cred = "GxUWAXtC4DP11iJjG4o5Fr2IKCdb0VNj:3ukFu8i7PnaqHkfm";
		String authorisasi = "Basic R3hVV0FYdEM0RFAxMWlKakc0bzVGcjJJS0NkYjBWTmo6M3VrRnU4aTdQbmFxSGtmbQ==";
		httpsConn.setRequestProperty("Authorization", authorisasi);
		httpsConn.setRequestMethod("POST");
		byte[] b = "".getBytes("utf-8");
		httpsConn.setDoInput(true);
		httpsConn.setDoOutput(true);
		httpsConn.setRequestProperty("Content-Length", "0");
		httpsConn.setRequestProperty("User-Agent", "aing");
		httpsConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		DataOutputStream out = new DataOutputStream(httpsConn.getOutputStream());
		out.write(b);
		out.write("\r\n".getBytes());
		out.flush();
		out.close();
		httpsConn.connect();
		InputStreamReader input = null;
		StringBuffer sb = new StringBuffer();
		InputStream stream = httpsConn.getErrorStream();
		if(stream ==null) {
			stream = httpsConn.getInputStream();
			input = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(input);
			String line = "";
			
			while((line=buffer.readLine())!=null) {
				sb.append(line);
			}
			String responseStr = sb.toString();
			System.out.println("Request ke : " + index);
			System.out.println("Response ke :"+ index + " " + responseStr);
		}

	}

}
