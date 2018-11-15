package care.com.careOff.Utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref(val activity: Activity) {
        var sharedPref : SharedPreferences

        init {
           sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        }

        fun update(key : String, value : String) {
            with(sharedPref.edit()){
                putString(key, value)
                commit()
            }
        }

        fun fetch(key: String) : String? {
            return  sharedPref.getString(key, null)
        }

}