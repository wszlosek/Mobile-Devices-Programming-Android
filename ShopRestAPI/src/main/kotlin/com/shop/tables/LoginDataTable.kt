package com.shop.tables

import org.jetbrains.exposed.sql.Table

object LoginDataTable : Table("login data") {
    val id = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
    val login = varchar("login", 50)
    val password = varchar("password", 50)
}