package pl.edu.uj.ii.szlosek.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity

class Adapter(context: Context): BaseAdapter() {
    private val mContext: Context
    private val productsNames = arrayListOf<String>(
        "Product 1", "Product 2", "Product 3",
        "Product 4", "Product 5"
    )

    init {
        mContext = context
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
        nameTextView.text = productsNames.get(p0)

        val buttons = rowProducts.findViewById<Button>(R.id.buyButton)
        buttons.setOnClickListener {
            val intent = Intent(mContext, Cart::class.java).apply {
                putExtra("nameOfProduct",nameTextView.text)
            }
            mContext.startActivity(intent)
        }

        return rowProducts
    }


}