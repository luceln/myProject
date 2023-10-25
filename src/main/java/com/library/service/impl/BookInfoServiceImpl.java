package com.library.service.impl;

import com.library.entity.po.BookInfo;
import com.library.entity.query.BookInfoQuery;
import com.library.entity.query.SimplePage;
import com.library.entity.vo.PaginationResultVO;
import com.library.enums.PageSize;
import com.library.mappers.BookInfoMapper;
import com.library.service.BookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 书本信息ServiceImpl
 * @author: luceln
 * @date: 2023/10/23
 */
@Service("bookInfoService")
public class BookInfoServiceImpl implements BookInfoService {

	@Resource
	private BookInfoMapper<BookInfo, BookInfoQuery> bookInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<BookInfo> findListByParam(BookInfoQuery query) {
		return this.bookInfoMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(BookInfoQuery query) {
		return this.bookInfoMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<BookInfo> findListByPage(BookInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<BookInfo> list = this.findListByParam(query);
		PaginationResultVO<BookInfo> result = new PaginationResultVO<>(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(BookInfo bean) {
		return this.bookInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<BookInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.bookInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<BookInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.bookInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据BookId查询
	 */
	public BookInfo getBookInfoByBookId(Long bookId) {
		return this.bookInfoMapper.selectByBookId(bookId);
	}

	/**
	 * 根据BookId更新
	 */
	public Integer updateBookInfoByBookId(BookInfo bean, Long bookId) {
		return this.bookInfoMapper.updateByBookId(bean, bookId);
	}

	/**
	 * 根据BookId删除
	 */
	public Integer deleteBookInfoByBookId(Long bookId) {
		return this.bookInfoMapper.deleteByBookId(bookId);
	}
}