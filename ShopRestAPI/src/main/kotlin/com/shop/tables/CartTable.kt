package com.shop.tables

import org.jetbrains.exposed.sql.Table

object CartTable : Table("carts") {
    val id = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
    val userId = integer("userId")
}