package com.yellowstar.data.loader.vo;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class ColumnNameValuePair {

	private final String columnName;

	private final String columnValue;

	public ColumnNameValuePair(ColumnNameHolder columnName,
			ColumnValueHolder columnValue) {
		this.columnName = columnName.getValue();
		this.columnValue = columnValue.getValue();
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

}
