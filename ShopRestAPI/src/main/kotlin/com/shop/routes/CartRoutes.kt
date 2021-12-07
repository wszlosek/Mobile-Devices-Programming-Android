package com.shop.routes

import com.shop.models.*
import com.shop.tables.CartTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.cartSerialization() {
    getCart()
    postCart()
    putCart()
    deleteCart()
}

private fun Application.getCart() {
    routing {
        get("/cart") {
            var carts = mutableListOf<Cart>()
            transaction {
                carts = CartTable.selectAll().map { it.toCart() }.toMutableList()
            }
            call.respond(carts)
        }

        get("/cart/{id}") {
            val id: Int = call.parameters["id"]!!.toInt()
            var cart = Cart()
            transaction {
                cart = CartTable.select { CartTable.id eq id }.map { it.toCart() }.first()
            }
            call.respond(cart)
        }
    }
}

private fun Application.postCart() {
    routing {
        post("/cart") {
            val color = call.receive<Cart>()
            addCartToDatabase(color)
            call.respondText("Color stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putCart() {
    routing {
        put("/cart/{id}") {
            val id = call.parameters["id"]
            transaction {
                CartTable.deleteWhere { CartTable.id eq id!!.toInt() }
            }

            val rec = call.receive<Cart>()
            addCartToDatabase(rec)
        }
    }
}

private fun Application.deleteCart() {
    routing {
        delete("/cart") {
            transaction {
                SchemaUtils.drop(CartTable)
                SchemaUtils.create(CartTable)
            }
        }

        delete("/cart/{id}") {
            val id = call.parameters["id"]
            transaction {
                CartTable.deleteWhere { CartTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addCartToDatabase(cart: Cart) {
    transaction {
        CartTable.insert {
            it[userId] = cart.userId
        }
    }
}
