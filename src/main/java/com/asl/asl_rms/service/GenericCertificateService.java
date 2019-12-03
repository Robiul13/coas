package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.GenericCertificate;
import com.asl.asl_rms.repository.GenericCertificateRepo;

@Service
public class GenericCertificateService {

	@Autowired
	GenericCertificateRepo genericCertificateRepo;

	public GenericCertificate save(GenericCertificate genericCertificate) {
		GenericCertificate saved = genericCertificateRepo.save(genericCertificate);
		return saved;
	}

	public List<GenericCertificate> findAll() {
		return (List<GenericCertificate>) genericCertificateRepo.findAll();
	}

	public GenericCertificate findOne(Long id) {
		return genericCertificateRepo.findById(id).get();
	}

	public List<GenericCertificate> findAllByStatus(String status) {
		return (List<GenericCertificate>) genericCertificateRepo.findAllByStatus(status);
	}

}
