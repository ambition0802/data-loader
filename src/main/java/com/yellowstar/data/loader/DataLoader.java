package com.yellowstar.data.loader;

import com.yellowstar.data.loader.autoconfigure.DataLoaderProperties;
import com.yellowstar.data.loader.csv.CSVDataReader;
import com.yellowstar.data.loader.exception.CsvDirNotExistException;
import com.yellowstar.data.loader.jdbc.JdbcTemplateMixin;
import com.yellowstar.data.loader.sql.builder.SqlBuilder;
import com.yellowstar.data.loader.vo.ColumnInfo;
import com.yellowstar.data.loader.vo.ColumnNameHolder;
import com.yellowstar.data.loader.vo.ColumnNameValuePair;
import com.yellowstar.data.loader.vo.NumbericColumnTypeHolder;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/11
 */
public class DataLoader {

	private final JdbcTemplateMixin jdbcTemplateMixin;

	private final DataLoaderProperties dataLoaderProperties;

	private static final String CSV_FILE_SUFFIX = ".csv";

	public DataLoader(JdbcTemplateMixin jdbcTemplateMixin,
			DataLoaderProperties dataLoaderProperties) {
		this.jdbcTemplateMixin = jdbcTemplateMixin;
		this.dataLoaderProperties = dataLoaderProperties;
	}

	public void loadDataByCaseKey(String caseKey) {
		if (StringUtils.isEmpty(caseKey)) {
			return;
		}

		String csvDirPath = dataLoaderProperties.getCsvDir();
		File csvDir = new File(
				this.getClass().getClassLoader().getResource(csvDirPath).getPath());
		if (!csvDir.exists()) {
			throw new CsvDirNotExistException("指定存放csv文件的目录不存在：" + csvDirPath);
		}

		File[] csvFiles = csvDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(CSV_FILE_SUFFIX);
			}
		});

		for (File csvFile : csvFiles) {
			String csvFileName = csvFile.getName();
			String tableName = csvFileName.substring(0,
					csvFileName.length() - CSV_FILE_SUFFIX.length());
			Map<ColumnNameHolder, NumbericColumnTypeHolder> columnNameIsNumbericMap = jdbcTemplateMixin
					.getColumnNameIsNumbericMap(tableName);
			List<List<ColumnNameValuePair>> dataNeedBeLoaded = CSVDataReader
					.readFColumnInfoFrom(csvFile, caseKey);

			List<String> insertSQLList = new ArrayList<>();
			for (List<ColumnNameValuePair> oneRowData : dataNeedBeLoaded) {

				List<ColumnInfo> columnInfos = ColumnInfo
						.generateColumnInfoList(columnNameIsNumbericMap, oneRowData);
				SqlBuilder sqlBuilder = new SqlBuilder(tableName,
						columnNameIsNumbericMap.size(),
						dataLoaderProperties.getDefaultNullColumnPlaceHolder())
								.addColumnInfoList(columnInfos);
				String oneInsertSQL = sqlBuilder.build();
				insertSQLList.add(oneInsertSQL);
			}

			// 批量执行数据库sql插入操作
			jdbcTemplateMixin.batchInsert(insertSQLList);
		}

	}

}
