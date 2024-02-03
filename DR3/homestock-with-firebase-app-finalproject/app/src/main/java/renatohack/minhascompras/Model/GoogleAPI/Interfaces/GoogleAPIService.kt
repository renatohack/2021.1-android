package renatohack.minhascompras.Model.GoogleAPI.Interfaces

import renatohack.minhascompras.Model.GoogleAPI.Classes.ResponseGoogleAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleAPIService {

    //Recurso "https://customsearch.googleapis.com/customsearch/v1"

    //Retonar resultado de pesquisa
    @GET("v1")
    suspend fun pesquisar(@Query("q") q : String,
                          @Query("key") key : String = "AIzaSyBCz-y6gbtVLlOtGMQsqrXPUZc48PLJ6yA",
                          @Query("cx") cx : String = "339c677b391e62a6f",
                          @Query("searchType") image : String = "image",
                          @Query("lr") lr : String = "br",
                          @Query("gl") gl : String = "br") : ResponseGoogleAPI



}