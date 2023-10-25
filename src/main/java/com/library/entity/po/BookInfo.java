package com.library.entity.po;

import com.library.enums.DateTimePatternEnum;
import com.library.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @Description: 书本信息
 * @author: luceln
 * @date: 2023/10/23
 */
public class BookInfo implements Serializable {
	/**
	 * 主键
	 */
	private Long bookId;

	/**
	 * 书名
	 */
	private String name;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 出版社
	 */
	private String publish;

	/**
	 * ISBN
	 */
	private String ISBN;

	/**
	 * 介绍
	 */
	private String introduction;

	/**
	 * 语言
	 */
	private String language;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 出版时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pubDate;

	/**
	 * 分类
	 */
	private Integer classId;

	/**
	 * 数量
	 */
	private Integer number;

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getPublish() {
		return publish;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "主键:" + (bookId == null ? "空" : bookId) + ", 书名:" + (name == null ? "空" : name) + ", 作者:" + (author == null ? "空" : author) + ", 出版社:" + (publish == null ? "空" : publish) + ", ISBN:" + (ISBN == null ? "空" : ISBN) + ", 介绍:" + (introduction == null ? "空" : introduction) + ", 语言:" + (language == null ? "空" : language) + ", 价格:" + (price == null ? "空" : price) + ", 出版时间:" + (pubDate == null ? "空" : DateUtils.format(pubDate, DateTimePatternEnum.YYYY_MM_DD.getPattern())) + ", 分类:" + (classId == null ? "空" : classId) + ", 数量:" + (number == null ? "空" : number);
	}
}