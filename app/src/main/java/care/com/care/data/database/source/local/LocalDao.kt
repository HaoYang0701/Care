package care.com.care.data.database.source.local

import androidx.room.Dao
import androidx.room.Query
import care.com.care.Model.Job

@Dao interface LocalDao {

    @Query("SELECT * FROM Jobs") fun getJobs() : List<Job>

    @Query("DELETE FROM Jobs") fun deleteJobs()
}