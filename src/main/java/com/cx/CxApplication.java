package com.cx;

import com.cx.bean.Quote;
import com.cx.config.StorageProperties;
import com.cx.config.WebSecurityConfig;
import com.cx.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

/**
 * @author CX
 * @create 2018/1/12
 */
@SpringBootApplication
@ComponentScan("com.cx")
@EnableScheduling
@EnableConfigurationProperties(value={StorageProperties.class})
public class CxApplication {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(CxApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, StorageService storageService) throws Exception {
		return args -> {
		    log.info("RestFul初始化...");
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
            log.info("文件上传初始化...");
            storageService.deleteAll();
            storageService.init();
		};
	}
}
