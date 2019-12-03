package com.asl.asl_rms.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LicensceUtil {

	// m@@ added by Ashid. taskid: ALWA_01
	public static ActionResult resolveLicenseASL() {
		ActionResult re = new ActionResult();
		License license = new License();
		String data = "";
		String signature = "";
		Document document = null;
		try {
			URI uri = new URI(LicensceUtil.class.getResource("/license.lic").getFile());
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(uri.getPath()));
			Element root = document.getRootElement();

			String mac = getString(root.elementText("mac"));
			data = data + mac;

			String client = getString(root.elementText("client"));
			data = data + client;

			String expiration = getString(root.elementText("expiration"));
			data = data + expiration;

			String validDay = getString(root.elementText("validday"));
			data = data + validDay;

			license.setCpuId(mac);
			license.setExpDate(expiration);

			signature = getString(root.elementText("signature"));
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMsg("Exception in processing license File!");
			return re;
		}

		boolean status = false;
		try {
			URI uri = new URI(LicensceUtil.class.getResource("/configuration.properties").getFile());
			FileInputStream is = new FileInputStream(uri.getPath());
			Properties props = new Properties();
			props.load(is);
			String licensePublicKey = (String) props.get("licensePublicKey");

			status = RSACoder.verify(data.getBytes(), licensePublicKey, signature);
		} catch (Exception e) {
			status = false;
		}

		if (!status) {
			re.setSuccess(false);
			re.setMsg("License is invalid！");
			return re;
		}

		re.setSuccess(true);
		re.setDataObject(license);
		return re;
	}

	public static String getString(Object s) {
		return s == null ? "" : String.valueOf(s);
	}
	
	// m@@ added by Ashid. taskid: ALWA_01
		public static void loadLicenseASL() {
			System.out.println("Validating the system licence ... ");
			ActionResult rs = resolveLicenseASL();

			if (!rs.isSuccess()) {
				System.out.println("The License is illegal,please re-apply for a new license!");
				System.exit(1);
			}

			Map sysMap = new HashMap();

			License license = (License) rs.getDataObject();
			license.setExpLimitFlag(true);

			if (license.isExpLimitFlag()) {
				String expDateNew = license.getExpDate();
				expDateNew = expDateNew == null ? "" : expDateNew;
				long leftMins = 0L;
				try {
					leftMins = DateUtil.getMinuteDiff(new Date(),
							DateUtil.DATE_FORMAT_SHORT.parse(expDateNew));
				} catch (ParseException e) {
				}
				if (leftMins <= 0L) {
					System.out.println("Sorry,the system license expires, apply for a new license!");
					System.exit(-1);
				}

			}

			String macStr = "";
			Enumeration<NetworkInterface> networks;
			try {
				networks = NetworkInterface.getNetworkInterfaces();
				NetworkInterface inter;
				while (networks.hasMoreElements()) {
					inter = networks.nextElement();
					byte[] mac = inter.getHardwareAddress();
					if (mac != null) {
						for (int i = 0; i < mac.length; i++) {
							macStr += String.format("%02X%s", mac[i],
									(i < mac.length - 1) ? ":" : "");
						}
						macStr += ",";
					}
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!StringUtil.isEmptyString(macStr)) {
				if (macStr.contains(license.getCpuId().toUpperCase())) {
					System.out.println("Validation system license is complete!");
				} else {
					System.out
							.println("The machine information not authorized,please re-apply for a new license!");
					System.exit(-1);
				}
			} else {
				System.out.println("The machine information not found!");
				System.exit(-1);
			}

			

		}


	
}
