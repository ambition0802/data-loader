package com.yellowstar.data.loader.vo;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class ColumnValueHolder {

	public ColumnValueHolder(String value) {
		this.value = value;
	}

	private final String value;

	public String getValue() {
		return value;
	}

}
