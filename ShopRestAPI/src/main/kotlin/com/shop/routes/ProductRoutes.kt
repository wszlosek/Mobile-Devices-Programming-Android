package com.shop.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.models.Product
import com.shop.models.productIdSign
import com.shop.models.productSign
import com.shop.models.toProduct
import com.shop.tables.ProductTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.productSerialization() {
    getProduct()
    postProduct()
    putProduct()
    deleteProduct()
}

private fun Application.getProduct() {
    routing {
        get(productSign) {
            var products = mutableListOf<Product>()
            transaction {
                products = ProductTable.selectAll().map { it.toProduct() }.toMutableList()
            }
            call.respond(products)
        }

        get(productIdSign) {
            val id: Int = call.parameters["id"]!!.toInt()
            var product = Product()
            transaction {
                product = ProductTable.select { ProductTable.id eq id }.map { it.toProduct() }.first()
            }
            call.respond(product)
        }
    }
}

private fun Application.postProduct() {
    routing {
        post(productSign) {
            val product = call.receive<Product>()
            addProductToDatabase(product)
            call.respondText("Product stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putProduct() {
    routing {
        put(productIdSign) {
            val id = call.parameters["id"]
            val product = call.receive<Product>()
            transaction {
                ProductTable.update({ ProductTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[name] = product.name
                        it[categoryId] = product.categoryId
                        it[size] = product.size
                        it[colorId] = product.colorId
                        it[price] = product.price
                        it[description] = product.description
                    }
                }
            }
        }
    }
}

private fun Application.deleteProduct() {
    routing {
        delete(productSign) {
            transaction {
                SchemaUtils.drop(ProductTable)
                SchemaUtils.create(ProductTable)
            }
        }

        delete("/product/{id}") {
            val id = call.parameters["id"]
            transaction {
                ProductTable.deleteWhere { ProductTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addProductToDatabase(product: Product) {
    transaction {
        ProductTable.insert {
            it[name] = product.name
            it[categoryId] = product.categoryId
            it[size] = product.size
            it[colorId] = product.colorId
            it[price] = product.price
            it[description] = product.description
        }
    }
}
