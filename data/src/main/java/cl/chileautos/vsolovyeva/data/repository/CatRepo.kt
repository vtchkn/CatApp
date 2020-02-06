package cl.chileautos.vsolovyeva.data.repository


import cl.chileautos.vsolovyeva.data.model.TheCatImage
import cl.chileautos.vsolovyeva.data.api.TheCatApiService
import cl.chileautos.vsolovyeva.data.model.Resource
import cl.chileautos.vsolovyeva.data.model.Status
import retrofit2.Response
import java.io.IOException
import java.util.HashMap

class CatRepo(private val api: TheCatApiService, private val apiKey: String) {

    suspend fun loadCats(): List<TheCatImage>? {
        return safeApiCall(
            call = {
                api.getImagesAsync(apiKey, hashMapOf(
                    Pair("size", "mid"),
                    Pair("limit", "100"),
                    Pair("mime_types", "jpg")
                )) },
            errorMessage = "Error loading stock data"
        )
    }


    private suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): T? {
        val result: Resource<T> = safeApiResult(call, errorMessage)
        var data: T? = null
        when (result.status) {
            Status.SUCCESS ->
                data = result.data
            Status.ERROR -> {
                throw result.throwable!!
            }
        }
        return data

    }


    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Resource<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) Resource.success(response.body())
            else {
                Resource.error(IOException("$errorMessage " + response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Resource.error(e)
        }
    }


}