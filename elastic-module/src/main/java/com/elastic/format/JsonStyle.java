package com.elastic.format;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author happyeveryday
 * 注释: Json字符串
 * 2018年9月23日
 */
public interface JsonStyle {
	
	default String toJson() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
