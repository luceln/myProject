package com.library.service.impl;

import com.library.entity.po.ClassInfo;
import com.library.entity.query.ClassInfoQuery;
import com.library.entity.query.SimplePage;
import com.library.entity.vo.PaginationResultVO;
import com.library.enums.PageSize;
import com.library.mappers.ClassInfoMapper;
import com.library.service.ClassInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 书籍分类表ServiceImpl
 * @author: luceln
 * @date: 2023/10/23
 */
@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {

	@Resource
	private ClassInfoMapper<ClassInfo, ClassInfoQuery> classInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<ClassInfo> findListByParam(ClassInfoQuery query) {
		return this.classInfoMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(ClassInfoQuery query) {
		return this.classInfoMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<ClassInfo> findListByPage(ClassInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ClassInfo> list = this.findListByParam(query);
		PaginationResultVO<ClassInfo> result = new PaginationResultVO<>(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(ClassInfo bean) {
		return this.classInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<ClassInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.classInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<ClassInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.classInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据ClassId查询
	 */
	public ClassInfo getClassInfoByClassId(Integer classId) {
		return this.classInfoMapper.selectByClassId(classId);
	}

	/**
	 * 根据ClassId更新
	 */
	public Integer updateClassInfoByClassId(ClassInfo bean, Integer classId) {
		return this.classInfoMapper.updateByClassId(bean, classId);
	}

	/**
	 * 根据ClassId删除
	 */
	public Integer deleteClassInfoByClassId(Integer classId) {
		return this.classInfoMapper.deleteByClassId(classId);
	}
}