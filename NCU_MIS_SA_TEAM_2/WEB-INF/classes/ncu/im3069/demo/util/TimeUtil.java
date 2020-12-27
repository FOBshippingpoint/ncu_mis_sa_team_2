package ncu.im3069.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	public static DateTimeFormatter formatter ;

	public static String format(LocalDateTime localDateTime) {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return localDateTime.format(formatter);
	}
	
	public static String format(LocalDate localDate) {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(formatter);
	}
	
	public static LocalDateTime toLocalDateTimeFromString(String localDateTime) {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(localDateTime, formatter);
	}
	
}
