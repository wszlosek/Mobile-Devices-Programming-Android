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

class AboutFragment : Fragment(R.layout.fragment_about) {
    override fun onStart() {
        super.onStart()

        val buttonBack = view?.findViewById<Button>(R.id.buttonBack)
        buttonBack?.setOnClickListener {
            val action =
                AboutFragmentDirections.actionAboutFragmentToProductsFragment()
            view!!.findNavController().navigate(action)
        }

        val buttonToMap = view?.findViewById<Button>(R.id.buttonAboutToMap)
        buttonToMap?.setOnClickListener {
            val action =
                AboutFragmentDirections.actionAboutFragmentToMapsFragment()
            view!!.findNavController().navigate(action)
        }
    }
}