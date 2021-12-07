package com.shop

import com.shop.models.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shop.plugins.*
import com.shop.tables.ColorTable
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
        SchemaUtils.create(ShopLocalizationTable)
        SchemaUtils.create(ColorTable)

      //  users.addAll(UserTable.select { UserTable.id eq UserTable.id }.map { it.toUser() })
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)

}
