package com.example.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;

/**
 * 
 * HystrixCommand测试任务类<br>
 * 版权: Copyright (c) 2011-2018<br>
 * 
 * @author: 孙常军<br>
 * @date: 2018年9月13日<br>
 */
public class TestHystrixCommand extends HystrixCommand<Object> {
	private static final Logger logger = LoggerFactory.getLogger(TestHystrixCommand.class);

	/**
	 * 构造方法指定配置
	 * 
	 * @param setter
	 */
	protected TestHystrixCommand(Setter setter) {
		super(setter);
	}

	/**
	 * 具体执行方法
	 */
	@Override
	protected Object run() throws Exception {
		for (int i = 0; i < 1000; i++) {
			logger.info("任务正在执行...");
		}
		return Boolean.TRUE;
	}

}
