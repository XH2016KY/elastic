package com.elastic.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elastic.module.people;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-elastic.xml"})
public class PeopleTest {
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Test
	public void test() {
		
		List<people> findAll = peopleRepository.findAll();
		System.out.println(findAll);
		
	}

}
