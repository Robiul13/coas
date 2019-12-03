package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.HeirCertificate;

@Repository
public interface HeirCertificateRepo extends CrudRepository<HeirCertificate, Long> {

	@Query(value = "\n" + "SELECT * FROM HEIR_CERTIFICATE r WHERE r.TRACKING_NO=?1 AND  r.LANG=?2", nativeQuery = true)
	HeirCertificate findByTrackingNo(String trackingNo, String lang);
	
	@Query(value = "\n" + "SELECT * FROM HEIR_CERTIFICATE r order by TRACKING_NO desc", nativeQuery = true)
	List<HeirCertificate> findAll();
	
	@Query(value = "\n" + "SELECT * FROM HEIR_CERTIFICATE r where r.status=?1  order by TRACKING_NO desc", nativeQuery = true)
	List<HeirCertificate> findAllByStatus(String status);

}
