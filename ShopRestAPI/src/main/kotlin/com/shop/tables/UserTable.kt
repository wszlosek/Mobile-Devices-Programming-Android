package com.shop.tables

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
    val id = integer("id")
    override val primaryKey = PrimaryKey(id)
    val firstName = varchar("firstName", 50)
    val surname = varchar("surname", 50)
    val localization = varchar("localization", 50)
}
