package com.yellowstar.data.loader.autoconfigure;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import javax.sql.DataSource;

/**
 * DataLoaderAutoConfiguration Tester.
 *
 * @author <Authors name>
 * @since <pre>5月 11, 2021</pre>
 * @version 1.0
 */
public class DataLoaderAutoConfigurationTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(DataLoaderAutoConfiguration.class));

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}



}
