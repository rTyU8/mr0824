package main.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class CalendarUtils {
	
	public static boolean isWeekend(LocalDate date) {
		DayOfWeek weekday = date.getDayOfWeek();
		return DayOfWeek.SATURDAY.equals(weekday) || DayOfWeek.SUNDAY.equals(weekday);
	}
	
	public static boolean isHoliday(LocalDate date) {
		return isIndependenceDay(date) || isLaborDay(date);
	}
	
	private static boolean isIndependenceDay(LocalDate date) {
        LocalDate holiday = LocalDate.of(date.getYear(), Month.JULY, 4);
        
        // Check if holiday occurs on the weekend
        DayOfWeek holidayDayOfWeek = holiday.getDayOfWeek();
        if (DayOfWeek.SATURDAY.equals(holidayDayOfWeek)) {
        	holiday = holiday.minusDays(1);
        } else if (DayOfWeek.SUNDAY.equals(holidayDayOfWeek)) {
        	holiday = holiday.plusDays(1);
        }
        
        return date.equals(holiday);
	}
	
	private static boolean isLaborDay(LocalDate date) {
        // Find the first Monday of September for the input date's year
        LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);

        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
        	laborDay = laborDay.plusDays(1);
        }

        return laborDay.equals(date);
	}
}
