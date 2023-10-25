package com.library.controller;

import com.library.entity.po.LendList;
import com.library.entity.query.LendListQuery;
import com.library.entity.vo.ResponseVO;
import com.library.service.LendListService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 借还信息Controller
 * @author: luceln
 * @date: 2023/10/23
 */
@RestController
@RequestMapping("/lendList")
public class LendListController extends ABaseController {

	@Resource
	private LendListService lendListService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(LendListQuery query) {
		return getSuccessResponseVO(lendListService.findListByPage(query));
	}

	/**
	 * 新增
	 */

	@RequestMapping("add")
	public ResponseVO add(LendList bean) {
		this.lendListService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */

	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<LendList> listBean) {
		this.lendListService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */

	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<LendList> listBean) {
		this.lendListService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据SerNum查询
	 */
	@RequestMapping("getLendListBySerNum")
	public ResponseVO getLendListBySerNum(Long serNum) {
		return getSuccessResponseVO(this.lendListService.getLendListBySerNum(serNum));
	}

	/**
	 * 根据SerNum更新
	 */
	@RequestMapping("updateLendListBySerNum")
	public ResponseVO updateLendListBySerNum(LendList bean, Long serNum) {
		this.lendListService.updateLendListBySerNum(bean, serNum);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据SerNum删除
	 */
	@RequestMapping("deleteLendListBySerNum")
	public ResponseVO deleteLendListBySerNum(Long serNum) {
		this.lendListService.deleteLendListBySerNum(serNum);
		return getSuccessResponseVO(null);
	}
}