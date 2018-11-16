package care.com.careOff.Utils

class TextUtil {
    companion object {
        fun getNotificationText(string : String) : String {
            val stringArray = string.split("=")
            if (stringArray.size >= 2) {
                return stringArray[1]
            }
            return ""
        }
    }
}