package com.asl.asl_rms.intercepter;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SampleInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	Enforcer enforcer;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String obj = requestURI.substring(request.getContextPath().length());

		if (obj.startsWith("/dncc21/")) {
			return true;
		}

		// if(requestURI.startsWith("/assets") ||
		if (obj.startsWith("/assets") || obj.startsWith("/uploads") || obj.startsWith("/error")) {
			return true;
		}

		String sub = getPrincipal();
		String act = request.getMethod();

		if (enforcer.enforce(sub, obj, act)) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private String getPrincipal() {
		String userName = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			System.out.println("61: SecurityContextHolder.getContext().getAuthentication() is null");
		} else {
			System.out.println("63: isAuthenticated" + auth.isAuthenticated());
		}

		Object principal = auth.getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}