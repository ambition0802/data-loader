package com.yellowstar.data.loader.exception;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/11
 */
public class CsvDirNotExistException extends RuntimeException {

	public CsvDirNotExistException(String message) {
		super(message);
	}

}
