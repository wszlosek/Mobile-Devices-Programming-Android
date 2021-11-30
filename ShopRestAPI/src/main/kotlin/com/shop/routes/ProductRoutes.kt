package com.shop.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.models.Product
import com.shop.models.productStorrage
import com.shop.tables.ProductTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.productSerialization() {
    getProduct()
    postProduct()
    putProduct()
    deleteProduct()
}

private fun Application.getProduct() {
    routing {
        get("/product") {
            call.respond(productStorrage)
        }

        get("/product/{id}") {
            val id = call.parameters["id"]
            val product: Product = productStorrage.find { it.id == id!!.toInt() }!!
            call.respond(product)
        }
    }
}

private fun Application.postProduct() {
    routing {
        post("/product") {
            val product = call.receive<Product>()
            productStorrage.add(product)
            addProductToDatabase(product)

            call.respondText("Product stored correctly", status = HttpStatusCode.Created)
            println(productStorrage)
        }
    }
}

private fun Application.putProduct() {
    routing {
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
    }
}

private fun Application.deleteProduct() {
    routing {
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

private fun addProductToDatabase(product: Product) {
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
