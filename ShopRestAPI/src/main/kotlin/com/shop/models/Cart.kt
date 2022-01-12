package com.shop.models

import com.shop.tables.CartTable
import org.jetbrains.exposed.sql.ResultRow

data class Cart(val id: Int, val userId: Int, val productId: Int, val amount: Int) {
    constructor() : this(0, 0, 0, 0)
}

const val cartSign = "/cart"
const val cartIdSign = "/cart/{id}"

fun ResultRow.toCart() = Cart(
    id = this[CartTable.id],
    userId = this[CartTable.userId],
    productId = this[CartTable.productId],
    amount = this[CartTable.amount]
)