package com.library.service;

import com.library.entity.vo.PaginationResultVO;
import com.library.entity.po.LendList;
import com.library.entity.query.LendListQuery;

import java.util.List;
/**
 * @Description: 借还信息Service
 * @author: luceln
 * @date: 2023/10/23
 */
public interface LendListService {

	/**
	 * 根据条件查询列表
	 */
	List<LendList> findListByParam(LendListQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(LendListQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<LendList> findListByPage(LendListQuery query);

	/**
	 * 新增
	 */
	Integer add(LendList bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<LendList> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<LendList> listBean);

	/**
	 * 根据SerNum查询
	 */
	LendList getLendListBySerNum(Long serNum);

	/**
	 * 根据SerNum更新
	 */
	Integer updateLendListBySerNum(LendList bean, Long serNum);

	/**
	 * 根据SerNum删除
	 */
	Integer deleteLendListBySerNum(Long serNum);

}