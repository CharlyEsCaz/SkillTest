package mx.com.charlyescaz.web.api

import android.util.Log
import com.google.gson.GsonBuilder
import mx.com.charlyescaz.web.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object APISkilltest {

    private const val TAG = "API_SKILL_TEST"
    private val apiService: SkillTestService

    init{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("x-rapidapi-key", BuildConfig.api_key)
                    .addHeader("x-rapidapi-host", BuildConfig.api_host)
                    .build()
                chain.proceed(newRequest)
            }

        val gson = GsonBuilder()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.api_base)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClientBuilder.build())
            .build()

        apiService = retrofit.create(SkillTestService::class.java)
    }

    private fun <T>doRequest(operation: String, call: Call<T>, cb: (success: Boolean, data: T?) -> Unit) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                handleFailure(operation,t,cb)
            }
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    cb(true, response.body())
                } else {
                    handleUnsuccessful(operation, cb)
                }

            }

        })
    }

    private fun <T>handleUnsuccessful(operation: String,callback: (success: Boolean,data: T?) -> Unit) {
        Log.w(TAG,"$operation was unsuccessful")
        callback(false, null)
    }

    private fun <T>handleFailure(operation: String, t: Throwable,callback: (success: Boolean,data: T?) -> Unit) {
        Log.e(TAG, "$operation has failed")
        Log.e(TAG, "Message is: " + t.message)
        callback(false, null)
    }

    fun getWeather(q: String,
                   lat: String,
                   lon: String,
                   callback: String,
                   id: String,
                   lang: String?,
                   units: String,
                   mode: String,
                   cb: (success: Boolean, data: String?) -> Unit) {
        doRequest(
            "Get Weather Data",
            apiService.getWeather(q,lat,lon,callback,id,lang,units,mode),
            cb
        )
    }
}