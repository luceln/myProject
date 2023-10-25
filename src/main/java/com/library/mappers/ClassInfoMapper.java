package com.library.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 书籍分类表Mapper
 * @author: luceln
 * @date: 2023/10/23
 */
public interface ClassInfoMapper<T, P> extends BaseMapper {
	/**
	 * 根据ClassId查询
	 */
	T selectByClassId(@Param("classId") Integer classId);

	/**
	 * 根据ClassId更新
	 */
	Integer updateByClassId(@Param("bean") T t, @Param("classId") Integer classId);

	/**
	 * 根据ClassId删除
	 */
	Integer deleteByClassId(@Param("classId") Integer classId);


}