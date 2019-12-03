package com.asl.asl_rms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.asl.asl_rms.model.CitizenCertificate;
import com.asl.asl_rms.model.Complain;
import com.asl.asl_rms.model.ConfiguarationItems;
import com.asl.asl_rms.model.GenericCertificate;
import com.asl.asl_rms.model.Heir;
import com.asl.asl_rms.model.HeirCertificate;
import com.asl.asl_rms.model.Neonate;
import com.asl.asl_rms.model.Notice;
import com.asl.asl_rms.service.CitizenCertificateService;
import com.asl.asl_rms.service.ComplainService;
import com.asl.asl_rms.service.ConfiguarationItemService;
import com.asl.asl_rms.service.FileStorageService;
import com.asl.asl_rms.service.GenericCertificateService;
import com.asl.asl_rms.service.HeirCertificateService;
import com.asl.asl_rms.service.NeonateService;
import com.asl.asl_rms.service.NoticeService;
import com.asl.asl_rms.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ApiController {

	@Autowired
	ConfiguarationItemService confItemService;

	@RequestMapping(value = { "/api/v1/about/aboutMGT" }, method = RequestMethod.POST)
	@ResponseBody
	public String aboutMGT(@RequestBody String json, HttpServletRequest request) {

		Gson gson = new GsonBuilder().create();
		Boolean isJson = CommonUtil.isJSONValid(json);
		String toJson = "";
		if (isJson) {
			Map<String, String> param = gson.fromJson(json, Map.class);
			String lang = param.get("lang");

			String aboutMGT = "";
			ConfiguarationItems item = confItemService.findAnItem("aboutMGT", lang);
			aboutMGT = item.getItemValue();

			Map<String, String> result = new HashMap<String, String>();
			result.put("aboutMGT", aboutMGT);
			result.put("aboutMGT", aboutMGT);
			result.put("success", "true");
			result.put("biography", aboutMGT);
			result.put("name", "Masum Goni Taposh");
			result.put("ward", "21");
			result.put("cityCorporation", "DNCC");
			toJson = gson.toJson(result);

		}

		return toJson;
	}

	@RequestMapping(value = { "/api/v1/about/aboutMGT" }, method = RequestMethod.GET)
	@ResponseBody
	public String getAboutMGT(@RequestParam(name = "lang") String lang) {
		String toJson = "";
		String aboutMGT = "";
		ConfiguarationItems item = confItemService.findAnItem("aboutMGT", lang);
		aboutMGT = item.getItemValue();

		Map<String, String> result = new HashMap<String, String>();
		result.put("aboutMGT", aboutMGT);
		result.put("success", "true");
		result.put("biography", aboutMGT);
		result.put("name", "Masum Goni Taposh");
		result.put("ward", "21");
		result.put("cityCorporation", "DNCC");

		Gson gson = new GsonBuilder().create();
		toJson = gson.toJson(result);
		return toJson;
	}

	@Autowired
	CitizenCertificateService citizenService;

	@RequestMapping(value = { "/api/v1/services/citizen/en/saveApplication" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveCitizenApiEn(CitizenCertificate citizen, Model model, HttpServletRequest request,
			@RequestParam(name = "imgNid1", required = false) MultipartFile imgNid1,
			@RequestParam(name = "imgNid2", required = false) MultipartFile imgNid2,
			@RequestParam(name = "imgPp", required = false) MultipartFile imgPp) {

		citizen.setPpPath(uploadFile(imgPp));
		citizen.setNid1Path(uploadFile(imgNid1));
		citizen.setNid2Path(uploadFile(imgNid2));
		citizen.setTrackingNo("" + System.currentTimeMillis());
		citizen.setLang("en");
		citizen.setStatus(CommonUtil.STATUS_SUBMITTED);
		citizen.setCreateDate(new Date());

		CitizenCertificate saved = citizenService.save(citizen);

		Map<String, String> result = new HashMap<String, String>();
		result.put("trackingNo", saved.getTrackingNo());
		result.put("success", "true");
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	private FileStorageService fileStorageService;

	private String uploadFile(MultipartFile file) {
		// Check if the file is null
		if (file == null) {
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

	@RequestMapping(value = { "/api/v1/services/citizen/bn/saveApplication" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveCitizenApiBn(CitizenCertificate citizen, Model model, HttpServletRequest request,
			@RequestParam(name = "imgNid1", required = false) MultipartFile imgNid1,
			@RequestParam(name = "imgNid2", required = false) MultipartFile imgNid2,
			@RequestParam(name = "imgPp", required = false) MultipartFile imgPp) {

		citizen.setPpPath(uploadFile(imgPp));
		citizen.setNid1Path(uploadFile(imgNid1));
		citizen.setNid2Path(uploadFile(imgNid2));
		citizen.setTrackingNo("" + System.currentTimeMillis());
		citizen.setLang("bn");
		citizen.setStatus(CommonUtil.STATUS_SUBMITTED);
		citizen.setCreateDate(new Date());

		CitizenCertificate saved = citizenService.save(citizen);

		Map<String, String> result = new HashMap<String, String>();
		result.put("trackingNo", saved.getTrackingNo());
		result.put("success", "true");
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	ComplainService complainService;

	@RequestMapping(value = { "/api/v1/complain/saveComplain" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveComplain(Complain complain, Model model, HttpServletRequest request,
			@RequestParam(name = "pic1", required = false) MultipartFile pic1,
			@RequestParam(name = "pic2", required = false) MultipartFile pic2,
			@RequestParam(name = "pic3", required = false) MultipartFile pic3) {

		complain.setPic1_path(uploadFile(pic1));
		complain.setPic2_path(uploadFile(pic2));
		complain.setPic3_path(uploadFile(pic3));
		complain.setTrackingNo("" + System.currentTimeMillis());
		complain.setStatus(CommonUtil.STATUS_SUBMITTED);
		complain.setCreateDate(new Date());

		Complain saved = complainService.save(complain);

		String subject = "Complain tracking No: " + saved.getTrackingNo();
		String message = "Sir, Thanks for submitting the complain. Your Complain tracking No. is "
				+ saved.getTrackingNo();
		sendMail(saved.getEmail(), subject, message);

		Map<String, String> result = new HashMap<String, String>();
		result.put("trackingNo", saved.getTrackingNo());
		result.put("success", "true");
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	HeirCertificateService heirService;

	@RequestMapping(value = { "/api/v1/services/heirCertificate/create" }, method = RequestMethod.POST)
	@ResponseBody
	public String createHeirCertificate(HeirCertificate bean,
			@RequestParam(name = "img1", required = false) MultipartFile img1,
			@RequestParam(name = "img2", required = false) MultipartFile img2,
			@RequestParam(name = "img3", required = false) MultipartFile img3) {

		bean.setImg1Path(uploadFile(img1));
		bean.setImg2Path(uploadFile(img2));
		bean.setImg3Path(uploadFile(img3));
		bean.setTrackingNo("" + System.currentTimeMillis());
		bean.setStatus(CommonUtil.STATUS_SUBMITTED);
		bean.setCreateDate(new Date());

		Map<String, String> result = new HashMap<String, String>();

		if (bean.getHeirName().size() > 0) {
			HeirCertificate saved = heirService.save(bean);
			result.put("trackingNo", saved.getTrackingNo());
			result.put("success", "true");

			for (int i = 0; i < bean.getHeirName().size(); i++) {
				Heir heir = new Heir();

				heir.setName(bean.getHeirName().size() > 0 ? bean.getHeirName().get(i) : null);
				heir.setRelation(bean.getHeirRelation().size() > 0 ? bean.getHeirRelation().get(i) : null);
				heir.setAge(bean.getHeirAge().size() > 0 ? bean.getHeirAge().get(i) : null);
				heir.setRemark(bean.getHeirRemark().size() > 0 ? bean.getHeirRemark().get(i) : null);
				heir.setHeirCertificate(saved);
				heirService.save(heir);
			}

		}

		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	GenericCertificateService genericCertificateService;

	@RequestMapping(value = { "/api/v1/services/othersCertificate/save" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveGenericServiceForm(GenericCertificate genericCertificate, Model model, HttpServletRequest request,
			@RequestParam(name = "img1", required = false) MultipartFile pic1,
			@RequestParam(name = "img2", required = false) MultipartFile pic2) {

		genericCertificate.setPic1_path(uploadFile(pic1));
		genericCertificate.setPic2_path(uploadFile(pic2));
		genericCertificate.setTrackingNo("" + System.currentTimeMillis());
		genericCertificate.setStatus(CommonUtil.STATUS_SUBMITTED);
		genericCertificate.setCreateDate(new Date());

		GenericCertificate saved = genericCertificateService.save(genericCertificate);

		Map<String, String> result = new HashMap<String, String>();
		result.put("trackingNo", saved.getTrackingNo());
		result.put("success", "true");
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	NeonateService neonateService;

	@RequestMapping(value = { "/api/v1/services/neonate/save" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveNeonate(@RequestBody Neonate neonate, Model model, HttpServletRequest request) {
		neonate.setTrackingNo("" + System.currentTimeMillis());
		neonate.setStatus(CommonUtil.STATUS_SUBMITTED);
		neonate.setCreateDate(new Date());
		Neonate saved = neonateService.save(neonate);

		Map<String, String> result = new HashMap<String, String>();
		result.put("trackingNo", saved.getTrackingNo());
		result.put("success", "true");
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;

	}

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = { "/api/v1/about/noticeList" }, method = RequestMethod.POST)
	@ResponseBody
	public String noticeList() {

		List<Notice> noticeList = noticeService.findAll();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("noticeList", noticeList);
		result.put("success", "true");

		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(result);
		return toJson;
	}

	@RequestMapping(value = { "/api/v1/about/noticeList" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Notice> getNoticeList(@RequestParam(name = "limit") int limit) {
		if (limit < 1) {
			limit = 3;
		}
		List<Notice> noticeList = noticeService.findAllByLimit(limit);
		return noticeList;
	}

	private void sendMail(String to, String subject, String message) {

		EmailValidator validator = EmailValidator.getInstance();
		if (validator.isValid(to)) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(to);
			msg.setSubject(subject);
			msg.setText(message);
			javaMailSender.send(msg);
			System.out.println("Email Sent to: " + to);
		} else {
			System.out.println("Could sent mail to invalid email address!");
		}

	}

	@Autowired
	private JavaMailSender javaMailSender;

	private void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("ashid8bd@gmail.com");
		helper.setSubject("Testing from Spring Boot");
		helper.setText("<h1>Check attachment!</h1>", true);

		helper.addAttachment("license.lic", new ClassPathResource("license.lic"));

		javaMailSender.send(msg);

	}

}
