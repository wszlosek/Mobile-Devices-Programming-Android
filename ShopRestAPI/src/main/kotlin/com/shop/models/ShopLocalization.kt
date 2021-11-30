package com.shop.models

import com.shop.tables.ShopLocalizationTable
import org.jetbrains.exposed.sql.ResultRow

val shopsLocalizations = mutableListOf<ShopLocalization>()

data class ShopLocalization(
    val id: Int, val name: String,
    val city: String, val country: String
)

fun ResultRow.toShopLocalization() = ShopLocalization(
    id = this[ShopLocalizationTable.id],
    name = this[ShopLocalizationTable.name],
    city = this[ShopLocalizationTable.city],
    country = this[ShopLocalizationTable.country]
)
