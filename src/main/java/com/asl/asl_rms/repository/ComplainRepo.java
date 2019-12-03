package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.Complain;

@Repository
public interface ComplainRepo extends CrudRepository<Complain, Long> {

	@Query(value = "\n" + "SELECT * FROM COMPLAIN r order by TRACKING_NO desc", nativeQuery = true)
	List<Complain> findAll();
	
	@Query(value = "\n" + "SELECT * FROM COMPLAIN r where r.status=?1  order by TRACKING_NO desc", nativeQuery = true)
	List<Complain> findAllByStatus(String status);

}
