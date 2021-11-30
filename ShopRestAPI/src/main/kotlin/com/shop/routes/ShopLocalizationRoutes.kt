package com.shop.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.models.Product
import com.shop.models.ShopLocalization
import com.shop.models.productStorrage
import com.shop.models.shopsLocalizations
import com.shop.tables.ProductTable
import com.shop.tables.ShopLocalizationTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.shopLocalizationSerialization() {
    getShopLocalization()
    postShopLocalization()
    putShopLocalization()
    deleteShopLocalization()
}

private fun Application.getShopLocalization() {
    routing {
        get("/shop") {
            call.respond(shopsLocalizations)
        }

        get("/shop/{id}") {
            val id = call.parameters["id"]
            val shop: ShopLocalization = shopsLocalizations.find { it.id == id!!.toInt() }!!
            call.respond(shop)
        }
    }
}

private fun Application.postShopLocalization() {
    routing {
        post("/shop") {
            val shop = call.receive<ShopLocalization>()
            shopsLocalizations.add(shop)
            addLocalizationToDatabase(shop)

            call.respondText("Shop localization stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putShopLocalization() {
    routing {
        put("/shop/{id}") {
            val id = call.parameters["id"]
            val shop: ShopLocalization = shopsLocalizations.find { it.id == id!!.toInt() }!!

            shopsLocalizations.remove(shop)
            transaction {
                ShopLocalizationTable.deleteWhere { ShopLocalizationTable.id eq id!!.toInt() }
            }

            val rec = call.receive<ShopLocalization>()
            shopsLocalizations.add(rec)
            addLocalizationToDatabase(rec)
        }
    }
}

private fun Application.deleteShopLocalization() {
    routing {
        delete("/shop") {
            shopsLocalizations.clear()
            transaction {
                SchemaUtils.drop(ShopLocalizationTable)
                SchemaUtils.create(ShopLocalizationTable)
            }
        }

        delete("/shop/{id}") {
            val id = call.parameters["id"]
            val shop: ShopLocalization = shopsLocalizations.find { it.id == id!!.toInt() }!!

            shopsLocalizations.remove(shop)
            transaction {
                ShopLocalizationTable.deleteWhere { ShopLocalizationTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addLocalizationToDatabase(shop: ShopLocalization) {
    transaction {
        ShopLocalizationTable.insert {
            it[id] = shop.id
            it[name] = shop.name
            it[city] = shop.city
            it[country] = shop.country
        }
    }
}
