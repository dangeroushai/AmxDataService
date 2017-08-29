package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.ArticleDo;

public interface ArticleDao extends JpaRepository<ArticleDo, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM article WHERE FIND_IN_SET( :attrId ,attr_ids) AND is_enable = 1 AND is_delete = 0 ORDER BY weight,id DESC LIMIT :startIndex , :pageSize")
	List<ArticleDo> findAll(@Param("attrId")int attrId,@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM article WHERE FIND_IN_SET( :attrId ,attr_ids) AND is_enable = 1 AND is_delete = 0")
	long count(@Param("attrId")int attrId);
}