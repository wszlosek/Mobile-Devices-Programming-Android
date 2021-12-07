package com.shop.tables

import org.jetbrains.exposed.sql.Table

object ProductTable : Table("products") {
    val id = integer("id")
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 50)
    val categoryId = integer("category")
    val colorId = integer("color").references(ColorTable.id)
    val price = integer("price")
    val description = varchar("description", 80)
}
