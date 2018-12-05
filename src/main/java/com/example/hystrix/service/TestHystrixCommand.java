package com.example.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

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
	 */
	protected TestHystrixCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestGroup"))
		/* 依赖超时时间,500毫秒 */
		.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
	}

	/**
	 * 具体执行方法
	 */
	@Override
	protected Object run() throws Exception {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return "run";
	}

	@Override
	protected Object getFallback() {
		return "fallback";
	}

	public static void main(String[] args) {
		logger.info("{}", new TestHystrixCommand().execute());
	}

}
