package com.shop

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shop.plugins.*
import com.shop.tables.*
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
        SchemaUtils.create(ShopLocalizationTable)
        SchemaUtils.create(ColorTable)
        SchemaUtils.create(CategoryTable)
        SchemaUtils.create(CartTable)
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)

}
