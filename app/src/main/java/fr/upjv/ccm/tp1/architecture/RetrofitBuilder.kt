package fr.upjv.ccm.tp1.architecture

import com.google.gson.GsonBuilder
import fr.upjv.ccm.tp1.remote.BoardGameEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.boardgameatlas.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()


    fun getBoardgameQuote(): BoardGameEndpoint = retrofit.create(BoardGameEndpoint::class.java)
}