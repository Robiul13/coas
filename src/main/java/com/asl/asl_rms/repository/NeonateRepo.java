package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.Neonate;

@Repository
public interface NeonateRepo extends CrudRepository<Neonate, Long> {

	@Query(value = "\n" + "SELECT * FROM NEONATE r WHERE r.TRACKING_NO=?1 AND  r.LANG=?2", nativeQuery = true)
	Neonate findByTrackingNo(String trackingNo, String lang);
	
	@Query(value = "\n" + "SELECT * FROM NEONATE r order by TRACKING_NO desc", nativeQuery = true)
	List<Neonate> findAll();

}
