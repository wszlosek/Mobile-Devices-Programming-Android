package pl.edu.uj.ii.szlosek.shop

import okhttp3.OkHttpClient
import pl.edu.uj.ii.szlosek.shop.builds.*
import pl.edu.uj.ii.szlosek.shop.services.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {
    private fun buildService(classToCreate: Class<*>): Any? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://4e6c-2a02-a31a-e045-8500-b122-760d-77b8-5c24.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(classToCreate)
    }

    fun buildAllServices(){
        cartService = buildService(CartService::class.java) as CartService
        categoryService = buildService(CategoryService::class.java) as CategoryService
        colorService = buildService(ColorService::class.java) as ColorService
        productService = buildService(ProductService::class.java) as ProductService
        shopLocalizationService = buildService(ShopLocalizationService::class.java) as ShopLocalizationService
        userService = buildService(UserService::class.java) as UserService
    }
}