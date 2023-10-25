package com.library.entity.query;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Description: 书本信息查询对象
 * @author: luceln
 * @date: 2023/10/23
 */
public class BookInfoQuery extends BaseQuery {
	/**
	 * 主键
	 */
	private Long bookId;

	/**
	 * 书名
	 */
	private String name;

	private String nameFuzzy;

	/**
	 * 作者
	 */
	private String author;

	private String authorFuzzy;

	/**
	 * 出版社
	 */
	private String publish;

	private String publishFuzzy;

	/**
	 * ISBN
	 */
	private String ISBN;

	private String ISBNFuzzy;

	/**
	 * 介绍
	 */
	private String introduction;

	private String introductionFuzzy;

	/**
	 * 语言
	 */
	private String language;

	private String languageFuzzy;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 出版时间
	 */
	private Date pubDate;

	private String pubDateStart;

	private String pubDateEnd;

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

	public void setNameFuzzy(String nameFuzzy) {
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy() {
		return nameFuzzy;
	}

	public void setAuthorFuzzy(String authorFuzzy) {
		this.authorFuzzy = authorFuzzy;
	}

	public String getAuthorFuzzy() {
		return authorFuzzy;
	}

	public void setPublishFuzzy(String publishFuzzy) {
		this.publishFuzzy = publishFuzzy;
	}

	public String getPublishFuzzy() {
		return publishFuzzy;
	}

	public void setISBNFuzzy(String ISBNFuzzy) {
		this.ISBNFuzzy = ISBNFuzzy;
	}

	public String getISBNFuzzy() {
		return ISBNFuzzy;
	}

	public void setIntroductionFuzzy(String introductionFuzzy) {
		this.introductionFuzzy = introductionFuzzy;
	}

	public String getIntroductionFuzzy() {
		return introductionFuzzy;
	}

	public void setLanguageFuzzy(String languageFuzzy) {
		this.languageFuzzy = languageFuzzy;
	}

	public String getLanguageFuzzy() {
		return languageFuzzy;
	}

	public void setPubDateStart(String pubDateStart) {
		this.pubDateStart = pubDateStart;
	}

	public String getPubDateStart() {
		return pubDateStart;
	}

	public void setPubDateEnd(String pubDateEnd) {
		this.pubDateEnd = pubDateEnd;
	}

	public String getPubDateEnd() {
		return pubDateEnd;
	}

}