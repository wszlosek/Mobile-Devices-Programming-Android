package pl.edu.uj.ii.szlosek.shop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.edu.uj.ii.szlosek.shop.builds.getProducts
import pl.edu.uj.ii.szlosek.shop.models.Product

class Adapter(context: Context): BaseAdapter() {
    private val mContext: Context = context
    var x = getProductsToList()
    private val productsNames = generateProducts()

    private fun generateProducts(): ArrayList<Product> {
        val result: ArrayList<Product> = mutableListOf<Product>() as ArrayList<Product>
        for (i in x.indices) {
            result.add(x[i])
        }

        return result
    }

    override fun getCount(): Int {
        return productsNames.size
    }

    override fun getItem(p0: Int): Any {
        return productsNames[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowProducts = layoutInflater.inflate(
            R.layout.row_products, p2, false
        )

        val nameTextView = rowProducts.findViewById<TextView>(R.id.productName)
        nameTextView.text = productsNames[p0].toString()

        val buttons = rowProducts.findViewById<Button>(R.id.buyButton)
        buttons.setOnClickListener {
            val intent = Intent(mContext, Cart::class.java).apply {
                putExtra("nameOfProduct",nameTextView.text)
            }
            mContext.startActivity(intent)
        }

        return rowProducts
    }

    private fun getProductsToList(): List<Product> {
        val x: List<Product>
        runBlocking {
            withContext(Dispatchers.IO) {
                x = getProducts()
            }
        }
        return x
    }

}