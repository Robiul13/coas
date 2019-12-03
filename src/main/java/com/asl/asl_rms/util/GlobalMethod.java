package com.asl.asl_rms.util;

import com.asl.asl_rms.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Component
public class GlobalMethod {
	public String getPrincipal() {
		String userName = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();

		} else {
			userName = principal.toString();
		}
		System.out.println("The loged in user name is : " + userName);
		return userName;
	}

	public String getPrincipalFullName() {

		String userFullname = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			userFullname = ((User) principal).getFirstName() + " " + ((User) principal).getLastName();
		} else {
			userFullname = principal.toString();
		}

		return userFullname;
	}

	public String getRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("isAuthenticated" + auth.isAuthenticated());
		User user = ((User) auth.getPrincipal());
		String name = user.getRoles().iterator().next().getName();
		return name;
	}

	Date date = null;

	public Date getDate() {
		String timeStampl = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String pattern = "yyyy-mm-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			date = simpleDateFormat.parse(timeStampl);
			System.out.println(" " + date);
		} catch (ParseException e) {
			System.out.println("The exception is : " + e);
		}
		System.out.println("Date:" + date);
		return date;
	}
}