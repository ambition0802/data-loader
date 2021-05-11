package com.yellowstar.data.loader.sql.builder;

import com.yellowstar.data.loader.vo.ColumnInfo;

import java.util.List;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class SqlBuilder {

	private final ColumnInfo[] columnInfoArr;

	private final String tableName;

	private final int columnNum;

	private int index = 0;

	private static final String INSERT_SQL_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";

	private static final String COLUMN_SEPARATOR = ", ";

	private String nullValuePlaceholder = "NULL_COLUMN";

	public SqlBuilder(String tableName, int columnNum, String nullValuePlaceholder) {
		this.tableName = tableName;
		this.columnNum = columnNum;
		this.nullValuePlaceholder = nullValuePlaceholder;

		this.columnInfoArr = new ColumnInfo[columnNum];
	}

	public SqlBuilder addColumnInfo(ColumnInfo columnInfo) {
		this.columnInfoArr[this.index++] = columnInfo;
		return this;
	}

	public SqlBuilder addColumnInfoList(List<ColumnInfo> columnInfoList) {
		columnInfoList.forEach(columnInfo -> addColumnInfo(columnInfo));
		return this;
	}

	public String build() {

		StringBuilder columnsBuilder = new StringBuilder();
		StringBuilder valuesBuilder = new StringBuilder();
		for (int i = 0; i < this.columnNum; i++) {
			ColumnInfo columnInfo = columnInfoArr[i];
			String columnValue = columnInfo.getValue();
			if (columnValue != null && !columnValue.equals(nullValuePlaceholder)) {

				columnsBuilder.append(columnInfo.getName()).append(COLUMN_SEPARATOR);
				if (columnInfo.getIsNumberic()) {
					valuesBuilder.append(columnValue.trim()).append(COLUMN_SEPARATOR);
				}
				else {
					// columnValue中如果存在单引号会有问题
					valuesBuilder.append("'")
							.append(columnValue.trim().replace("'", "\\'")).append("'")
							.append(", ");
				}
			}
		}

		String columnsSubSql = columnsBuilder.toString();
		String valuesSubSql = valuesBuilder.toString();
		if (!columnsSubSql.equals("")) {
			columnsSubSql = columnsSubSql.substring(0,
					columnsSubSql.length() - COLUMN_SEPARATOR.length());
			valuesSubSql = valuesSubSql.substring(0,
					valuesBuilder.length() - COLUMN_SEPARATOR.length());
		}

		return String.format(INSERT_SQL_TEMPLATE, tableName, columnsSubSql, valuesSubSql);
	}

}
