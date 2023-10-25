package com.library.controller;

import com.library.entity.po.BookInfo;
import com.library.entity.query.BookInfoQuery;
import com.library.entity.vo.ResponseVO;
import com.library.service.BookInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 书本信息Controller
 * @author: luceln
 * @date: 2023/10/23
 */
@RestController
@RequestMapping("/bookInfo")
public class BookInfoController extends ABaseController {

	@Resource
	private BookInfoService bookInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(BookInfoQuery query) {
		return getSuccessResponseVO(bookInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */

	@RequestMapping("add")
	public ResponseVO add(BookInfo bean) {
		this.bookInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */

	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<BookInfo> listBean) {
		this.bookInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */

	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<BookInfo> listBean) {
		this.bookInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据BookId查询
	 */
	@RequestMapping("getBookInfoByBookId")
	public ResponseVO getBookInfoByBookId(Long bookId) {
		return getSuccessResponseVO(this.bookInfoService.getBookInfoByBookId(bookId));
	}

	/**
	 * 根据BookId更新
	 */
	@RequestMapping("updateBookInfoByBookId")
	public ResponseVO updateBookInfoByBookId(BookInfo bean, Long bookId) {
		this.bookInfoService.updateBookInfoByBookId(bean, bookId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据BookId删除
	 */
	@RequestMapping("deleteBookInfoByBookId")
	public ResponseVO deleteBookInfoByBookId(Long bookId) {
		this.bookInfoService.deleteBookInfoByBookId(bookId);
		return getSuccessResponseVO(null);
	}
}