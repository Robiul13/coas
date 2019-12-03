package com.asl.asl_rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.asl_rms.model.Notice;
import com.asl.asl_rms.repository.NoticeRepo;

@Service

public class NoticeService {

	@Autowired
	private NoticeRepo noticeRepo;

	public List<Notice> findAll() {
		return (List<Notice>) noticeRepo.findAll();
	}
	
	public List<Notice> findAllByLimit(int limit) {
		return (List<Notice>) noticeRepo.findAllByLimit(limit);
	}

	public Notice findOne(Long id) {
		return noticeRepo.findById(id).get();
	}

	public Notice save(Notice notice) {
		Notice saved = noticeRepo.save(notice);
		return saved;
	}

}
