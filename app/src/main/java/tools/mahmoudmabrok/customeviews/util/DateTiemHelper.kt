package tools.mahmoudmabrok.customeviews.util

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter


object DateTiemHelper {

    fun get12HourTime(hours :Int , minutes:Int): String? {
        val time  = LocalTime.of(hours,minutes)
        val st = time.format(DateTimeFormatter.ofPattern("hh:mm a"))
        return st
    }

    fun get24HourTime(hours :Int , minutes:Int): String? {
        val date12 = get12HourTime(hours, minutes)
        if (date12 == null) return  date12
        // get time from dateString
        val time  = LocalTime.parse(date12 , DateTimeFormatter.ofPattern("hh:mm a"))
        // convert it to 24H time
        return time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

   fun get24HourTime(date:String?): String? {
        if (date == null) return null
        // get time from dateString
        val time  = LocalTime.parse(date , DateTimeFormatter.ofPattern("hh:mm a"))
        // convert it to 24H time
        val st = time.format(DateTimeFormatter.ofPattern("HH:mm"))
        return st
    }




}