package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.ReceiverAddressDo;

public interface ReceiverAddressDao extends JpaRepository<ReceiverAddressDo, Long> {

}