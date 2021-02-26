package cn.yz.easybuy.util;

import java.util.UUID;

/**
 * 随机数字串
 * @author
 *
 */
public class UUIDUtil {
	public static String getUUId() {
		UUID uuid =UUID.randomUUID();
		String uidStr=uuid.toString();
		//改成大写
		uidStr=uidStr.toUpperCase();
		//去-
		uidStr=uidStr.replaceAll("-", "").substring(15);//取substring从下标为？开始到？结束中的字符串的方法
		return uidStr;
	}
}
