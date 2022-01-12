package com.shop.models

import com.shop.tables.ShopLocalizationTable
import org.jetbrains.exposed.sql.ResultRow

data class ShopLocalization(
    val id: Int, val name: String, val street: String,
    val city: String, val country: String
) {
    constructor() : this(0, "", "", "", "")
}

const val shopSign = "/shop"
const val shopIdSign = "/shop/{id}"

fun ResultRow.toShopLocalization() = ShopLocalization(
    id = this[ShopLocalizationTable.id],
    name = this[ShopLocalizationTable.name],
    street = this[ShopLocalizationTable.street],
    city = this[ShopLocalizationTable.city],
    country = this[ShopLocalizationTable.country]
)
