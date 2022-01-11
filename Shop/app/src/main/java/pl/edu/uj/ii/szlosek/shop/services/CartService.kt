package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Cart
import retrofit2.Call
import retrofit2.http.*

interface CartService {
    @GET("cart")
    suspend fun getCarts() : List<Cart>

    @GET("cart/{id}")
    suspend fun getCart(@Path("id") id: Int) : Cart

    @POST("cart")
    suspend fun createCart(@Body cart: Cart)

    @PUT("cart/{id}")
    suspend fun updateCart(@Path("id") id: Int, @Body cart: Cart)

    @DELETE("cart/{id}")
    suspend fun deleteCart(@Path("id") id: Int)
}