package com.library.controller;

import com.library.entity.po.ClassInfo;
import com.library.entity.query.ClassInfoQuery;
import com.library.entity.vo.ResponseVO;
import com.library.service.ClassInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 书籍分类表Controller
 * @author: luceln
 * @date: 2023/10/23
 */
@RestController
@RequestMapping("/classInfo")
public class ClassInfoController extends ABaseController {

	@Resource
	private ClassInfoService classInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ClassInfoQuery query) {
		return getSuccessResponseVO(classInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */

	@RequestMapping("add")
	public ResponseVO add(ClassInfo bean) {
		this.classInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */

	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ClassInfo> listBean) {
		this.classInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */

	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ClassInfo> listBean) {
		this.classInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据ClassId查询
	 */
	@RequestMapping("getClassInfoByClassId")
	public ResponseVO getClassInfoByClassId(Integer classId) {
		return getSuccessResponseVO(this.classInfoService.getClassInfoByClassId(classId));
	}

	/**
	 * 根据ClassId更新
	 */
	@RequestMapping("updateClassInfoByClassId")
	public ResponseVO updateClassInfoByClassId(ClassInfo bean, Integer classId) {
		this.classInfoService.updateClassInfoByClassId(bean, classId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据ClassId删除
	 */
	@RequestMapping("deleteClassInfoByClassId")
	public ResponseVO deleteClassInfoByClassId(Integer classId) {
		this.classInfoService.deleteClassInfoByClassId(classId);
		return getSuccessResponseVO(null);
	}
}