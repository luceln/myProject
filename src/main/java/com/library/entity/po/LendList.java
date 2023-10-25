package com.library.entity.po;

import com.library.enums.DateTimePatternEnum;
import com.library.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description: 借还信息
 * @author: luceln
 * @date: 2023/10/23
 */
public class LendList implements Serializable {
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lendDate;

	/**
	 * 还书日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date backDate;

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

	@Override
	public String toString() {
		return "借还记录ID:" + (serNum == null ? "空" : serNum) + ", 书主键:" + (bookId == null ? "空" : bookId) + ", 用户ID:" + (userId == null ? "空" : userId) + ", 借书日期:" + (lendDate == null ? "空" : DateUtils.format(lendDate, DateTimePatternEnum.YYYY_MM_DD.getPattern())) + ", 还书日期:" + (backDate == null ? "空" : DateUtils.format(backDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
	}
}