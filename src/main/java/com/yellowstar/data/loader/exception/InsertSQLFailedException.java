package com.yellowstar.data.loader.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/11
 */
public class InsertSQLFailedException extends  RuntimeException {

    public InsertSQLFailedException(List<String> failedInsertSqlList) {
        super("这些insert sql执行失败：" + failedInsertSqlList.toString());
    }



}
