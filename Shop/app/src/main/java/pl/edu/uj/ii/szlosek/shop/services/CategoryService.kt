package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Category
import retrofit2.http.*

interface CategoryService {
    @GET("category")
    suspend fun getCategories() : List<Category>

    @GET("category/{id}")
    suspend fun getCategory(@Path("id") id: Int) : Category

    @POST("category")
    suspend fun createCategory(@Body category: Category)

    @PUT("category/{id}")
    suspend fun updateCategory(@Path("id") id: Int, @Body cart: Category)

    @DELETE("category/{id}")
    suspend fun deleteCategory(@Path("id") id: Int)
}