package com.library.entity.po;


import java.io.Serializable;


/**
 * @Description: 书籍分类表
 * @author: luceln
 * @date: 2023/10/23
 */
public class ClassInfo implements Serializable {
	/**
	 * 分类主键
	 */
	private Integer classId;

	/**
	 * 分类名
	 */
	private String className;

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

	@Override
	public String toString() {
		return "分类主键:" + (classId == null ? "空" : classId) + ", 分类名:" + (className == null ? "空" : className);
	}
}