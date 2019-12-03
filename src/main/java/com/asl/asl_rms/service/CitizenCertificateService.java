package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.CitizenCertificate;
import com.asl.asl_rms.repository.CitizenCertificateRepo;

@Service
public class CitizenCertificateService {

    @Autowired
    CitizenCertificateRepo citizenRepo;

    public List<CitizenCertificate> getAll() {
        return (List<CitizenCertificate>) citizenRepo.findAll();
    }

    public CitizenCertificate findByTrackingNo(String trackingNo, String lang){
        return citizenRepo.findByTrackingNo(trackingNo, lang);
    }
    
    public CitizenCertificate save(CitizenCertificate citizen){
		CitizenCertificate saved = citizenRepo.save(citizen);		
		return saved;
	}
    
    public CitizenCertificate findOne(Long id){
        return citizenRepo.findById(id).get();
    }
    
    public List<CitizenCertificate> findAllByStatus(String status) {
        return (List<CitizenCertificate>) citizenRepo.findAllByStatus(status);
    }

   
}
