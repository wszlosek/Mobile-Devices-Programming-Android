package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun getCategories() {
    val call: Call<List<Category>> = categoryService.getCategories()
    call.enqueue(object : Callback<List<Category>> {
        override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
            val carts = response.body()
            Log.d("Categories", carts.toString())
        }

        override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getCategory(id: Int) {
    val call: Call<Category> = categoryService.getCategory(id)
    call.enqueue(object : Callback<Category> {
        override fun onResponse(call: Call<Category>, response: Response<Category>) {
            val category = response.body()
            Log.d("Category", category.toString())
        }

        override fun onFailure(call: Call<Category>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addCategory(categoryData: Category) {
    categoryService.createCategory(categoryData).enqueue(
        object : Callback<Category> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
            }
        }
    )
}

fun updateCategory(id: Int, categoryData: Category) {
    val call: Call<Category> = categoryService.updateCategory(id, categoryData)
    call.enqueue(object : Callback<Category> {
        override fun onResponse(call: Call<Category>, response: Response<Category>) {
        }

        override fun onFailure(call: Call<Category>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteCategory(id: Int) {
    val call: Call<Category> = categoryService.deleteCategory(id)
    call.enqueue(object : Callback<Category> {
        override fun onResponse(call: Call<Category>, response: Response<Category>) {
        }

        override fun onFailure(call: Call<Category>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
}