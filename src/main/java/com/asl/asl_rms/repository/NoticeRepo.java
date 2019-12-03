package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.Notice;

@Repository
public interface NoticeRepo extends CrudRepository<Notice, Long> {

	@Query(value = "\n" + "SELECT * FROM NOTICE r where active='1' order by TRACKING_NO desc", nativeQuery = true)
	List<Notice> findAll();
	
	@Query(value = "\n" + "SELECT * FROM NOTICE r where active='1' order by TRACKING_NO desc LIMIT ?1", nativeQuery = true)
	public List<Notice> findAllByLimit(int limit);

}
