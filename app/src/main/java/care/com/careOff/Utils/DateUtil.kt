package care.com.careOff.Utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        fun getDate(dateString : String) : String {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            var date = Date().toString()
            try {
                val fullDate = formatter.parse(dateString)
                val monthformatter = SimpleDateFormat("MMM dd", Locale.US)
                date = monthformatter.format(fullDate).toString()
            } catch (e : ParseException) {
            }
            return date
        }

        fun getTime(dateString: String, lengthInMins: Int) : String {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            var time = ""
            try {
                val fullDate = formatter.parse(dateString)
                val calendar = Calendar.getInstance()
                calendar.time = fullDate
                calendar.add(Calendar.MINUTE, lengthInMins)
                val timeformatter = SimpleDateFormat("h:mm a")

                val starttime = timeformatter.format(fullDate)
                val endDate = Date(calendar.timeInMillis)
                val endtime = timeformatter.format(endDate)
                time = "$starttime - $endtime"
            } catch (e : ParseException) {
            }
            return time
        }
    }
}