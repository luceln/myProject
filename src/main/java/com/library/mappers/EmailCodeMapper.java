package com.library.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 邮箱验证码Mapper
 * @author: luceln
 * @date: 2023/09/24
 */
public interface EmailCodeMapper<T, P> extends BaseMapper {
	/**
	 * 根据EmailAndCode查询
	 */
	T selectByEmailAndCode(@Param("email") String email, @Param("code") String code);

	/**
	 * 根据EmailAndCode更新
	 */
	Integer updateByEmailAndCode(@Param("bean") T t, @Param("email") String email, @Param("code") String code);

	/**
	 * 根据EmailAndCode删除
	 */
	Integer deleteByEmailAndCode(@Param("email") String email, @Param("code") String code);

	void disableEmailCode(@Param("email") String email);
}