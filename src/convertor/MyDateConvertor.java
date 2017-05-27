package convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyDateConvertor implements Converter {

	/**
	 * @clazz 要转换成的类型
	 * @value 要转换的值
	 */
	@Override
	public Object convert(Class clazz, Object value) {
		// 将String转换成Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse((String)value);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
