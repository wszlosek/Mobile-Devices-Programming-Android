package pl.edu.uj.ii.szlosek.shop.builds

import android.util.Log
import pl.edu.uj.ii.szlosek.shop.models.ShopLocalization
import pl.edu.uj.ii.szlosek.shop.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
fun getShops() {
    val call: Call<List<ShopLocalization>> = shopLocalizationService.getShops()
    call.enqueue(object : Callback<List<ShopLocalization>> {
        override fun onResponse(call: Call<List<ShopLocalization>>, response: Response<List<ShopLocalization>>) {
            val shops = response.body()
            Log.d("ShopLocalizations", shops.toString())
        }

        override fun onFailure(call: Call<List<ShopLocalization>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getShopLocalization(id: Int) {
    val call: Call<ShopLocalization> = shopLocalizationService.getShop(id)
    call.enqueue(object : Callback<ShopLocalization> {
        override fun onResponse(call: Call<ShopLocalization>, response: Response<ShopLocalization>) {
            val shop = response.body()
            Log.d("ShopLocalization", shop.toString())
        }

        override fun onFailure(call: Call<ShopLocalization>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addShopLocalization(shopData: ShopLocalization) {
    shopLocalizationService.createShop(shopData).enqueue(
        object : Callback<ShopLocalization> {
            override fun onFailure(call: Call<ShopLocalization>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<ShopLocalization>, response: Response<ShopLocalization>) {
            }
        }
    )
}

fun updateShopLocalization(id: Int, shopData: ShopLocalization) {
    val call: Call<ShopLocalization> = shopLocalizationService.updateShop(id, shopData)
    call.enqueue(object : Callback<ShopLocalization> {
        override fun onResponse(call: Call<ShopLocalization>, response: Response<ShopLocalization>) {
        }

        override fun onFailure(call: Call<ShopLocalization>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteShopLocalization(id: Int) {
    val call: Call<ShopLocalization> = shopLocalizationService.deleteShop(id)
    call.enqueue(object : Callback<ShopLocalization> {
        override fun onResponse(call: Call<ShopLocalization>, response: Response<ShopLocalization>) {
        }

        override fun onFailure(call: Call<ShopLocalization>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
}

*/

suspend fun getShops(): List<ShopLocalization> {
    return shopLocalizationService.getShops()
}

suspend fun getShop(id: Int): ShopLocalization {
    return shopLocalizationService.getShop(id)
}

suspend fun createShop(shop: ShopLocalization) {
    return shopLocalizationService.createShop(shop)
}

suspend fun updateShop(id: Int, shop: ShopLocalization) {
    return shopLocalizationService.updateShop(id, shop)
}

suspend fun deleteShop(id: Int) {
    return shopLocalizationService.deleteShop(id)
}
