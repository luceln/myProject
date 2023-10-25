package com.library.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 书本信息Mapper
 * @author: luceln
 * @date: 2023/10/23
 */
public interface BookInfoMapper<T, P> extends BaseMapper {
	/**
	 * 根据BookId查询
	 */
	T selectByBookId(@Param("bookId") Long bookId);

	/**
	 * 根据BookId更新
	 */
	Integer updateByBookId(@Param("bean") T t, @Param("bookId") Long bookId);

	/**
	 * 根据BookId删除
	 */
	Integer deleteByBookId(@Param("bookId") Long bookId);


}