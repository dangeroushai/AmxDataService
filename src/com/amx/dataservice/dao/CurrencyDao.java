package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.CurrencyDo;

public interface CurrencyDao extends JpaRepository<CurrencyDo, Integer> {
}