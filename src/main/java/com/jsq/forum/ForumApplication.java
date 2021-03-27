package com.jsq.forum;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan
public class ForumApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.devtools.restart.enabled", "false");
		ConfigurableApplicationContext context = SpringApplication.run(ForumApplication.class, args);
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource.getClass());
	}
	
	// PageHelper的配置
	@Bean
	public PageHelper pageHelper() {
		System.out.println("MyBatisConfiguration.pageHelper()");
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
}
