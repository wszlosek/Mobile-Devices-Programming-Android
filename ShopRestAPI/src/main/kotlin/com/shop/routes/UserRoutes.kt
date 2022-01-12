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
        get(userSign) {
            var users = mutableListOf<User>()
            transaction {
                users = UserTable.selectAll().map { it.toUser() }.toMutableList()
            }
            call.respond(users)
        }

        get(userIdSign) {
            val id: Int = call.parameters["id"]!!.toInt()
            var user = User()
            transaction {
                user = UserTable.select { UserTable.id eq id }.map { it.toUser() }.first()
            }
            call.respond(user)
        }
    }
}

private fun Application.postUser() {
    routing {
        post(userSign) {
            val user = call.receive<User>()
            addUserToDatabase(user)
            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putUser() {
    routing {
        put(userIdSign) {
            val id = call.parameters["id"]
            val user = call.receive<User>()
            transaction {
                UserTable.update({ UserTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[firstName] = user.firstName
                        it[surname] = user.surname
                        it[localization] = user.localization
                        it[phoneNumber] = user.phoneNumber
                    }
                }
            }
        }
    }
}

private fun Application.deleteUser() {
    routing {
        delete(userSign) {
            transaction {
                SchemaUtils.drop(UserTable)
                SchemaUtils.create(UserTable)
            }
        }

        delete(userIdSign) {
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
            it[firstName] = user.firstName
            it[surname] = user.surname
            it[localization] = user.localization
            it[phoneNumber] = user.phoneNumber
        }
    }
}
