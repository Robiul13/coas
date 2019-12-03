package com.asl.asl_rms.repository;

import com.asl.asl_rms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	public User findByUsername(String username);
	public User findByEmail(String email);
	public List<User> findByActive(boolean active);
}
