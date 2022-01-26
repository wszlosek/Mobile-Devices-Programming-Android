package pl.edu.uj.ii.szlosek.shop.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Stripe {
    @GET("create-payment-intent/{customer_id}/{product_id}")
    fun getPaymentIntent(@Path("customer_id") customerId: String, @Path("product_id") productId: String) : Call<String>
}