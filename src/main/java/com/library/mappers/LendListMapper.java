package com.library.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 借还信息Mapper
 * @author: luceln
 * @date: 2023/10/23
 */
public interface LendListMapper<T, P> extends BaseMapper {
	/**
	 * 根据SerNum查询
	 */
	T selectBySerNum(@Param("serNum") Long serNum);

	/**
	 * 根据SerNum更新
	 */
	Integer updateBySerNum(@Param("bean") T t, @Param("serNum") Long serNum);

	/**
	 * 根据SerNum删除
	 */
	Integer deleteBySerNum(@Param("serNum") Long serNum);


}