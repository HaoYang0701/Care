package care.com.care.data.database.source.local

import care.com.care.Model.Job
import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import care.com.care.data.database.source.DataSource
import io.reactivex.Flowable
import io.reactivex.Observable

class LocalDataSource(val localDao : LocalDao) : DataSource {

    override fun registerNewUser(jsonParams: String): Observable<RegistrationResponse> {
        return Observable.empty()
    }

    override fun getToken(): Flowable<Token> {
        return Flowable.empty()
    }

    override fun deleteJob(jobID: String) {

    }

    override fun submitJobRequest() {

    }

    override fun applyForJob(jobID: String) {

    }

    override fun refreshJobs() {

    }

    override fun getJob(jobID: String) {

    }

    override fun getJobRequests() : Flowable<List<Job>> {
        val jobs = localDao.getJobs()
        if (jobs.isNotEmpty()) {
            return Flowable.just(jobs)
        }
        return Flowable.empty()
    }

}