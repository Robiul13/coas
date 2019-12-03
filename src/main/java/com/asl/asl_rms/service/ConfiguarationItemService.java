package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.ConfiguarationItems;
import com.asl.asl_rms.repository.ConfiguarationItemRepo;

@Service
public class ConfiguarationItemService {

    @Autowired
    ConfiguarationItemRepo confItemRepo;

    public List<ConfiguarationItems> getAll() {
        return (List<ConfiguarationItems>) confItemRepo.findAll();
    }

    public ConfiguarationItems findAnItem(String itemName, String lang){
        return confItemRepo.findAnItem(itemName, lang);
    }
    
  //  String findAnItem(String itemName, String lang);

   
}
