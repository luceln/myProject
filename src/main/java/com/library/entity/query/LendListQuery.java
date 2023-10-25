package com.library.entity.query;

import java.util.Date;


/**
 * @Description: 借还信息查询对象
 * @author: luceln
 * @date: 2023/10/23
 */
public class LendListQuery extends BaseQuery {
	/**
	 * 借还记录ID
	 */
	private Long serNum;

	/**
	 * 书主键
	 */
	private Long bookId;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 借书日期
	 */
	private Date lendDate;

	private String lendDateStart;

	private String lendDateEnd;

	/**
	 * 还书日期
	 */
	private Date backDate;

	private String backDateStart;

	private String backDateEnd;

	public void setSerNum(Long serNum) {
		this.serNum = serNum;
	}

	public Long getSerNum() {
		return serNum;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setLendDateStart(String lendDateStart) {
		this.lendDateStart = lendDateStart;
	}

	public String getLendDateStart() {
		return lendDateStart;
	}

	public void setLendDateEnd(String lendDateEnd) {
		this.lendDateEnd = lendDateEnd;
	}

	public String getLendDateEnd() {
		return lendDateEnd;
	}

	public void setBackDateStart(String backDateStart) {
		this.backDateStart = backDateStart;
	}

	public String getBackDateStart() {
		return backDateStart;
	}

	public void setBackDateEnd(String backDateEnd) {
		this.backDateEnd = backDateEnd;
	}

	public String getBackDateEnd() {
		return backDateEnd;
	}

}