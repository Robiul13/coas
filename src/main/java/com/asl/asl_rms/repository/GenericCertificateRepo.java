package com.asl.asl_rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.GenericCertificate;

@Repository
public interface GenericCertificateRepo extends CrudRepository<GenericCertificate, Long> {
	@Query(value = "\n" + "SELECT * FROM GENERIC_CERTIFICATE r order by TRACKING_NO desc", nativeQuery = true)
	List<GenericCertificate> findAll();

	@Query(value = "\n"
			+ "SELECT * FROM GENERIC_CERTIFICATE r where r.status=?1  order by TRACKING_NO desc", nativeQuery = true)
	List<GenericCertificate> findAllByStatus(String status);

}
