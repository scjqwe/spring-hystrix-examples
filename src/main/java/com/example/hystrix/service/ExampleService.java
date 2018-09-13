package com.example.hystrix.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.Observer;

import com.example.hystrix.command.ExampleHystrixCommand;
import com.example.hystrix.factory.HystrixSetterFactory;
import com.netflix.hystrix.HystrixCommand;

/**
 * 
 * 示例业务类<br>
 * 版权: Copyright (c) 2011-2018<br>
 * 
 * @author: 孙常军<br>
 * @date: 2018年9月13日<br>
 */
public class ExampleService {
	private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

	public void execute() {
		HystrixCommand.Setter setter = HystrixSetterFactory.customSetter("TestCommandGroupKey", "TestCommandKey");
		ExampleHystrixCommand command = new ExampleHystrixCommand(setter);

		// 同步执行
		Boolean result = (Boolean) command.execute();
		logger.info("sync result: {}", result);

		// 异步执行
		command = new ExampleHystrixCommand(setter);
		Future<Object> future = command.queue();
		try {
			result = (Boolean) future.get(1000, TimeUnit.MILLISECONDS);
			logger.info("sync result: {}", result);
		} catch (TimeoutException e) {
			logger.error("Exception", e);
		} catch (InterruptedException e) {
			logger.error("Exception", e);
		} catch (ExecutionException e) {
			logger.error("Exception", e);
		}

		// 响应执行
		command = new ExampleHystrixCommand(setter);
		Observable<Object> observe = command.observe();
		observe.subscribe(new Observer<Object>() {
			@Override
			public void onCompleted() {
				// 最终回调，onNext/onError之后
				logger.info("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// 错误时回调
				logger.info("onError::Exception", arg0);
			}

			@Override
			public void onNext(Object arg0) {
				// 获取结果后回调
				logger.info("onNext::result:{}", arg0);
			}
		});

	}

	public static void main(String[] args) {
		new ExampleService().execute();
	}

}
