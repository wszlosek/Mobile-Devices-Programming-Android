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
        get(shopSign) {
            var localizations = mutableListOf<ShopLocalization>()
            transaction {
                localizations = ShopLocalizationTable.selectAll().map { it.toShopLocalization() }.toMutableList()
            }
            call.respond(localizations)
        }

        get(shopIdSign) {
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
        post(shopSign) {
            val shop = call.receive<ShopLocalization>()
            addLocalizationToDatabase(shop)
            call.respondText("Shop localization stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putShopLocalization() {
    routing {
        put(shopIdSign) {
            val id = call.parameters["id"]
            val shop = call.receive<ShopLocalization>()
            transaction {
                ShopLocalizationTable.update({ ShopLocalizationTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[name] = shop.name
                        it[street] = shop.street
                        it[city] = shop.city
                        it[country] = shop.country
                    }
                }
            }
        }
    }
}

private fun Application.deleteShopLocalization() {
    routing {
        delete(shopSign) {
            transaction {
                SchemaUtils.drop(ShopLocalizationTable)
                SchemaUtils.create(ShopLocalizationTable)
            }
        }

        delete(shopIdSign) {
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
            it[street] = shop.street
            it[city] = shop.city
            it[country] = shop.country
        }
    }
}
