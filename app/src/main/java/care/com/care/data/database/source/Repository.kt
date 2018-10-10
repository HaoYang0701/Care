package care.com.care.data.database.source

import care.com.care.Model.Job
import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import care.com.care.data.database.source.local.LocalDataSource
import care.com.care.data.database.source.remote.RemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Observable

class Repository(val remoteDataSource : RemoteDataSource, val localDataSource: LocalDataSource) : DataSource {

    override fun registerNewUser(jsonParams: String): Observable<RegistrationResponse> {
        return remoteDataSource.registerNewUser(jsonParams)
    }

    var casheIsDirty = false
    var cachedJobs : LinkedHashMap<String, Job> = LinkedHashMap()

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

    override fun getJobRequests() : Flowable<List<Job>>{
        if (cachedJobs.isNotEmpty() && !casheIsDirty){
            return Flowable.just(ArrayList<Job>(cachedJobs.values))
        }

        val remoteJob = getJobFromRemoteDataSource()

        if (casheIsDirty) {
            return remoteJob
        } else {
            val localJob = getAndCacheLocalJobs()
            return localJob
        }
    }
    
    private fun getJobFromRemoteDataSource(): Flowable<List<Job>> {
        return remoteDataSource.getJobRequests()
    }

    private fun getAndCacheLocalJobs(): Flowable<List<Job>> {
        return localDataSource.getJobRequests()
                .flatMap { jobs ->
                    Flowable.fromIterable(jobs)
                            .doOnNext { job -> cachedJobs.put(job.id.toString(), job)}
                            .toList()
                            .toFlowable()
                }
    }

}