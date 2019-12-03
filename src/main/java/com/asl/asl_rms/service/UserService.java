package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.User;
import com.asl.asl_rms.repository.UserRepo;

@Service

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =	userRepo.findByUsername(username);
		UserDetails userd =(UserDetails)user;
		return userd;
	}

	public List<User> getAllUser(){
		return (List<User>) userRepo.findAll();
	}
	public User usernameExist(String email){ return userRepo.findByEmail(email); }
	public void saveUser(User user){
		String encoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoded);
		User saved = userRepo.save(user);
		
	}
	public User findOne(Long id){ return userRepo.findById(id).get(); }
	public List<User> findActiveUser(boolean active) { return userRepo.findByActive(active);}
	public User findByUserName(String username) {return userRepo.findByUsername(username);}

}
