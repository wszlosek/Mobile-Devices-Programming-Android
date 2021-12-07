package com.shop.plugins

import com.shop.routes.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
    productSerialization()
    userSerialization()
    shopLocalizationSerialization()
    colorSerialization()
    cartSerialization()
    categorySerialization()
}
