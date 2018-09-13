package com.example.hystrix.command;

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
public class ExampleHystrixCommand extends HystrixCommand<Object> {
	private static final Logger logger = LoggerFactory.getLogger(ExampleHystrixCommand.class);

	/**
	 * 构造方法指定配置
	 * 
	 * @param setter
	 */
	public ExampleHystrixCommand(Setter setter) {
		super(setter);
	}

	/**
	 * 具体执行方法
	 */
	@Override
	protected Object run() throws Exception {
		for (int i = 0; i < 10; i++) {
			logger.info("任务正在执行...");
		}
		return Boolean.TRUE;
	}

	/**
	 * 降级方法
	 */
	@Override
	protected Object getFallback() {
		logger.info("任务执行异常...");
		return Boolean.FALSE;
	}

}
