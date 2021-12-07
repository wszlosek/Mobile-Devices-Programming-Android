package com.shop.models

import com.shop.tables.CategoryTable
import org.jetbrains.exposed.sql.ResultRow

data class Category(val id: Int, val name: String) {
    constructor() : this(0, "")
}

fun ResultRow.toCategory() = Category(
    id = this[CategoryTable.id],
    name = this[CategoryTable.name]
)