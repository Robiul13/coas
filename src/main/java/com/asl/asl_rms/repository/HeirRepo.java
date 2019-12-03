package com.asl.asl_rms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.Heir;

@Repository
public interface HeirRepo extends CrudRepository<Heir, Long> {

}
