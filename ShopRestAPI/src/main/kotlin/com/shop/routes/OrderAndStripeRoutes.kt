package com.shop.routes

import com.google.gson.Gson
import com.shop.models.*
import com.shop.tables.ProductTable
import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.orderSerialization() {
    getOrder()
}

private fun Application.getOrder() {
    routing {
        get("/create-payment-intent/{customer_id}/{product_id}") {
            try {
                val customer_id = call.parameters["customer_id"]
                val product_id = call.parameters["product_id"]
                if (customer_id != null && product_id != null) {
                    val cart: Int = customer_id.toInt()
                    var sProduct: Product = Product()

                    transaction {
                        for (product in ProductTable.selectAll()) {
                            if (product.toProduct().id == product_id.toInt()) {
                                sProduct = product.toProduct()
                            }
                        }
                    }

                    println("mmm " + sProduct.price.toString())
                    val price: Float = sProduct.price
                    Stripe.apiKey = Secrets.secretServerKey

                    val params = PaymentIntentCreateParams.builder()
                        .setAmount((price*100).toLong())
                        .setCurrency("pln")
                        .build()

                    val intent = PaymentIntent.create(params)
                    call.respond(Gson().toJson(intent.clientSecret))
                }
            } catch(e : Exception) {
                call.respond(HttpStatusCode.BadRequest, e.message.toString())
            }
        }
    }
}
