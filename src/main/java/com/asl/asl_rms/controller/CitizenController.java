package com.asl.asl_rms.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asl.asl_rms.model.CitizenCertificate;
import com.asl.asl_rms.model.Complain;
import com.asl.asl_rms.model.ConfiguarationItems;
import com.asl.asl_rms.service.CitizenCertificateService;
import com.asl.asl_rms.service.ConfiguarationItemService;
import com.asl.asl_rms.service.UserService;
import com.asl.asl_rms.util.GlobalMethod;

@Controller
public class CitizenController {
	
	@Autowired
	UserService userService;
	@Autowired
	private Environment environment;
	@Autowired
	Enforcer enforcer;
	@Autowired
	GlobalMethod globalMethod;

	
	
	@Autowired
	CitizenCertificateService ccService;

	@RequestMapping(value = { "/adminpanel/v2/citizenCertificate/list" }, method = RequestMethod.GET)
	public String getCitizenCertificatelist(Model model) {
		List<CitizenCertificate> complainlist = ccService.getAll();
		for (CitizenCertificate complain : complainlist) {
			String code = complain.getStatus();
			complain.setStatus(getStatusName(code));
		}
		List<String> stringList = getAllFromCasbin(
				userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());
		model.addAttribute("complainlist", complainlist);
		return "dashboard/citizen/citizen_list";
	}

	private String getStatusName(String code) {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("01", "Submitted");
		statusMap.put("02", "In Process");
		statusMap.put("03", "Rejected");
		statusMap.put("04", "Completed");

		return statusMap.get(code);
	}
	
	@Autowired
	ConfiguarationItemService confItemService;

	@RequestMapping(value = { "/adminpanel/v2/citizenCertificate/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		
		CitizenCertificate cc=ccService.findOne(id);
		model.addAttribute("clients", cc);
		List<String> stringList = getAllFromCasbin(
				userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
		model.addAttribute("menulist", stringList);
		model.addAttribute("fullname", globalMethod.getPrincipalFullName());

		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("01", "Submitted");
		statusMap.put("02", "In Process");
		statusMap.put("03", "Rejected");
		statusMap.put("04", "Completed");
		model.addAttribute("statusMap", statusMap);
		String birtAddress = "";
		ConfiguarationItems item = confItemService.findAnItem("birtAddress", "en");
		birtAddress = item.getItemValue();			
		
		if("bn".equals(cc.getLang())){
			birtAddress=birtAddress+"?__report=prepay/pos/dncc_w21_national_certificate_ban.rptdesign&p_id="+cc.getId();
		}		
		if("en".equals(cc.getLang())){
			birtAddress=birtAddress+"?__report=prepay/pos/dncc_w21_national_certificate_eng.rptdesign&p_id="+cc.getId();
		}			
		model.addAttribute("birtAddress", birtAddress);

		return "dashboard/citizen/edit_citizen";
	}

	@RequestMapping(value = { "/adminpanel/v2/citizenCertificate/update" }, method = RequestMethod.POST)
	public String updateCitizenCertificate(Complain bean) {

		Map<String, String> result = new HashMap<String, String>();

		long id = bean.getId();
		String status = bean.getStatus();
		String remark = bean.getRemark();

		CitizenCertificate saved = ccService.findOne(id);
		if (saved.getStatus().equals(status)) {
			result.put("success", "false");
			result.put("message", "Status can not be same. Please change!");
		} else {
			saved.setStatus(status);
			saved.setRemark(remark);
			saved.setModifyDate(new Date());
			saved.setModifyBy(getPrincipal());

			CitizenCertificate updaeted = ccService.save(saved);

			result.put("success", "true");
			result.put("message", "Citizen Certificate updated successfully");

		}

		return "redirect:/adminpanel/v2/citizenCertificate/list";

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
}
