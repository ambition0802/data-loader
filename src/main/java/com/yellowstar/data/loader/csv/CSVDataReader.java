package com.yellowstar.data.loader.csv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.yellowstar.data.loader.vo.ColumnNameHolder;
import com.yellowstar.data.loader.vo.ColumnNameValuePair;
import com.yellowstar.data.loader.vo.ColumnValueHolder;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import java.io.File;
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
public class CSVDataReader {

	public static List<List<ColumnNameValuePair>> readFColumnInfoFrom(File csvFile,
			String caseKey) {
		CsvReader reader = CsvUtil.getReader();
		CsvData data = reader.read(csvFile);
		List<CsvRow> rows = data.getRows();

		// 第一行是所有列的列名，第一列列名永远是caseKey
		CsvRow firstRow = rows.get(0);
		List<String> rawList = firstRow.getRawList();

		// 跳过第一列caseKey来遍历csv文件的第一行列名
		Map<Integer, String> csvColumnIndexNameMap = new HashMap<>();
		for (int i = 1; i < rawList.size(); i++) {
			csvColumnIndexNameMap.put(i, rawList.get(i).trim());
		}

		List<List<ColumnNameValuePair>> result = new ArrayList<>();
		// 遍历csv文件的数据行
		for (int i = 1; i < rows.size(); i++) {
			CsvRow dataRow = rows.get(i);

			String currentRowCasekey = dataRow.get(0).trim();
			if (!currentRowCasekey.equals(caseKey.trim())) {
				// 不是指定的case_key就pass掉
				continue;
			}

			List<ColumnNameValuePair> oneRowColumnData = new ArrayList<>();
			for (int j = 1; j < dataRow.size(); j++) {
				String columnValue = dataRow.get(j).trim();
				oneRowColumnData.add(new ColumnNameValuePair(
						new ColumnNameHolder(csvColumnIndexNameMap.get(j)),
						new ColumnValueHolder(columnValue)));
			}

			result.add(oneRowColumnData);
		}

		return result;
	}

}
