package com.shop.models

import com.shop.tables.CategoryTable
import org.jetbrains.exposed.sql.ResultRow

data class Category(val id: Int, val name: String) {
    constructor() : this(0, "")
}

const val categorySign = "/category"
const val categoryIdSign = "/category/{id}"

fun ResultRow.toCategory() = Category(
    id = this[CategoryTable.id],
    name = this[CategoryTable.name]
)