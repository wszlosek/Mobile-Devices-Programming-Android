package com.shop.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.models.User
import com.shop.models.userStorrage
import com.shop.tables.UserTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.userSerialization() {
    getUser()
    postUser()
    putUser()
    deleteUser()
}

private fun Application.getUser() {

    routing {
        get("/user") {
            call.respond(userStorrage)
        }

        get("/user/{id}") {
            val id = call.parameters["id"]
            val user: User = userStorrage.find { it.id == id!!.toInt() }!!
            call.respond(user)
        }
    }
}

private fun Application.postUser() {

    routing {
        post("/user") {
            val user = call.receive<User>()
            userStorrage.add(user)
            addUserToDatabase(user)

            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putUser() {

    routing {
        put("/user/{id}") {
            val id = call.parameters["id"]
            val user: User = userStorrage.find { it.id == id!!.toInt() }!!

            userStorrage.remove(user)
            transaction {
                UserTable.deleteWhere { UserTable.id eq id!!.toInt() }
            }

            val rec = call.receive<User>()
            userStorrage.add(rec)
            addUserToDatabase(rec)
        }
    }
}

private fun Application.deleteUser() {

    routing {
        delete("/user") {
            userStorrage.clear()
            transaction {
                SchemaUtils.drop(UserTable)
                SchemaUtils.create(UserTable)
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]
            val user: User = userStorrage.find { it.id == id!!.toInt() }!!

            userStorrage.remove(user)
            transaction {
                UserTable.deleteWhere { UserTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addUserToDatabase(user: User) {

    transaction {
        UserTable.insert {
            it[id] = user.id
            it[firstName] = user.firstName
            it[surname] = user.surname
            it[localization] = user.localization
        }
    }
}
