package com.asl.asl_rms.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asl.asl_rms.model.Notice;
import com.asl.asl_rms.service.ConfiguarationItemService;
import com.asl.asl_rms.service.NoticeService;
import com.asl.asl_rms.util.CommonUtil;

@Controller
public class Dncc21Controller {

	@Autowired
	private Environment environment;
	
	@Autowired
	ConfiguarationItemService confItemService;

	private static final Logger logger = LoggerFactory.getLogger(Dncc21Controller.class);

	@RequestMapping(value = { "/dncc21/home" }, method = RequestMethod.GET)
	public String dnccHomeBn(Model model, HttpSession httpSession) {
		Date date = new Date();
		model.addAttribute("now", date.toString());
		model.addAttribute("lang", "bn");
		return "/dncc21/index";
	}
	
	@RequestMapping(value = { "/dncc21/services/citizen" }, method = RequestMethod.GET)
	public String getCitizenPage(Model model, HttpSession httpSession) {

		return "/dncc21/citizenCertificate";
	}
	
	

	@RequestMapping(value = { "/dncc21/services/heir" }, method = RequestMethod.GET)
	public String getHeirPage(Model model, HttpSession httpSession) {

		return "/dncc21/heirCertificate";
	}
	
	
	
	@RequestMapping(value = { "/dncc21/services/others" }, method = RequestMethod.GET)
	public String getOtherCertificatePage(Model model, HttpSession httpSession) {

		return "/dncc21/othersCertificate";
	}
	
	@RequestMapping(value = { "/dncc21/services/neonate" }, method = RequestMethod.GET)
	public String getNeonateForm(Model model, HttpSession httpSession) {

		return "/dncc21/neonate";
	}
	
	@RequestMapping(value = { "/dncc21/complain" }, method = RequestMethod.GET)
	public String dnccComplain(Model model, HttpSession httpSession) {

		return "/dncc21/complain";
	}
	
	@RequestMapping(value = { "/dncc21/social" }, method = RequestMethod.GET)
	public String dnccSocial(Model model, HttpSession httpSession) {

		return "/dncc21/social";
	}
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = { "/dncc21/noticeBoard" }, method = RequestMethod.GET)
	public String noticeBoard(Model model, HttpServletRequest request) {
		
		String lang=CommonUtil.getCookieValue(request, "lang");
		lang=lang==null?"bn":lang;
		
		List<Notice> noticeList=noticeService.findAll();
		if("bn".equals(lang)) {
			for (Notice notice : noticeList) {
				notice.setTitle(notice.getTitleBn());
			}	
		}
		
		model.addAttribute("noticeList", noticeService.findAll());
		return "/dncc21/noticeBoard";
	}


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
