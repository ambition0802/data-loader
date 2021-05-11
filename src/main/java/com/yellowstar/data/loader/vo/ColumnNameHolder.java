package com.yellowstar.data.loader.vo;

import java.util.Objects;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class ColumnNameHolder {

	public ColumnNameHolder(String value) {
		this.value = value;
	}

	private final String value;

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ColumnNameHolder that = (ColumnNameHolder) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

}
