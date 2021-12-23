package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getProducts() {
    val call: Call<List<Product>> = productService.getProducts()
    call.enqueue(object : Callback<List<Product>> {
        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
            val products = response.body()
            Log.d("Products", products.toString())
        }

        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getProduct(id: Int) {
    val call: Call<Product> = productService.getProduct(id)
    call.enqueue(object : Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
            val product = response.body()
            Log.d("Product", product.toString())
        }

        override fun onFailure(call: Call<Product>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addProduct(productData: Product) {
    productService.createProduct(productData).enqueue(
        object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
            }
        }
    )
}

fun updateProduct(id: Int, productData: Product) {
    val call: Call<Product> = productService.updateProduct(id, productData)
    call.enqueue(object : Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
        }

        override fun onFailure(call: Call<Product>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteProduct(id: Int) {
    val call: Call<Product> = productService.deleteProduct(id)
    call.enqueue(object : Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
        }

        override fun onFailure(call: Call<Product>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
}