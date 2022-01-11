package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.Cart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
fun getCarts() {
    val call: Call<List<Cart>> = cartService.getCarts()
    call.enqueue(object : Callback<List<Cart>> {
        override fun onResponse(call: Call<List<Cart>>, response: Response<List<Cart>>) {
            val carts = response.body()
            Log.d("Carts", carts.toString())
        }

        override fun onFailure(call: Call<List<Cart>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getCart(id: Int) {
    val call: Call<Cart> = cartService.getCart(id)
    call.enqueue(object : Callback<Cart> {
        override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
            val cart = response.body()
            Log.d("Cart", cart.toString())
        }

        override fun onFailure(call: Call<Cart>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addCart(cartData: Cart) {
    cartService.createCart(cartData).enqueue(
        object : Callback<Cart> {
            override fun onFailure(call: Call<Cart>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
            }
        }
    )
}

fun updateCart(id: Int, productData: Cart) {
    val call: Call<Cart> = cartService.updateCart(id, productData)
    call.enqueue(object : Callback<Cart> {
        override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
        }

        override fun onFailure(call: Call<Cart>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteCart(id: Int) {
    val call: Call<Cart> = cartService.deleteCart(id)
    call.enqueue(object : Callback<Cart> {
        override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
        }

        override fun onFailure(call: Call<Cart>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
}

 */

suspend fun getCarts(): List<Cart> {
    return cartService.getCarts()
}

suspend fun getCart(id: Int): Cart {
    return cartService.getCart(id)
}

suspend fun createCart(cart: Cart) {
    return cartService.createCart(cart)
}

suspend fun updateCart(id: Int, cart: Cart) {
    return cartService.updateCart(id, cart)
}

suspend fun deleteCart(id: Int) {
    return cartService.deleteCart(id)
}