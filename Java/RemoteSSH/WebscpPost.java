package com.uncal.dt.remote.sh;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebscpPost {
	private String mode="shell";
	private String error="";
	private String host="192.168.1.56";
	private String user="root";
	private String password;
	private String connect;
	private UncalSshChannel ssh;
	
	@PostMapping(value="prnth")
	public String OlahPerintah(@RequestBody String perintah) throws JSONException, IOException {
//		proses(new JSONObject(perintah).getString("prnth"));
		System.out.println("perintah : " + perintah);
		JSONObject obj = new JSONObject(perintah);
		if(obj.has("tersambung")) {
			boolean b = Boolean.valueOf(obj.getBoolean("tersambung"));
			if(b) {
				String p = obj.getString("prnth");
				if(ssh!=null) {
					ssh.kirimPerintah(p);
					return ssh.ambilKeadaan().toString();
				}
			}
		}
		return proses(new JSONObject(perintah).getString("prnth"));
	}
	String proses(String p) {
		String hasil = "";
		String[] pr = p.split("[:]");
		if(pr.length>1) {
			switch(pr[0]) {
			case "Keperluan":
				if(!pr[1].equals("")) {
					mode = pr[1];
					hasil = "[\""+p+" --> Benar\"]";
				}else {
					error = "[salah keperluan\"]";
					hasil = "[\""+p+" --> Salah\"]";
				}
				break;
			case "SambungKe":
				if(!pr[1].equals("")) {
					host = pr[1];
					hasil = "[\""+p+" --> Benar\"]";
				}else {
					error = "[salah sambung\"]";
					hasil = "[\""+p+" --> Salah\"]";
				}
				break;
			case "Pengguna":
				if(!pr[1].equals("")) {
					user = pr[1];
					hasil = "[\""+p+" --> Benar\"]";
				}else {
					error = "[salah pengguna\"]";
					hasil = "[\""+p+" --> Salah\"]";
				}
				break;
			case "KataSandi":
				if(!pr[1].equals("")) {
					password = pr[1];
					hasil = "[\""+p+" --> Benar\"]";
				}else {
					error = "salah kata sandi\"]";
					hasil = "[\""+p+" --> Salah\"]";
				}
				break;
				default:
					hasil = "[\""+p+" --> Salah\"]";
			}
		}else {
			String[] per = p.trim().split(" "); 
			if(per.length>1) {
				System.out.println(p);
				EsHaExecuter sh = new EsHaExecuter();
				
			}else {
				switch(p) {
				case "tutup":
					if(ssh==null) {
						hasil = "[\"tidak tersambung\"]";
					}else {
						if(ssh.periksa()) {
							ssh.keluar();
							hasil = "[\"telah keluar dari sambungan\"]";
						}else {
							hasil = "[\"tidak tersambung\"]";
						}
					}
					break;
				case "periksa":
					if(ssh==null) {
						hasil = "[\"tidak tersambung\"]";
						break;
					}
					if(ssh.periksa()) {
						hasil = "[\"tersambung\"]";
					}else {
						hasil = "[\"tidak tersambung\"]";
					}
					break;
				case "Program":
					break;
				case "Sambungkan":
					System.out.println("akan disambungkan");
					System.out.println("mode : " + mode);
					System.out.println("host : " + host);
					System.out.println("user : " + user);
					System.out.println("password : " + password);
					if(mode!=null && host!=null && user!=null && password!=null) {
						hasil = "[\"Disambungkan\"]";
						ssh = new UncalSshChannel();
						String [] h = host.split(",");
						if(h.length==2) {
							ssh.setHost(h[0]);
							ssh.setPort(h[1].equals("")?22:Integer.valueOf(h[1]));
						}else {
							ssh.setHost(host);
							ssh.setPort(22);
						}
						ssh.setUser(user);
						ssh.setPassword(password);
						ssh.setMode(mode);
						ssh.Sambungkan();
						if(!ssh.getStatus().equals("")) {
							hasil = "[\""+ssh.getStatus()+"\"]";
							break;
						}
						if(!ssh.getError().equals("")) {
							hasil = "[\""+ssh.getError()+"[\"";
							break;
						}
						hasil = ssh.ambilKeadaan().toString();
					}else {
						hasil = "[\"Tidak akan disambungkan, karena masukannya tidak sesuai\"]";
					}
					break;
					default:
						hasil = "[\""+p+" --> Salah\"]";
				}
			}
		}
		System.out.println("Hasil : " + hasil);
		return hasil;
	}
}
