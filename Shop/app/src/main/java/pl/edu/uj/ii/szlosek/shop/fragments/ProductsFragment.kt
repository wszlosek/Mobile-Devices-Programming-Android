package pl.edu.uj.ii.szlosek.shop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.navigation.findNavController
import pl.edu.uj.ii.szlosek.shop.Adapter
import pl.edu.uj.ii.szlosek.shop.R

class ProductsFragment : Fragment(R.layout.fragment_products) {
    override fun onStart() {
        super.onStart()

        val buttonAboutMe = view?.findViewById<Button>(R.id.buttonToAboutMe)
        buttonAboutMe?.setOnClickListener {
            val action =
                ProductsFragmentDirections.actionProductsFragmentToAboutFragment()
            view!!.findNavController().navigate(action)
        }

        val listView = view?.findViewById<ListView>(R.id.productsList)
        listView?.adapter = context?.let { Adapter(it) }

        val buttonBuy = view?.findViewById<Button>(R.id.buttonBuy)
        buttonBuy?.setOnClickListener {
            val action =
                ProductsFragmentDirections.actionProductsFragmentToCartFragment()
            view!!.findNavController().navigate(action)
        }
    }
}