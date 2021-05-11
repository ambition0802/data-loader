package com.yellowstar.data.loader.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class ColumnInfo {

	public ColumnInfo(ColumnNameHolder name, ColumnValueHolder value,
			NumbericColumnTypeHolder isNumberic) {
		this.name = name.getValue();
		this.value = value.getValue();
		this.isNumberic = isNumberic.getIsNumberic();
	}

	private final String name;

	private final String value;

	private final boolean isNumberic;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean getIsNumberic() {
		return isNumberic;
	}

	public static List<ColumnInfo> generateColumnInfoList(
			Map<ColumnNameHolder, NumbericColumnTypeHolder> colNameNumbericMap,
			List<ColumnNameValuePair> colValPair) {

		List<ColumnInfo> result = new ArrayList<>();
		colValPair.forEach(colVal -> {
			ColumnNameHolder columnNameHolder = new ColumnNameHolder(
					colVal.getColumnName());
			result.add(new ColumnInfo(columnNameHolder,
					new ColumnValueHolder(colVal.getColumnValue()),
					colNameNumbericMap.get(columnNameHolder)));
		});

		return result;
	}

	public static void main(String[] args) {
		Map<ColumnNameHolder, NumbericColumnTypeHolder> colNameNumbericMap = new HashMap<>();
		colNameNumbericMap.put(new ColumnNameHolder("id"),
				new NumbericColumnTypeHolder(true));

		colNameNumbericMap.get(new ColumnNameHolder("id"));
		return;
	}

}
