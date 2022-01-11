package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    // only write :(

    /*
    @GET("user")
    fun getUsers() : Call<List<User>>

    @GET("user/{id}")
    fun getUser(@Path("id") id: Int) : Call<User>

    @POST("user")
    fun createUser(@Body user: User) : Call<User>

    @PUT("user/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User) : Call<User>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int) : Call<User> */

    @GET("user")
    suspend fun getUsers() : List<User>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: Int) : User

    @POST("user")
    suspend fun createUser(@Body user: User)

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User)

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}