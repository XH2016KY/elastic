package com.elastic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author happyeveryday
 * 注释: people索引条件查询
 * 2018年9月23日
 */
@Controller
@RequestMapping(produces="application/json;charset=UTF-8")
@SuppressWarnings({"rawtypes","unchecked"})
public class PeopleCondtioalQueryController {

	@Autowired
	private TransportClient client;
		
	@PostMapping(value = "quer/people/man")
	public ResponseEntity query(
			   @RequestParam(name = "name",required = false)String name,
			   @RequestParam(name = "ltage",required = false)Integer ltage,
			   @RequestParam(name = "country",required = false)String keyword,
			   @RequestParam(name ="startdate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
			   Date satrtdate,
			   @RequestParam(name ="enddate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
			   Date enddate,
			   @RequestParam(name = "gtage",required = false)Integer gtage
			){
		       BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		       if(name != null) {
		    	   boolQuery.must(QueryBuilders.matchQuery("name", name));
		       }
		       if(keyword != null) {
		    	   boolQuery.must(QueryBuilders.matchQuery("country", keyword));
		       }
		       RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age")
		    		   .from(gtage);
		       if(ltage != null && ltage >0) {
		    	   rangeQuery.to(ltage);
		       }
		       boolQuery.filter(rangeQuery);
		       
		       SearchRequestBuilder builer = this.client.prepareSearch("people")
		       .setTypes("man")
		       .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		       .setQuery(boolQuery)
		       .setFrom(0)
		       .setSize(5);
		       System.out.println(builer);
		       SearchResponse response = builer.get();
		       List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		       for(SearchHit hit:response.getHits()) {
		    	   result.add(hit.getSourceAsMap());
		       }
		       System.out.println(result);
		       return new ResponseEntity(result,HttpStatus.OK);	
	}
}
