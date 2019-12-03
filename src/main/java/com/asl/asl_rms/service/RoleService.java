package com.asl.asl_rms.service;

import com.asl.asl_rms.model.Role;
import com.asl.asl_rms.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role getOne(Long id){ return roleRepo.findById(id).get();}
    public List<Role> getAll(){ return (List<Role>) roleRepo.findAll();}
    public void saveRole(Role role){ roleRepo.save(role);}
}
