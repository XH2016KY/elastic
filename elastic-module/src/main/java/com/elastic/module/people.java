package com.elastic.module;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * @author happyeveryday
 * 注释: people索引实体
 * 2018年9月23日
 */
@Document(indexName = "people",type = "post",shards = 5,replicas=1)
public class people {
    
	@Id
	private Integer age;
	
	private String country;
	
	private Date date;
	
	private String name;
	
	private String title;
}
