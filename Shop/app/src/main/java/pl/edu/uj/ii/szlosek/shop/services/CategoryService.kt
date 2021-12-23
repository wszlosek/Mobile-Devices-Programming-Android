package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Category
import retrofit2.Call
import retrofit2.http.*

interface CategoryService {
    @GET("category")
    fun getCategories() : Call<List<Category>>

    @GET("category/{id}")
    fun getCategory(@Path("id") id: Int) : Call<Category>

    @POST("category")
    fun createCategory(@Body category: Category) : Call<Category>

    @PUT("category/{id}")
    fun updateCategory(@Path("id") id: Int, @Body cart: Category) : Call<Category>

    @DELETE("category/{id}")
    fun deleteCategory(@Path("id") id: Int) : Call<Category>
}