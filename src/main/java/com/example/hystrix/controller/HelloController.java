package com.example.hystrix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * 测试hystrix-dashboard监控<br>
 * 版权: Copyright (c) 2011-2018<br>
 * 
 * @author: 孙常军<br>
 * @date: 2018年9月13日<br>
 */
@RestController
public class HelloController {

	@HystrixCommand
	@RequestMapping("/hello")
	public Object hello() {
		return "hello";
	}

}
