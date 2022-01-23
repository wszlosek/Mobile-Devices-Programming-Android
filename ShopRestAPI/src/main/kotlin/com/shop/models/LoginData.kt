package com.shop.models

import com.shop.tables.LoginDataTable
import org.jetbrains.exposed.sql.ResultRow

data class LoginData(val id: Int, val login: String, val password: String) {
    constructor() : this(0, "", "")
}

const val loginDataSign = "/logindata"
const val loginDataIdSign = "/logindata/{id}"

fun ResultRow.toLoginData() = LoginData(
    id = this[LoginDataTable.id],
    login = this[LoginDataTable.login],
    password = this[LoginDataTable.password]
)