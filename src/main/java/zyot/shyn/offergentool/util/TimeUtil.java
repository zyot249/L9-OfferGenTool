package zyot.shyn.offergentool.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static LocalDate getLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
