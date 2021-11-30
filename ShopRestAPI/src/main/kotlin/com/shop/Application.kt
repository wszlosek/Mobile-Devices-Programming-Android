package com.shop

import com.shop.models.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shop.plugins.*
import com.shop.tables.ProductTable
import com.shop.tables.ShopLocalizationTable
import com.shop.tables.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun main() {

    Database.connect("jdbc:sqlite:shopdb.sqlite", "org.sqlite.JDBC")
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

    transaction {
        SchemaUtils.create(ProductTable)
        SchemaUtils.create(UserTable)

        productStorrage.addAll(ProductTable.select { ProductTable.id eq ProductTable.id }.map { it.toProduct() })
        userStorrage.addAll(UserTable.select { UserTable.id eq UserTable.id }.map { it.toUser() })
        shopsLocalizations.addAll(ShopLocalizationTable.select { ShopLocalizationTable.id eq ShopLocalizationTable.id }.map { it.toShopLocalization() })
    }

    println(productStorrage)
    println(userStorrage)
    println(shopsLocalizations)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)

}
