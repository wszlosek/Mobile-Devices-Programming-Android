package com.shop.tables

import org.jetbrains.exposed.sql.Table

object ProductTable : Table("products") {
    val id = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 50)
    val categoryId = integer("category").references(CategoryTable.id)
    val size = varchar("size", 50)
    val colorId = integer("color").references(ColorTable.id)
    val price = float("price")
    val description = varchar("description", 80)
}
