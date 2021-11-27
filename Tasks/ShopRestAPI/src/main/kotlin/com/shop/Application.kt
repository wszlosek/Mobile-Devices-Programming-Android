package com.shop

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shop.plugins.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.math.BigDecimal

val productStorrage = mutableListOf<Product>()

fun main() {

    productStorrage.addAll(
        arrayOf(
            Product(1, "xd", "Car", 30000, "blabla")
        )
    )

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}

data class Product(val id: Int, val name: String, val categoryName: String,
                   val price: Int, val description: String)


