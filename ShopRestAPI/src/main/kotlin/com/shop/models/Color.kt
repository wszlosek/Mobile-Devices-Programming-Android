package com.shop.models

import com.shop.tables.ColorTable
import org.jetbrains.exposed.sql.ResultRow

data class Color(val id: Int, val name: String) {
    constructor() : this(0, "")
}

fun ResultRow.toColor() = Color(
    id = this[ColorTable.id],
    name = this[ColorTable.name],
)