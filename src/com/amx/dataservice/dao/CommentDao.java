package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.CommentDo;

public interface CommentDao extends JpaRepository<CommentDo, Long> {
	
}