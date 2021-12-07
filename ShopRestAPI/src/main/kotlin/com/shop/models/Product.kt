package com.shop.models

import com.shop.tables.ProductTable
import org.jetbrains.exposed.sql.ResultRow

data class Product(
    val id: Int, val name: String, val categoryId: Int,
    val colorId: Int, val price: Int, val description: String
) {
    constructor() : this(0, "", 0, 0, 0, "")
}

fun ResultRow.toProduct() = this[ProductTable.colorId]?.let {
    Product(
        id = this[ProductTable.id],
        name = this[ProductTable.name],
        categoryId = this[ProductTable.categoryId],
        colorId = it,
        price = this[ProductTable.price],
        description = this[ProductTable.description]
    )
}
