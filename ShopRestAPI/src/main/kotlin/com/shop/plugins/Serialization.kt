package com.shop.plugins

import com.shop.Product
import com.shop.ProductTable
import com.shop.productStorrage
import com.shop.toProduct
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
    productSerialization()
}

fun addProductToDatabase(product: Product) {
    transaction {
        ProductTable.insert {
            it[id] = product.id
            it[name] = product.name
            it[category] = product.category
            it[price] = product.price
            it[description] = product.description
        }
    }
}

fun Application.productSerialization() {
    routing {
        get("/product") {
            call.respond(productStorrage)
        }

        get("/product/{id}") {
            val id = call.parameters["id"]
            val product: Product = productStorrage.find { it.id == id!!.toInt() }!!
            call.respond(product)
        }

        post("/product") {
            val product = call.receive<Product>()
            productStorrage.add(product)
            addProductToDatabase(product)

            call.respondText("Product stored correctly", status = HttpStatusCode.Created)
            println(productStorrage)
        }

        put("/product/{id}") {
            val id = call.parameters["id"]
            val product: Product = productStorrage.find { it.id == id!!.toInt() }!!

            productStorrage.remove(product)
            transaction {
                ProductTable.deleteWhere { ProductTable.id eq id!!.toInt() }
            }

            val rec = call.receive<Product>()
            productStorrage.add(rec)
            addProductToDatabase(rec)
        }

        delete("/product") {
            productStorrage.clear()
            transaction {
                SchemaUtils.drop(ProductTable)
                SchemaUtils.create(ProductTable)
            }
        }

        delete("/product/{id}") {
            val id = call.parameters["id"]
            val product: Product = productStorrage.find { it.id == id!!.toInt() }!!

            productStorrage.remove(product)
            transaction {
                ProductTable.deleteWhere { ProductTable.id eq id!!.toInt() }
            }
        }
    }
}
