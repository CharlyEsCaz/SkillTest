package mx.com.charlyescaz.web.api

import mx.com.charlyescaz.web.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SkillTestService {

    @GET("${BuildConfig.api_base}/weather")
    fun getWeather(@Query("q") q: String,
                   @Query("lat") lat: String,
                   @Query("lon") lon: String,
                   @Query("callback") callback: String,
                   @Query("id") id: String,
                   @Query("lang") lang: String?,
                   @Query("units") units: String,
                   @Query("mode") mode: String,
    ): Call<String>
}