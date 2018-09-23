package com.elastic.repositories;

import java.util.List;
import com.elastic.module.people;

/**
 * 
 * @author happyeveryday
 * 注释: 商品数据仓库接口
 * 2018年9月23日
 */
public interface PeopleRepository /*extends ElasticsearchRepository<people, Integer>*/{
	
	/**
	 * 查询所有文件
	 */
	List<people>findAll();
	/**
	 * 查询单个实例
	 * @return
	 */
	people findOne();
	
    
}
