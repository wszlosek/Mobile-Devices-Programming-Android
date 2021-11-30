package com.shop.plugins

import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import com.shop.routes.productSerialization
import com.shop.routes.shopLocalizationSerialization
import com.shop.routes.userSerialization

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
    productSerialization()
    userSerialization()
    shopLocalizationSerialization()
}
