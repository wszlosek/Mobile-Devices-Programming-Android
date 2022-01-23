package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.LoginData
import retrofit2.http.*

interface LoginDataService {
    @GET("logindata")
    suspend fun getLoginData() : List<LoginData>

    @GET("logindata/{id}")
    suspend fun getLoginData(@Path("id") id: Int) : LoginData

    @POST("logindata")
    suspend fun createLoginData(@Body loginData: LoginData)

    @PUT("logindata/{id}")
    suspend fun updateLoginData(@Path("id") id: Int, @Body loginData: LoginData)

    @DELETE("logindata/{id}")
    suspend fun deleteLoginData(@Path("id") id: Int)
}