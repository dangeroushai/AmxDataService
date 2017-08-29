package com.amx.dataservice.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.CustomHodometerDo;

public interface CustomHodometerDao extends JpaRepository<CustomHodometerDo, Long> {
	
	/**
	 * 该方法最终执行步骤：
	 * 1.select * ... where id in (?)(一次查询)
	 * 2.delete ......where id = ?(分别删除)
	 * @param ids
	 * @return
	 */
	int deleteByIdIn(Collection<Long> ids);
	
	int deleteByTravelId(long travelId);
	
}