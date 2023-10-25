package com.library.entity.query;



/**
 * @Description: 书籍分类表查询对象
 * @author: luceln
 * @date: 2023/10/23
 */
public class ClassInfoQuery extends BaseQuery {
	/**
	 * 分类主键
	 */
	private Integer classId;

	/**
	 * 分类名
	 */
	private String className;

	private String classNameFuzzy;

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassNameFuzzy(String classNameFuzzy) {
		this.classNameFuzzy = classNameFuzzy;
	}

	public String getClassNameFuzzy() {
		return classNameFuzzy;
	}

}