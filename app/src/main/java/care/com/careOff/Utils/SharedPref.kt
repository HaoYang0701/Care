package care.com.careOff.Utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPref(val context: Context) {
        var sharedPref : SharedPreferences

        init {
           sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun update(key : String, value : String) {
            with(sharedPref.edit()){
                putString(key, value)
                commit()
            }
        }

        fun fetch(key: String) : String {
            return  sharedPref.getString(key, "")
        }

}