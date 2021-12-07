package com.shop.routes

import com.shop.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.shop.tables.UserTable
import org.jetbrains.exposed.sql.*
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
            var users = mutableListOf<User>()
            transaction {
                users = UserTable.selectAll().map { it.toUser() }.toMutableList()
            }
            call.respond(users)
        }

        get("/user/{id}") {
            val id: Int = call.parameters["id"]!!.toInt()
            var user = User()
            transaction {
                user = (UserTable.select { UserTable.id eq id }.map { it.toUser() })[0]
            }
            call.respond(user)
        }
    }
}

private fun Application.postUser() {

    routing {
        post("/user") {
            val user = call.receive<User>()
            addUserToDatabase(user)
            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putUser() {

    routing {
        put("/user/{id}") {
            val id = call.parameters["id"]
            transaction {
                UserTable.deleteWhere { UserTable.id eq id!!.toInt() }
            }

            val rec = call.receive<User>()
            addUserToDatabase(rec)
        }
    }
}

private fun Application.deleteUser() {

    routing {
        delete("/user") {
            transaction {
                SchemaUtils.drop(UserTable)
                SchemaUtils.create(UserTable)
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]
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
