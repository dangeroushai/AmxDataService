package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.ContactDo;

public interface ContactDao extends JpaRepository<ContactDo, Long> {
}