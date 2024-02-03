package renatohack.minhascompras.Model.GoogleAPI

import renatohack.minhascompras.Model.GoogleAPI.Interfaces.GoogleAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit : Retrofit? = null

    private fun getRetrofit() : Retrofit {
        if (retrofit == null){
            retrofit = Retrofit
                .Builder()
                .baseUrl("https://customsearch.googleapis.com/customsearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getGoogleAPIService() : GoogleAPIService {
        return getRetrofit().create(GoogleAPIService::class.java)
    }

}