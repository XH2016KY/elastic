package com.elastic.module;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.elastic.format.JsonStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author happyeveryday
 * 注释: 商品搜索实体
 * 2018年9月23日
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductElastic implements JsonStyle, Serializable{
	/**
	 *  商品Id
	 */
	private Long productId;
	/**
	 *  商品名字
	 */
	private String name;
	/**
	 *  商品价格
	 */
	private BigDecimal price;
	/**
	 *  商品数量
	 */
	private Integer count;
	/**
	 *  商品创建时间
	 */
	private Date createTime;
	/**
	 *  商品更新时间
	 */
	private Date updateTime;

}
