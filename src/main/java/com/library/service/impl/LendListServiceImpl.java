package com.library.service.impl;

import com.library.entity.po.LendList;
import com.library.entity.query.LendListQuery;
import com.library.entity.query.SimplePage;
import com.library.entity.vo.PaginationResultVO;
import com.library.enums.PageSize;
import com.library.mappers.LendListMapper;
import com.library.service.LendListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 借还信息ServiceImpl
 * @author: luceln
 * @date: 2023/10/23
 */
@Service("lendListService")
public class LendListServiceImpl implements LendListService {

	@Resource
	private LendListMapper<LendList, LendListQuery> lendListMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<LendList> findListByParam(LendListQuery query) {
		return this.lendListMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(LendListQuery query) {
		return this.lendListMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<LendList> findListByPage(LendListQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<LendList> list = this.findListByParam(query);
		PaginationResultVO<LendList> result = new PaginationResultVO<>(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(LendList bean) {
		return this.lendListMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<LendList> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.lendListMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<LendList> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.lendListMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据SerNum查询
	 */
	public LendList getLendListBySerNum(Long serNum) {
		return this.lendListMapper.selectBySerNum(serNum);
	}

	/**
	 * 根据SerNum更新
	 */
	public Integer updateLendListBySerNum(LendList bean, Long serNum) {
		return this.lendListMapper.updateBySerNum(bean, serNum);
	}

	/**
	 * 根据SerNum删除
	 */
	public Integer deleteLendListBySerNum(Long serNum) {
		return this.lendListMapper.deleteBySerNum(serNum);
	}
}