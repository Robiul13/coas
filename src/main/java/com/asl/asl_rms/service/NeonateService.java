package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.Neonate;
import com.asl.asl_rms.repository.NeonateRepo;

@Service
public class NeonateService {

	@Autowired
	NeonateRepo neonateRepo;

	public List<Neonate> list() {
		return (List<Neonate>) neonateRepo.findAll();
	}

	public Neonate findOne(Long id) {
		return neonateRepo.findById(id).get();
	}

	public Neonate findByTrackingNo(String trackingNo, String lang) {
		return neonateRepo.findByTrackingNo(trackingNo, lang);
	}

	public Neonate save(Neonate neonate) {
		Neonate saved = neonateRepo.save(neonate);
		return saved;
	}

}
