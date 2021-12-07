package com.shop.tables

import org.jetbrains.exposed.sql.Table

object ShopLocalizationTable : Table("shops localizations") {
    val id = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 50)
    val street = varchar("street", 50)
    val city = varchar("city", 50)
    val country = varchar("country", 50)
}