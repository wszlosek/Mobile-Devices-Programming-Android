package com.shop.models

import com.shop.tables.ProductTable
import org.jetbrains.exposed.sql.ResultRow

val productStorrage = mutableListOf<Product>()

data class Product(
    val id: Int, val name: String, val category: String,
    val price: Int, val description: String
)

fun ResultRow.toProduct() = Product(
    id = this[ProductTable.id],
    name = this[ProductTable.name],
    category = this[ProductTable.category],
    price = this[ProductTable.price],
    description = this[ProductTable.description]
)
