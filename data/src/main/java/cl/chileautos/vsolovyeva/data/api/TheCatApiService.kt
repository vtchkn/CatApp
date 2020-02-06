package cl.chileautos.vsolovyeva.data.api


import android.util.Log
import cl.chileautos.vsolovyeva.data.BuildConfig
import cl.chileautos.vsolovyeva.data.model.TheCatImage
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import java.util.*

interface TheCatApiService {

    /**
     * finds the cats images by specific query
     * all possible params
     * - size - String - full, med, small, thumb - in this example we use small by default
     * - category_ids - array[integer] (not using in this example)
     * - breed_id - String (not using in this example)
     * - mime_types - array[String] (not using in this example)
     * - order - String (not using in this example)
     * - limit - Int (not using in this example)
     * - page - Int (not using in this example)
     * - format - String (not using in this example)
     * @param query of categories anf breed_id
     * @return  observable of cat images list
     */
    @Headers("Content-Type: application/json")
    @GET("v1/images/search")
    suspend fun getImagesAsync(@Header("x-api-key") apiKey: String, @QueryMap query: HashMap<String, String>): Response<List<TheCatImage>>

    companion object Factory {
        fun create(baseUrl: String): TheCatApiService {
            val gson = GsonBuilder()
                .create()
            var retrofit: Retrofit
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("TheCatApiService", message)
                }
            }).apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                OkHttpClient.Builder()
                    .addInterceptor(this)
                    .build().apply {
                        retrofit = Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                            .client(this)
                            .build()
                    }
            }

            return retrofit.create(TheCatApiService::class.java)
        }
    }

}
