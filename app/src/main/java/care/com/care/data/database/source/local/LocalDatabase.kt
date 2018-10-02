package care.com.care.data.database.source.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao() : LocalDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        private val lock = Any()

        fun getInstance(context : Context) : LocalDatabase {
            synchronized(lock) {
                if (INSTANCE != null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            LocalDatabase::class.java, "Local.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}