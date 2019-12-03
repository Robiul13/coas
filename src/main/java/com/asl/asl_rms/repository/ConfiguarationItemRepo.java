package com.asl.asl_rms.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.asl_rms.model.ConfiguarationItems;

@Repository
public interface ConfiguarationItemRepo extends CrudRepository<ConfiguarationItems, Long> {
	
	 @Query(value = "\n" +
	            "SELECT * FROM CONFIGURATION_ITEMS r WHERE r.ITEM_NAME=?1 AND  r.LANG=?2", nativeQuery = true)
	 ConfiguarationItems findAnItem(String itemName, String lang);
	 

}
