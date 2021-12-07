package com.shop.tables

import org.jetbrains.exposed.sql.Table

object CartTable : Table("carts") {
    val id = integer("id")
    override val primaryKey = PrimaryKey(id)
    val userId = integer("userId").references(UserTable.id)
    val productId = integer("productId").references(ProductTable.id)
    val amount = integer("amount")
}