package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.ShopLocalization
import retrofit2.Call
import retrofit2.http.*

interface ShopLocalizationService {
    @GET("shop")
    suspend fun getShops() : List<ShopLocalization>

    @GET("shop/{id}")
    suspend fun getShop(@Path("id") id: Int) : ShopLocalization

    @POST("shop")
    suspend fun createShop(@Body shop: ShopLocalization)

    @PUT("shop/{id}")
    suspend fun updateShop(@Path("id") id: Int, @Body shop: ShopLocalization)

    @DELETE("shop/{id}")
    suspend fun deleteShop(@Path("id") id: Int)
}