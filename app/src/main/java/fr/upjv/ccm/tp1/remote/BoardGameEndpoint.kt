package fr.upjv.ccm.tp1.remote

import fr.upjv.ccm.tp1.model.Boardgame
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BoardGameEndpoint {

    @Headers(
        "Accept: application/json",
        "Accept-Encoding: identity",

    )
    @GET("search")
    suspend fun getBoardgamesByName(@Query("name") name: String,
                                    @Query("pretty") pretty: String = "true",
                                    @Query("limit") limit: String = "5",
                                    @Query("fields") fields: String = "name,description,price,thumb_url,type",
                                    @Query("client_id") client_id: String = "tgwbrp6Van") : List<Boardgame>
}