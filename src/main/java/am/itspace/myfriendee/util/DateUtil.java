package am.itspace.myfriendee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    private static final SimpleDateFormat SDF_SQL = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_DATE_TIME_SQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public static Date fromSqlStringToDate(String dateStr) throws ParseException {
        return SDF_SQL.parse(dateStr);
    }

    public static Date fromSqlStringToDateTime(String dateStr) throws ParseException {
        return SDF_DATE_TIME_SQL.parse(dateStr);
    }

    public static String fromDateToSqlString(Date date) {
        return SDF_SQL.format(date);
    }

    public static String fromDateToSqlDateTimeString(Date createdAt) {
        return SDF_DATE_TIME_SQL.format(createdAt);
    }
}
