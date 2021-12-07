package com.shop.models

import com.shop.tables.UserTable
import org.jetbrains.exposed.sql.ResultRow

data class User(
    val id: Int, val firstName: String, val surname: String,
    val localization: String, val phoneNumber: String
) {
    constructor() : this(0, "", "", "", "")
}

fun ResultRow.toUser() = User(
    id = this[UserTable.id],
    firstName = this[UserTable.firstName],
    surname = this[UserTable.surname],
    localization = this[UserTable.localization],
    phoneNumber = this[UserTable.phoneNumber]
)
