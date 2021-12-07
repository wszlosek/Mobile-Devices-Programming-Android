package com.shop.routes

import com.shop.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.tables.ShopLocalizationTable
import org.jetbrains.exposed.sql.*
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
            var localizations = mutableListOf<ShopLocalization>()
            transaction {
                localizations = ShopLocalizationTable.selectAll().map { it.toShopLocalization() }.toMutableList()
            }
            call.respond(localizations)
        }

        get("/shop/{id}") {
            val id: Int = call.parameters["id"]!!.toInt()
            var localization = ShopLocalization()
            transaction {
                localization = ShopLocalizationTable.select { ShopLocalizationTable.id eq id }.map { it.toShopLocalization() }.first()
            }
            call.respond(localization)
        }
    }
}

private fun Application.postShopLocalization() {
    routing {
        post("/shop") {
            val shop = call.receive<ShopLocalization>()
            addLocalizationToDatabase(shop)
            call.respondText("Shop localization stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putShopLocalization() {
    routing {
        put("/shop/{id}") {
            val id = call.parameters["id"]
            transaction {
                ShopLocalizationTable.deleteWhere { ShopLocalizationTable.id eq id!!.toInt() }
            }

            val rec = call.receive<ShopLocalization>()
            addLocalizationToDatabase(rec)
        }
    }
}

private fun Application.deleteShopLocalization() {
    routing {
        delete("/shop") {
            transaction {
                SchemaUtils.drop(ShopLocalizationTable)
                SchemaUtils.create(ShopLocalizationTable)
            }
        }

        delete("/shop/{id}") {
            val id = call.parameters["id"]
            transaction {
                ShopLocalizationTable.deleteWhere { ShopLocalizationTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addLocalizationToDatabase(shop: ShopLocalization) {
    transaction {
        ShopLocalizationTable.insert {
            it[name] = shop.name
            it[city] = shop.city
            it[country] = shop.country
        }
    }
}
