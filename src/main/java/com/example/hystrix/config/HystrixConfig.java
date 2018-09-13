package com.example.hystrix.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * 
 * Hystrix配置<br>
 * 版权: Copyright (c) 2011-2018<br>
 * 
 * @author: 孙常军<br>
 * @date: 2018年9月13日<br>
 */
@Configuration
public class HystrixConfig {

	@Bean
	public HystrixMetricsStreamServlet hystrixMetricsStreamServlet() {
		return new HystrixMetricsStreamServlet();
	}

	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean(HystrixMetricsStreamServlet servlet) {
		ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>();
		registrationBean.setServlet(servlet);
		registrationBean.setEnabled(true);// 是否启用该registrationBean
		registrationBean.addUrlMappings("/hystrix.stream");
		return registrationBean;
	}
}
