package pl.edu.uj.ii.szlosek.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.OkHttpClient
import pl.edu.uj.ii.szlosek.shop.models.Product
import pl.edu.uj.ii.szlosek.shop.services.ProductService
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var service: ProductService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  buildService()
      //  getProductsSync()
    }

    private fun buildService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://7f63-2a02-a31a-e045-8500-5047-752-51f0-3abe.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        service = retrofit.create(ProductService::class.java)
        print(service.getProducts())
    }

    private fun getProductsSync() {
        val call : Call<List<Product>> = service.getProducts()
        val response : Response<List<Product>> = call.execute()
        //wait for response in this place
        if(response.isSuccessful) {
            //do something with data
            val data : List<Product>? = response.body()
            println("xdddddddddd:")
            Log.d("xD", data.toString());
            print(data)
        }
        else {
            //do some fail action
            val error = response.errorBody()
            val message = response.message()
        }
        print(call)
        print(response)
    }
}