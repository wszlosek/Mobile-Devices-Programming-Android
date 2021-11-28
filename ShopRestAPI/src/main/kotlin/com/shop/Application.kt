package com.shop

import com.shop.ProductTable.category
import com.shop.ProductTable.description
import com.shop.ProductTable.id
import com.shop.ProductTable.name
import com.shop.ProductTable.price
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shop.plugins.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

val productStorrage = mutableListOf<Product>()

fun main() {

    Database.connect("jdbc:sqlite:shopdb.sqlite", "org.sqlite.JDBC")
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

    transaction {
        SchemaUtils.create(ProductTable)

        ProductTable.insert {
            it[id] = 4
            it[name] = "Toyotka Yaris"
            it[category] = "Car"
            it[price] = 5000
            it[description] = ":)))"
        }

        productStorrage.addAll(ProductTable.select { ProductTable.id eq ProductTable.id }.map { it.toProduct() })
    }

    println(productStorrage)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)

}

fun ResultRow.toProduct() = Product(
    id = this[id],
    name = this[name],
    category = this[category],
    price = this[price],
    description = this[description]
)

data class Product(
    val id: Int, val name: String, val category: String,
    val price: Int, val description: String
)

object ProductTable : Table("products") {
    val id = integer("id").primaryKey()
    val name = varchar("name", 50)
    val category = varchar("category", 50)
    val price = integer("price")
    val description = varchar("description", 80)
}
