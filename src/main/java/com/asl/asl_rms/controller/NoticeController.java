package com.asl.asl_rms.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.asl.asl_rms.configuration.SecurityUtility;
import com.asl.asl_rms.model.Notice;
import com.asl.asl_rms.model.Role;
import com.asl.asl_rms.model.User;
import com.asl.asl_rms.service.FileStorageService;
import com.asl.asl_rms.service.NoticeService;
import com.asl.asl_rms.service.RoleService;
import com.asl.asl_rms.service.UserService;
import com.asl.asl_rms.util.GlobalMethod;

@Controller
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	List<Role> roleList = new ArrayList<>();
	private Connection connection;
	@Autowired
	private Environment environment;
	@Autowired
	Enforcer enforcer;
	@Autowired
	GlobalMethod globalMethod;

	@Autowired
	NoticeService noticeService;

	

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("noticeList", noticeService.findAll());
		List<String> stringList = getAllFromCasbin(
				userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());
		return "/dashboard/notice/notice_list";
	}

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/addNotice" }, method = RequestMethod.GET)
	public String addNotice(Model model) {

		model.addAttribute("notice", new Notice());
		List<String> stringList = getAllFromCasbin(
				userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());
		return "/dashboard/notice/add_notice";
	}

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/saveNotice" }, method = RequestMethod.POST)
	public String saveNotice(Model model, @Valid @ModelAttribute("notice") Notice notice, BindingResult result) {
		System.out.println(notice);
		if (result.hasErrors()) {
		
			model.addAttribute("notice", notice);
			List<String> stringList = getAllFromCasbin(
					userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
			model.addAttribute("menulist", stringList);
			model.addAttribute("fullname", globalMethod.getPrincipalFullName());
			return "/dashboard/notice/add_notice";
		} else {

			notice.setUrl(uploadFile(notice.getPdf()));
			notice.setTrackingNo("" + System.currentTimeMillis());
			notice.setCreateDate(new Date());
			notice.setCreateBy(getPrincipal());
			notice.setActive(true);

			Notice saved = noticeService.save(notice);

			return "redirect:/adminpanel/v2/publicNotice/list";
		}

	}
	
	@Autowired
	private  FileStorageService fileStorageService;

	private String uploadFile(MultipartFile file) {
		// Check if the file is null
		if (file == null) {
			return null;
		}

		// Check if the file isEmpty
		if (StringUtils.isEmpty(file.getOriginalFilename())) {
			return null;
		}

		String fileName = fileStorageService.storeFile(file);

		// Check if the file isEmpty
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}
		String fileDownloadUri = "/uploads/picture/" + fileName;
		return fileDownloadUri;
	}

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/editNotice/{id}" }, method = RequestMethod.GET)
	public String editNotice(@PathVariable Long id, Model model) {
		Notice notice = noticeService.findOne(id);
		model.addAttribute("notice", notice);
		List<String> stringList = getAllFromCasbin(
				userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());
		return "/dashboard/notice/edit_notice"; 
		
	}

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/updateNotice/{id}" }, method = RequestMethod.POST)
	public String updateNotice(@PathVariable Long id, Model model, @Valid @ModelAttribute("notice") Notice notice,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.info("Validation Error");			
			model.addAttribute("notice", notice);
			
			List<String> stringList = getAllFromCasbin(
					userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
			model.addAttribute("menulist", stringList);
			model.addAttribute("fullname", globalMethod.getPrincipalFullName());
			return "/dashboard/notice/edit_notice"; 
		}
		
		Notice saved = noticeService.findOne(id);
		
		saved.setTitle(notice.getTitle());		
		saved.setTitleBn(notice.getTitleBn());		
		saved.setPublishDate(notice.getPublishDate());		
		saved.setModifyDate(new Date());
		saved.setModifyBy(getPrincipal());
		
		if(!StringUtils.isEmpty(notice.getPdf().getOriginalFilename())) {
			saved.setUrl(uploadFile(notice.getPdf()));
		}
		
		
		Notice updated = noticeService.save(saved);
		
		return "redirect:/adminpanel/v2/publicNotice/list";
	}

	@RequestMapping(value = { "/adminpanel/v2/publicNotice/deleteNotice/{id}" }, method = RequestMethod.GET)
	public String deleteNotice(@PathVariable Long id, Model model) {
		Notice notice = noticeService.findOne(id);
		notice.setActive(false);
		noticeService.save(notice);
		return "redirect:/adminpanel/v2/publicNotice/list";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		System.out.println(userName);
		return userName;
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

	public void insertToCasbin(User user) {
		for (Role role : user.getRoles()) {
			enforcer.addGroupingPolicy(user.getUsername(), role.getName());
		}
	}

	public void deleteFromCasbin(User user) {
		try {
			Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

			String url = environment.getRequiredProperty("spring.datasource.url");
			String username = environment.getRequiredProperty("spring.datasource.username");
			String password = environment.getRequiredProperty("spring.datasource.password");

			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = null;
			String deleteSql = "DELETE FROM casbin_rule WHERE v0 = ?";
			statement = connection.prepareStatement(deleteSql);
			statement.setString(1, user.getUsername());
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
