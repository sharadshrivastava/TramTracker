package au.com.realestate.hometime.service


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import au.com.realestate.hometime.R
import au.com.realestate.hometime.TramApp
import au.com.realestate.hometime.service.models.ApiResponse
import au.com.realestate.hometime.service.models.Token
import au.com.realestate.hometime.service.models.Tram
import com.squareup.moshi.JsonDataException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TramsRepository @Inject constructor() {

    private val TAG = TramsRepository::class.java.name

    @Inject
    lateinit var tramsApi: TramsApi

    init {
        TramApp.get().component.inject(this)
    }

    fun getToken(): LiveData<Resource<Token?>> {
        val data = MutableLiveData<Resource<Token?>>()

        tramsApi.token().enqueue(object : Callback<ApiResponse<Token?>> {
            override fun onResponse(call: Call<ApiResponse<Token?>>, response: Response<ApiResponse<Token?>>) {
                if (response.isSuccessful) {
                    val token = response.body()?.responseObject.orEmpty()[0]
                    data.value = Resource.success(token)
                } else {
                    handleFailure(data, response.message())
                    response.errorBody()?.close()
                }
            }

            override fun onFailure(call: Call<ApiResponse<Token?>>, t: Throwable) {
                Log.e(TAG, t.stackTrace.toString())

                var error = R.string.network_error
                if (t is JsonDataException) {
                    error = R.string.response_error
                }
                handleFailure(data, TramApp.get().getString(error))
            }
        })
        return data
    }

    fun getTrams(stopId: String, token: String?): LiveData<Resource<List<Tram?>?>> {
        val data = MutableLiveData<Resource<List<Tram?>?>>()

        tramsApi.trams(stopId, token).enqueue(object : Callback<ApiResponse<Tram?>> {
            override fun onResponse(call: Call<ApiResponse<Tram?>>, response: Response<ApiResponse<Tram?>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    data.setValue(Resource.success(apiResponse?.responseObject))
                } else {
                    handleFailure(data, response.message())
                    response.errorBody()?.close()
                }
            }

            override fun onFailure(call: Call<ApiResponse<Tram?>>, t: Throwable) {
                Log.e(TAG, t.stackTrace.toString())
                var error = R.string.network_error
                if (t is JsonDataException) {
                    error = R.string.response_error
                }
                handleFailure(data, TramApp.get().getString(error))
            }
        })
        return data
    }

    private fun handleFailure(liveData: MutableLiveData<*>, msg: String?) {
        liveData.value = Resource.error<Any>(msg, null)
    }

    //This is required to set test api using mock web server.
    fun setTramsApiService(testApi: TramsApi) {
        tramsApi = testApi
    }
}