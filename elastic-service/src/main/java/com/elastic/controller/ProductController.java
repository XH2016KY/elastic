package com.elastic.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author happyeveryday
 * 注释: 商品控制层
 * 2018 2018年9月23日
 */
@Controller
@RequestMapping(produces="application/json;charset=UTF-8")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductController {
	
	@Autowired
	private TransportClient client;
	
	/**
	 * 查询单个实体
	 * @param id
	 * @return
	 */
	@GetMapping(value="/get/people/man")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id",defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("people","man",id).get();
        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(),HttpStatus.OK);
    }
	
	/**
	 * 在索引里添加实体
	 * @param date
	 * @param age
	 * @param keyword
	 * @param name
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping(value="add/people/man")
	@ResponseBody
	public ResponseEntity add(
			@RequestParam(name = "date")
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date,
			@RequestParam(name = "age")Integer age,
			@RequestParam(name = "country")String keyword,
			@RequestParam(name = "name")String name
			) throws ParseException{
		        try {
					XContentBuilder context = XContentFactory.jsonBuilder()
					.startObject()
					.field("date",date.getTime())
					.field("age",age)
					.field("country",keyword)
					.field("name",name)
					.endObject();
					IndexResponse result = this.client.prepareIndex("people", "man")
					.setSource(context)
					.get();
					System.out.println(result.toString());
					System.out.println(result.getId());
				return new ResponseEntity(result,HttpStatus.OK);
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
				}
		
	}
	
	/**
	 *  根据索引id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "delete/people/man")
	public ResponseEntity delete(@RequestParam(name = "id")String id){
		      DeleteResponse result = this.client.prepareDelete("people","man",id).get();
		return new ResponseEntity(result,HttpStatus.OK);
		
	}
	/**
	 * 根据id修改索引
	 * @param id
	 * @param name
	 * @param age
	 * @param keyword
	 * @param date
	 * @return
	 */
	@PutMapping(value = "update/pepole/man")
	@ResponseBody
	public ResponseEntity update(
			   @RequestParam(name = "id")String id,
			   @RequestParam(name = "name",required = false)String name,
			   @RequestParam(name = "age",required = false)Integer age,
			   @RequestParam(name = "country",required = false)String keyword,
			   @RequestParam(name ="date",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
			   Date date
			){
		       UpdateRequest update = new UpdateRequest("people","man",id);
		       try {
				XContentBuilder builder = XContentFactory.jsonBuilder()
				   .startObject();
				if(name != null) {
					builder.field("name",name);
				}
				if(age != null) {
					builder.field("age",age);
				}
				if(keyword != null) {
					builder.field("country",keyword);
				}
				if(date != null) {
					builder.field("date",date.getTime());
				}
				builder.endObject();
				update.doc(builder);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		       try {
				UpdateResponse result = this.client.update(update).get();
				return new ResponseEntity(result,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

}
