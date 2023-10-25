package com.library.service;

import com.library.entity.dto.SessionWebUserDto;
import com.library.entity.vo.PaginationResultVO;
import com.library.entity.po.UserInfo;
import com.library.entity.query.UserInfoQuery;

import java.util.List;
/**
 * @Description: 用户信息Service
 * @author: luceln
 * @date: 2023/10/23
 */
public interface UserInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<UserInfo> findListByParam(UserInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(UserInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(UserInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<UserInfo> listBean);

	/**
	 * 根据UserId查询
	 */
	UserInfo getUserInfoByUserId(Integer userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateUserInfoByUserId(UserInfo bean, Integer userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoByUserId(Integer userId);

	/**
	 * 根据Email查询
	 */
	UserInfo getUserInfoByEmail(String email);

	/**
	 * 根据Email更新
	 */
	Integer updateUserInfoByEmail(UserInfo bean, String email);

	/**
	 * 根据Email删除
	 */
	Integer deleteUserInfoByEmail(String email);

	/**
	 * 登录
	 */
	SessionWebUserDto login(String email, String password);

	/**
	 * 注册
	 */
	void register(String email, String nickName, String password, String emailCode);
}