package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.Color
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
fun getColors() {
    val call: Call<List<Color>> = colorService.getColors()
    call.enqueue(object : Callback<List<Color>> {
        override fun onResponse(call: Call<List<Color>>, response: Response<List<Color>>) {
            val carts = response.body()
            Log.d("Categories", carts.toString())
        }

        override fun onFailure(call: Call<List<Color>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getColor(id: Int) {
    val call: Call<Color> = colorService.getColor(id)
    call.enqueue(object : Callback<Color> {
        override fun onResponse(call: Call<Color>, response: Response<Color>) {
            val color = response.body()
            Log.d("Color", color.toString())
        }

        override fun onFailure(call: Call<Color>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addColor(colorData: Color) {
    colorService.createColor(colorData).enqueue(
        object : Callback<Color> {
            override fun onFailure(call: Call<Color>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<Color>, response: Response<Color>) {
            }
        }
    )
}

fun updateColor(id: Int, colorData: Color) {
    val call: Call<Color> = colorService.updateColor(id, colorData)
    call.enqueue(object : Callback<Color> {
        override fun onResponse(call: Call<Color>, response: Response<Color>) {
        }

        override fun onFailure(call: Call<Color>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteColor(id: Int) {
    val call: Call<Color> = colorService.deleteColor(id)
    call.enqueue(object : Callback<Color> {
        override fun onResponse(call: Call<Color>, response: Response<Color>) {
        }

        override fun onFailure(call: Call<Color>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
}

 */

suspend fun getColors(): List<Color> {
    return colorService.getColors()
}

suspend fun getColor(id: Int): Color {
    return colorService.getColor(id)
}

suspend fun createColor(color: Color) {
    return colorService.createColor(color)
}

suspend fun updateColor(id: Int, color: Color) {
    return colorService.updateColor(id, color)
}

suspend fun deleteColor(id: Int) {
    return colorService.deleteColor(id)
}