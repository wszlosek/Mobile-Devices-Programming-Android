package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Color
import retrofit2.http.*

interface ColorService {
    @GET("color")
    suspend fun getColors() : List<Color>

    @GET("color/{id}")
    suspend fun getColor(@Path("id") id: Int) : Color

    @POST("color")
    suspend fun createColor(@Body category: Color)

    @PUT("color/{id}")
    suspend fun updateColor(@Path("id") id: Int, @Body cart: Color)

    @DELETE("color/{id}")
    suspend fun deleteColor(@Path("id") id: Int)
}