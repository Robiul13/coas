package com.asl.asl_rms.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asl.asl_rms.model.CitizenCertificate;
import com.asl.asl_rms.model.Complain;
import com.asl.asl_rms.model.GenericCertificate;
import com.asl.asl_rms.model.HeirCertificate;
import com.asl.asl_rms.service.CitizenCertificateService;
import com.asl.asl_rms.service.ComplainService;
import com.asl.asl_rms.service.GenericCertificateService;
import com.asl.asl_rms.service.HeirCertificateService;
import com.asl.asl_rms.service.RoleService;
import com.asl.asl_rms.service.UserService;
import com.asl.asl_rms.util.GlobalMethod;

@Controller
public class WelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	Enforcer enforcer;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Autowired
	Environment environment;
	@Autowired
	GlobalMethod globalMethod;

	@Autowired
	ComplainService complainService;

	@Autowired
	CitizenCertificateService ccService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model, HttpServletRequest request) {
		return "redirect:/adminpanel/dashboard";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, ModelMap map) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@Autowired
	HeirCertificateService hcService;

	@Autowired
	GenericCertificateService gcService;

	@RequestMapping(value = { "/adminpanel/dashboard" }, method = RequestMethod.GET)
	public String superadmin(Model model, HttpSession httpSession) {
		List<String> stringList = getAllFromCasbin(globalMethod.getRole());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());

		List<Complain> complainList = complainService.findAllByStatus("01");
		model.addAttribute("complainNew", complainList.size());

		List<CitizenCertificate> citizenList = ccService.findAllByStatus("01");
		model.addAttribute("citizenNew", citizenList.size());

		List<HeirCertificate> heirList = hcService.findAllByStatus("01");
		model.addAttribute("heirNew", heirList.size());

		List<GenericCertificate> gcList = gcService.findAllByStatus("01");
		model.addAttribute("gcNew", gcList.size());

		return "/dashboard/dashboard";
	}

	@RequestMapping(value = { "/adminpanel/lock/lockmysession" }, method = RequestMethod.GET)
	public String lockmysession(Model model, HttpSession httpSession) {
		String userName = null;
		String password = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			password = ((UserDetails) principal).getPassword();

		} else {
			userName = principal.toString();
		}

		httpSession.setAttribute("password", password);

		model.addAttribute("fullname", globalMethod.getPrincipalFullName());
		return "lockscreen";
	}

	/*
	 * private String getPrincipal() { String userName = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName = ((UserDetails)
	 * principal).getUsername(); } else { userName = principal.toString(); }
	 * System.out.println(userName); return userName; }
	 */
	public List<String> getAllFromCasbin(String role) {
		List<String> stringList = new ArrayList<>();
		try {
			Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

			String url = environment.getRequiredProperty("spring.datasource.url");
			String username = environment.getRequiredProperty("spring.datasource.username");
			String password = environment.getRequiredProperty("spring.datasource.password");

			Connection connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT v1 from casbin_rule WHERE v0=? AND v2=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, role);
			statement.setString(2, "GET");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				stringList.add(resultSet.getString("v1"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringList;
	}
}
