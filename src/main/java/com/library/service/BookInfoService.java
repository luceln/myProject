package com.library.service;

import com.library.entity.vo.PaginationResultVO;
import com.library.entity.po.BookInfo;
import com.library.entity.query.BookInfoQuery;

import java.util.List;
/**
 * @Description: 书本信息Service
 * @author: luceln
 * @date: 2023/10/23
 */
public interface BookInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<BookInfo> findListByParam(BookInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(BookInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<BookInfo> findListByPage(BookInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(BookInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<BookInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<BookInfo> listBean);

	/**
	 * 根据BookId查询
	 */
	BookInfo getBookInfoByBookId(Long bookId);

	/**
	 * 根据BookId更新
	 */
	Integer updateBookInfoByBookId(BookInfo bean, Long bookId);

	/**
	 * 根据BookId删除
	 */
	Integer deleteBookInfoByBookId(Long bookId);

}