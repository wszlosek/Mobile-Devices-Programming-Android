package pl.edu.uj.ii.szlosek.shop.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.edu.uj.ii.szlosek.shop.Cart
import pl.edu.uj.ii.szlosek.shop.ProductId
import pl.edu.uj.ii.szlosek.shop.R

class CartFragment : Fragment(R.layout.fragment_cart) {


    private lateinit var selectedProductName: TextView

    override fun onStart() {
        super.onStart()

        selectedProductName.text = "hehe"
        //selectedProductName.text = intent.getStringExtra("nameOfProduct")?.replace(",", "")
    }

}