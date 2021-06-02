package com.yellowstar.data.loader.jdbc;

import com.yellowstar.data.loader.exception.InsertSQLFailedException;
import com.yellowstar.data.loader.vo.ColumnNameHolder;
import com.yellowstar.data.loader.vo.NumbericColumnTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.*;

/**
 * @author yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class JdbcTemplateMixin {

	private static final int INSERT_SUCCESS = 1;

	public JdbcTemplateMixin(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private JdbcTemplate jdbcTemplate;

	private final List<Integer> numbericColumnType = Arrays.asList(Types.TINYINT,
			Types.SMALLINT, Types.INTEGER, Types.BIGINT, Types.FLOAT, Types.DOUBLE,
			Types.NUMERIC, Types.DECIMAL);

	public Map<ColumnNameHolder, NumbericColumnTypeHolder> getColumnNameIsNumbericMap(
			String tableName) {
		Map<ColumnNameHolder, NumbericColumnTypeHolder> result = new HashMap<>();
		SqlRowSet sqlRowSet = jdbcTemplate
				.queryForRowSet("SELECT * FROM " + tableName + " LIMIT 0 ");
		SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
		String[] columnNames = metaData.getColumnNames();
		Map<String, Integer> columnNameTypeMap = new HashMap<>();
		for (int i = 0; i < columnNames.length; i++) {
			String columnName = columnNames[i].trim();
			// String columnTypeName = metaData.getColumnTypeName(i + 1);
			int columnType = metaData.getColumnType(i + 1);
			result.put(new ColumnNameHolder(columnName),
					new NumbericColumnTypeHolder(isNumbericColumnType(columnType)));
		}

		return result;
	}

	public void batchInsert(List<String> insertSqlList) {

		if (insertSqlList == null || insertSqlList.size() == 0) {
			return;
		}
		int[] insertResultArr = jdbcTemplate
				.batchUpdate(insertSqlList.toArray(new String[0]));

		List<String> failedInsertSqlList = new ArrayList<>();
		for (int i = 0; i < insertResultArr.length; i++) {
			if (insertResultArr[i] != INSERT_SUCCESS) {
				failedInsertSqlList.add(insertSqlList.get(i));
			}
		}

		if (failedInsertSqlList.size() > 0) {
			throw new InsertSQLFailedException(failedInsertSqlList);
		}

	}

	private boolean isNumbericColumnType(int columnType) {
		return numbericColumnType.contains(columnType);
	}

}
