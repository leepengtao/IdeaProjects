package cn.bdqn.easybuy.util;

import java.util.UUID;

public class UUIDUtil {
	
	// 提供一个随机UUID
	public static String getUUId() {
		UUID uuid = UUID.randomUUID();
		String uidStr = uuid.toString();
		// 大写
		uidStr = uidStr.toUpperCase();
		// 去-
		uidStr = uidStr.replaceAll("-", "").substring(0,10);
		return uidStr;
	}
}
