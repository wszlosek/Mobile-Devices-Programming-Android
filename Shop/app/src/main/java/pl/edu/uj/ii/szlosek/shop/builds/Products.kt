package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getProducts() {
    val call : Call<List<Product>> = service.getProducts()
    //do request at this point and do something else during waiting for response
    call.enqueue(object: Callback<List<Product>> {
        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
            //response returned at some moment
            val products = response.body()
            Log.d("xD", products.toString())
        }
        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            //fail returned at some moment
            Log.d("ech", ":(")
        }
    })
}

fun getProduct(id: Int) {
    val call : Call<Product> = service.getProduct(id)
    //do request at this point and do something else during waiting for response
    call.enqueue(object: Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
            //response returned at some moment
            val product = response.body()
            Log.d("xDDD", product.toString())
        }
        override fun onFailure(call: Call<Product>, t: Throwable) {
            //fail returned at some moment
            Log.d("ech", ":(((")
        }
    })
}

fun addProduct(productData: Product){
    service.createProduct(productData).enqueue(
        object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
            }
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val addedProduct = response.body()
                Log.d("xD2", addedProduct.toString())
            }
        }
    )
}

fun updateProduct(id: Int, productData: Product) {
    val call : Call<Product> = service.updateProduct(id, productData)
    call.enqueue(object: Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
            val newProduct = response.body()
          //  Log.d("xDDD", newProduct.toString())
        }
        override fun onFailure(call: Call<Product>, t: Throwable) {
          //  Log.d("ech", ":(((")
        }
    })
}

fun deleteProduct(id: Int) {
    val call : Call<Product> = service.deleteProduct(id)
    //do request at this point and do something else during waiting for response
    call.enqueue(object: Callback<Product> {
        override fun onResponse(call: Call<Product>, response: Response<Product>) {
            //response returned at some moment
            val product = response.body()
           // Log.d("xDDD", product.toString())
        }
        override fun onFailure(call: Call<Product>, t: Throwable) {
            //fail returned at some moment
           // Log.d("ech", ":(((")
        }
    })
}