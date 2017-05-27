package utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	/**
	 * 将字符串数组转换成list集合
	 * 
	 * @param strings
	 *            字符串数组
	 * @return 返回一个list集合
	 */
	public static List<String> strings2List(String[] strings) {
		List<String> list = new ArrayList<>();
		for (String string : strings) {
			list.add(string);
		}
		return list;
	}

	/**
	 * 将字符串集合转换为一个字符串
	 * 
	 * @param list
	 *            字符串集合
	 * @param value
	 *            用来分隔字符串的符号
	 * @return 返回一个字符串
	 */
	public static String list2String(List<String> list, String value) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(value);
			}
		}
		
		return sb.toString();
	}

}
