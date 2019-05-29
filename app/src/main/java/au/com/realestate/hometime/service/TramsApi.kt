package au.com.realestate.hometime.service

import au.com.realestate.hometime.service.models.ApiResponse
import au.com.realestate.hometime.service.models.Token
import au.com.realestate.hometime.service.models.Tram
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TramsApi {

    @GET("/TramTracker/RestService/GetDeviceToken/?aid=TTIOSJSON&devInfo=HomeTimeAndroid")
    fun token(): Call<ApiResponse<Token?>>

    @GET("/TramTracker/RestService//GetNextPredictedRoutesCollection/{stopId}/78/false/?aid=TTIOSJSON&cid=2")
    fun trams(@Path("stopId") stopId: String, @Query("tkn") token: String?): Call<ApiResponse<Tram?>>

    companion object {
        var NORTH_CODE = "4055"
        var SOUTH_CODE = "4155"
        var BASE_URL = "http://ws3.tramtracker.com.au"
    }
}