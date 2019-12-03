package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.Complain;
import com.asl.asl_rms.repository.ComplainRepo;

@Service
public class ComplainService {

    @Autowired
    ComplainRepo complainRepo;

    public List<Complain> getAll() {
        return (List<Complain>) complainRepo.findAll();
    }

  
    
    public Complain save(Complain complain){
        Complain saved = complainRepo.save(complain);
		return saved;
	}
    
    public Complain findOne(Long id){
        return complainRepo.findById(id).get();
    }
    
    
    public List<Complain> findAllByStatus(String status) {
        return (List<Complain>) complainRepo.findAllByStatus(status);
    }
   
}
