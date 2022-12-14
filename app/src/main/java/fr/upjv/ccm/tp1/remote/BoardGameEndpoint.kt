package fr.upjv.ccm.tp1.remote

import fr.upjv.ccm.tp1.model.Boardgame
import retrofit2.http.GET
import retrofit2.http.Query

interface BoardGameEndpoint {

    @GET("/")
    suspend fun getBoardgamesByName(@Query("name") name: String,
                                    @Query("pretty") pretty: String = "true",
                                    @Query("client_id") client_id: String = "tgwbrp6Van") : List<Boardgame>
}