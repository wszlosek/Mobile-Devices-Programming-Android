package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.ShopLocalization
import retrofit2.Call
import retrofit2.http.*

interface ShopLocalizationService {
    @GET("shop")
    fun getShops() : Call<List<ShopLocalization>>

    @GET("shop/{id}")
    fun getShop(@Path("id") id: Int) : Call<ShopLocalization>

    @POST("shop")
    fun createShop(@Body shop: ShopLocalization) : Call<ShopLocalization>

    @PUT("shop/{id}")
    fun updateShop(@Path("id") id: Int, @Body shop: ShopLocalization) : Call<ShopLocalization>

    @DELETE("shop/{id}")
    fun deleteShop(@Path("id") id: Int) : Call<ShopLocalization>
}