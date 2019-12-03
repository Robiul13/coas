package com.asl.asl_rms.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.asl.asl_rms.service.FileStorageService;
import com.google.gson.Gson;

public class CommonUtil {

	public static final String STATUS_SUBMITTED = "01";
	public static final String STATUS_IN_PROCESS = "02";
	public static final String STATUS_REJECTED = "03";
	public static final String STATUS_COMPLETED = "04";

	public static final String GENERIC_CERTIFICATE_UNMARRIED = "UNMARRIED CERTIFICATE";
	public static final String GENERIC_CERTIFICATE_NON_REMARRIAGE = "NON REMARRIAGE CERTIFICATE";
	public static final String GENERIC_CERTIFICATE_MONTHLY_YEARLY_INCOME = "MONTHLY/YEARLY INCOME CERTIFICATE";
	public static final String GENERIC_CERTIFICATE_INSOLVENCY = "INSOLVENCY CERTIFICATE";
	public static final String GENERIC_CERTIFICATE_FAMILY = "FAMILY CERTIFICATE";

	public static boolean isJSONValid(String JSON_STRING) {
		Gson gson = new Gson();
		try {
			gson.fromJson(JSON_STRING, Object.class);
			return true;
		} catch (com.google.gson.JsonSyntaxException ex) {
			return false;
		}
	}

	public static String getCookieValue(HttpServletRequest request, String cName) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cName)) {
					cookieValue = cookie.getValue();
				}
			}
		}
		return cookieValue;
	}

	
}
