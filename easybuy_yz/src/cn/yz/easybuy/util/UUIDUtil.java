package cn.yz.easybuy.util;

import java.util.UUID;

/**
 * ������ִ�
 * @author
 *
 */
public class UUIDUtil {
	public static String getUUId() {
		UUID uuid =UUID.randomUUID();
		String uidStr=uuid.toString();
		//�ĳɴ�д
		uidStr=uidStr.toUpperCase();
		//ȥ-
		uidStr=uidStr.replaceAll("-", "").substring(15);//ȡsubstring���±�Ϊ����ʼ���������е��ַ����ķ���
		return uidStr;
	}
}
