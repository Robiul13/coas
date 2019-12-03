package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.Heir;
import com.asl.asl_rms.model.HeirCertificate;
import com.asl.asl_rms.repository.HeirCertificateRepo;
import com.asl.asl_rms.repository.HeirRepo;

@Service
public class HeirCertificateService {

	@Autowired
	HeirCertificateRepo heirCertificateRepo;

	@Autowired
	HeirRepo heirRepo;

	public List<HeirCertificate> getAllHeirCertificate() {
		return (List<HeirCertificate>) heirCertificateRepo.findAll();
	}

	public List<Heir> getAllHeir() {
		return (List<Heir>) heirRepo.findAll();
	}

	public HeirCertificate findHeirCertificate(Long id) {
		return heirCertificateRepo.findById(id).get();
	}

	public Heir findHeir(Long id) {
		return heirRepo.findById(id).get();
	}

	public HeirCertificate findByTrackingNo(String trackingNo, String lang) {
		return heirCertificateRepo.findByTrackingNo(trackingNo, lang);
	}

	public HeirCertificate save(HeirCertificate heirCertificate) {
		HeirCertificate saved = heirCertificateRepo.save(heirCertificate);
		return saved;
	}

	public Heir save(Heir heir) {
		Heir saved = heirRepo.save(heir);
		return saved;
	}

	public List<HeirCertificate> findAllByStatus(String status) {
		return (List<HeirCertificate>) heirCertificateRepo.findAllByStatus(status);
	}

}
