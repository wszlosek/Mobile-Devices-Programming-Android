package com.shop.plugins

import com.shop.Product
import com.shop.productStorrage
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
    productSerialization()
}

fun Application.productSerialization() {
    routing {
        get("/product/{id}") {
            val id = call.parameters["id"]
            val product: Product = productStorrage.find { it.id == id!!.toInt() }!!
            call.respond(product)
        }

        post("/product") {
            val product = call.receive<Product>()
            productStorrage.add(product)
            call.respondText("Product stored correctly", status = HttpStatusCode.Created)
        }

        put("/product/{id}") {
            val product = call.receive<Product>()
        }
    }
}
