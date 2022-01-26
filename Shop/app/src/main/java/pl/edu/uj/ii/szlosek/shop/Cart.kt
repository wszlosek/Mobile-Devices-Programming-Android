package pl.edu.uj.ii.szlosek.shop

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pl.edu.uj.ii.szlosek.shop.builds.stripeService
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.fragment.app.FragmentActivity
import com.google.android.material.internal.ContextUtils.getActivity
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class Cart : AppCompatActivity() {

    companion object {
        private const val TAG = "CheckoutActivity"
    }

    private lateinit var paymentIntentClientSecret: String
    private lateinit var paymentSheet: PaymentSheet

    private lateinit var payButton: Button

    private lateinit var selectedProductName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        selectedProductName = findViewById<TextView>(R.id.selectedProductName)
        selectedProductName.text = intent.getStringExtra("nameOfProduct")?.replace(",", "")

        payButton = findViewById(R.id.buttonBuy)
        payButton.setOnClickListener(::onPayClicked)
       // payButton.isEnabled = false

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        fetchPaymentIntent()
    }

    private fun fetchPaymentIntent() {
        stripeService
            .getPaymentIntent(UserId.id.toString(), ProductId.id.toString())
            .enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if(response.isSuccessful && response.body() != null) {
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                paymentIntentClientSecret = response.body()!!
                            }
                        }
                    } else {
                        Log.e("stripe_fail", response.message())
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("stripe", t.message.toString())
                }

            })
    }

    private fun showAlert(title: String, message: String? = null) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this,  message, Toast.LENGTH_LONG).show()
        }
    }

    private fun onPayClicked(view: View) {
        val configuration = PaymentSheet.Configuration("Example, Inc.")

        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
    }

    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult) {
        when (paymentResult) {
            is PaymentSheetResult.Completed -> {
                showToast("Payment complete!")
            }
            is PaymentSheetResult.Canceled -> {
                Log.i(TAG, "Payment canceled!")
            }
            is PaymentSheetResult.Failed -> {
                showAlert("Payment failed", paymentResult.error.localizedMessage)
            }
        }
    }
}