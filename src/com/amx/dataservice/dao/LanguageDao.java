package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.LanguageDo;

public interface LanguageDao extends JpaRepository<LanguageDo, Integer> {
}