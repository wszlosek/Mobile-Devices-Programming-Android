package com.shop.tables

import org.jetbrains.exposed.sql.Table

object ProductTable : Table("products") {
    val id = integer("id")
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 50)
    val category = varchar("category", 50)
    val price = integer("price")
    val description = varchar("description", 80)
}
