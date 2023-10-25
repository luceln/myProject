package com.library.service;

import com.library.entity.vo.PaginationResultVO;
import com.library.entity.po.ClassInfo;
import com.library.entity.query.ClassInfoQuery;

import java.util.List;
/**
 * @Description: 书籍分类表Service
 * @author: luceln
 * @date: 2023/10/23
 */
public interface ClassInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<ClassInfo> findListByParam(ClassInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(ClassInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ClassInfo> findListByPage(ClassInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(ClassInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ClassInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<ClassInfo> listBean);

	/**
	 * 根据ClassId查询
	 */
	ClassInfo getClassInfoByClassId(Integer classId);

	/**
	 * 根据ClassId更新
	 */
	Integer updateClassInfoByClassId(ClassInfo bean, Integer classId);

	/**
	 * 根据ClassId删除
	 */
	Integer deleteClassInfoByClassId(Integer classId);

}