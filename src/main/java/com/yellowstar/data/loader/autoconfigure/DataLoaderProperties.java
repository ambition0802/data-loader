package com.yellowstar.data.loader.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/11
 */
@ConfigurationProperties(prefix = DataLoaderProperties.DATA_LOADER_PREFIX)
public class DataLoaderProperties {

	public static final String DATA_LOADER_PREFIX = "dataloader";

	/**
	 * The direcotry path of csv files which contaions the data to be loaded to the
	 * database. The direcotry path is relative to the project root directory.
	 */
	private String csvDir = "testdata";

	/**
	 * The default value of placeholder for columns whose value is null
	 */
	private String defaultNullColumnPlaceHolder = "NULL_COLUMN";

	public String getCsvDir() {
		return csvDir;
	}

	public void setCsvDir(String csvDir) {
		this.csvDir = csvDir;
	}

	public String getDefaultNullColumnPlaceHolder() {
		return defaultNullColumnPlaceHolder;
	}

	public void setDefaultNullColumnPlaceHolder(String defaultNullColumnPlaceHolder) {
		this.defaultNullColumnPlaceHolder = defaultNullColumnPlaceHolder;
	}

}
