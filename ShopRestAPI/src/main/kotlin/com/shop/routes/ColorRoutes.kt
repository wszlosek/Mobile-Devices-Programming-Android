package com.shop.routes

import com.shop.models.*
import com.shop.tables.ColorTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.colorSerialization() {
    getColor()
    postColor()
    putColor()
    deleteColor()
}

private fun Application.getColor() {
    routing {
        get("/color") {
            var colors = mutableListOf<Color>()
            transaction {
                colors = ColorTable.selectAll().map { it.toColor() }.toMutableList()
            }
            call.respond(colors)
        }

        get("/color/{id}") {
            val id: Int = call.parameters["id"]!!.toInt()
            var color = Color()
            transaction {
                color = ColorTable.select { ColorTable.id eq id }.map { it.toColor() }.first()
            }
            call.respond(color)
        }
    }
}

private fun Application.postColor() {
    routing {
        post("/color") {
            val color = call.receive<Color>()
            addColorToDatabase(color)
            call.respondText("Color stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putColor() {
    routing {
        put("/color/{id}") {
            val id = call.parameters["id"]
            transaction {
                ColorTable.deleteWhere { ColorTable.id eq id!!.toInt() }
            }

            val rec = call.receive<Color>()
            addColorToDatabase(rec)
        }
    }
}

private fun Application.deleteColor() {
    routing {
        delete("/color") {
            transaction {
                SchemaUtils.drop(ColorTable)
                SchemaUtils.create(ColorTable)
            }
        }

        delete("/color/{id}") {
            val id = call.parameters["id"]
            transaction {
                ColorTable.deleteWhere { ColorTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addColorToDatabase(color: Color) {
    transaction {
        ColorTable.insert {
            it[name] = color.name
        }
    }
}
