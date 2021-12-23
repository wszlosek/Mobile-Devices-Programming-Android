package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Color
import retrofit2.Call
import retrofit2.http.*

interface ColorService {
    @GET("color")
    fun getColors() : Call<List<Color>>

    @GET("color/{id}")
    fun getColor(@Path("id") id: Int) : Call<Color>

    @POST("color")
    fun createColor(@Body category: Color) : Call<Color>

    @PUT("color/{id}")
    fun updateColor(@Path("id") id: Int, @Body cart: Color) : Call<Color>

    @DELETE("color/{id}")
    fun deleteColor(@Path("id") id: Int) : Call<Color>
}