package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.CitizenCertificate;

@Repository
public interface CitizenCertificateRepo extends CrudRepository<CitizenCertificate, Long> {

	@Query(value = "\n" + "SELECT * FROM CITIZEN_CERTIFICATE r WHERE r.TRACKING_NO=?1 AND  r.LANG=?2", nativeQuery = true)
	CitizenCertificate findByTrackingNo(String trackingNo, String lang);
	
	@Query(value = "\n" + "SELECT * FROM CITIZEN_CERTIFICATE r order by TRACKING_NO desc", nativeQuery = true)
	List<CitizenCertificate> findAll();
	
	@Query(value = "\n" + "SELECT * FROM CITIZEN_CERTIFICATE r where r.status=?1  order by TRACKING_NO desc", nativeQuery = true)
	List<CitizenCertificate> findAllByStatus(String status);

}
