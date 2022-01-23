package com.shop.routes

import com.shop.models.*
import com.shop.tables.LoginDataTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.loginDataSerialization() {
    getLoginData()
    postLoginData()
    putLoginData()
    deleteLoginData()
}

private fun Application.getLoginData() {
    routing {
        get(loginDataSign) {
            var loginData = mutableListOf<LoginData>()
            transaction {
                loginData = LoginDataTable.selectAll().map { it.toLoginData() }.toMutableList()
            }
            call.respond(loginData)
        }

        get(loginDataIdSign) {
            val id: Int = call.parameters["id"]!!.toInt()
            var loginData = LoginData()
            transaction {
                loginData = LoginDataTable.select { LoginDataTable.id eq id }.map { it.toLoginData() }.first()
            }
            call.respond(loginData)
        }
    }
}

private fun Application.postLoginData() {
    routing {
        post(loginDataSign) {
            val loginData = call.receive<LoginData>()
            addLoginDataToDatabase(loginData)
            call.respondText("LoginData stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putLoginData() {
    routing {
        put(loginDataIdSign) {
            val id = call.parameters["id"]
            val loginData = call.receive<LoginData>()
            transaction {
                LoginDataTable.update({ LoginDataTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[login] = loginData.login
                        it[password] = loginData.password
                    }
                }
            }
        }
    }
}

private fun Application.deleteLoginData() {
    routing {
        delete(loginDataSign) {
            transaction {
                SchemaUtils.drop(LoginDataTable)
                SchemaUtils.create(LoginDataTable)
            }
        }

        delete("/loginData/{id}") {
            val id = call.parameters["id"]
            transaction {
                LoginDataTable.deleteWhere { LoginDataTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addLoginDataToDatabase(loginData: LoginData) {
    transaction {
        LoginDataTable.insert {
            it[login] = loginData.login
            it[password] = loginData.password
        }
    }
}
