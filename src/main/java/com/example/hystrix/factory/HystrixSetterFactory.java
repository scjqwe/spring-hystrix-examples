package com.example.hystrix.factory;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 
 * HystrixSetter工厂类<br>
 * 版权: Copyright (c) 2011-2018<br>
 * 
 * @author: 孙常军<br>
 * @date: 2018年9月13日<br>
 */
public class HystrixSetterFactory {

	/**
	 * 生成通用Setter
	 * 
	 * @param groupKey
	 * @param gommandKey
	 * @return
	 */
	public static Setter customSetter(String groupKey, String commandKey) {
		// 设置CommandGroupKey
		HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory.asKey(groupKey);

		// 设置CommandKey
		HystrixCommandKey hystrixCommandKey = HystrixCommandKey.Factory.asKey(commandKey);

		// 设置线程配置
		HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
		// 并发执行的最大线程数，默认10
				.withCoreSize(2000)
				// 最大线程数
				.withMaximumSize(2000)
				// 排队线程数量阈值;即使maxQueueSize没有达到，达到queueSizeRejectionThreshold该值后，请求也会被拒绝。负值代表不限制
				.withQueueSizeRejectionThreshold(-1);

		// 设置命令配置
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
		// 设置统计的时间窗口值的，毫秒值，circuit break 的打开会根据1个rolling window的统计来计算。若rolling
		// window被设为10000毫秒，则rolling
		// window会被分成n个buckets，每个bucket包含success，failure，timeout，rejection的次数的统计信息。默认10000
				.withMetricsRollingStatisticalWindowInMilliseconds(5 * 60 * 1000)
				// 设置一个rolling window被划分的数量，若numBuckets＝10，rolling
				// window＝10000，那么一个bucket的时间即1秒。必须符合rolling window %
				// numberBuckets == 0。默认10
				.withMetricsRollingStatisticalWindowBuckets(5 * 60)
				// 设置rolling percentile window的时间，默认60000
				.withMetricsRollingPercentileWindowInMilliseconds(5 * 60 * 1000)
				// 设置rolling percentile window的numberBuckets
				.withMetricsRollingPercentileWindowBuckets(5 * 6)
				// 如果bucket
				// size＝100，window＝10s，若这10s里有500次执行，只有最后100次执行会被统计到bucket里去。增加该值会增加内存开销以及排序的开销。默认100
				.withMetricsRollingPercentileBucketSize(200)
				// 触发短路的时间值，当该值设为5000时，则当触发circuit
				// break后的5000毫秒内都会拒绝request，也就是5000毫秒后才会关闭circuit。默认5000
				.withCircuitBreakerSleepWindowInMilliseconds(5000)
				// 命令执行超时时间，默认1000ms;增加3秒是为了做抓取成功后的推送和缓存工作
				.withExecutionTimeoutInMilliseconds(2000)
				// 错误比率阀值，如果错误率>=该值，circuit会被打开，并短路所有请求触发fallback。默认50
				.withCircuitBreakerErrorThresholdPercentage(50)
				// 一个rolling window内最小的请求数。如果设为20，那么当一个rolling
				// window的时间内（比如说1个rolling
				// window是10秒）收到19个请求，即使19个请求都失败，也不会触发circuit break。默认20
				.withCircuitBreakerRequestVolumeThreshold(100);

		return Setter.withGroupKey(hystrixCommandGroupKey).andCommandKey(hystrixCommandKey).andThreadPoolPropertiesDefaults(threadPoolProperties)
				.andCommandPropertiesDefaults(commandProperties);
	}

}
