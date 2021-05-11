package com.yellowstar.data.loader.vo;

/**
 * @author 黄强(huangqiang.439 @ bytedance.com)
 * @version 1.0
 * @description
 * @created 2021/5/9
 */
public class NumbericColumnTypeHolder {

	public NumbericColumnTypeHolder(boolean isNumberic) {
		this.isNumberic = isNumberic;
	}

	private boolean isNumberic;

	public boolean getIsNumberic() {
		return isNumberic;
	}

}
