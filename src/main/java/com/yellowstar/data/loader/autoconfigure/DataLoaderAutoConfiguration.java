package com.yellowstar.data.loader.autoconfigure;

import com.yellowstar.data.loader.DataLoader;
import com.yellowstar.data.loader.jdbc.JdbcTemplateMixin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/11
 */
@Configuration
@ConditionalOnClass({ JdbcTemplate.class })
@ConditionalOnBean({ JdbcTemplate.class })
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
@EnableConfigurationProperties(DataLoaderProperties.class)
public class DataLoaderAutoConfiguration {

	@Bean
	public JdbcTemplateMixin jdbcTemplateMixin(JdbcTemplate jdbcTemplate) {
		return new JdbcTemplateMixin(jdbcTemplate);
	}

	@Bean
	public DataLoader dataLoader(JdbcTemplateMixin jdbcTemplateMixin,
			DataLoaderProperties dataLoaderProperties) {
		return new DataLoader(jdbcTemplateMixin, dataLoaderProperties);
	}

}
