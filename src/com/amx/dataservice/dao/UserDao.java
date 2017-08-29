package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.UserDo;

public interface UserDao extends JpaRepository<UserDo, Long> {

	UserDo findOneByPhone(String phone);
}