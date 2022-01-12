package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.User

/*
fun getUsers(): List<User> {
    val call: Call<List<User>> = userService.getUsers()
    call.enqueue(object : Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            val users = response.body()
            Log.d("Users", users.toString())
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun getUser(id: Int) {
    val call: Call<User> = userService.getUser(id)
    call.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            val user = response.body()
            Log.d("User", user.toString())
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.d("Read", "error")
        }
    })
}

fun addUser(userData: User) {
    userService.createUser(userData).enqueue(
        object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Create", "error")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
            }
        }
    )
}

fun updateUser(id: Int, userData: User) {
    val call: Call<User> = userService.updateUser(id, userData)
    call.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.d("Update", "error")
        }
    })
}

fun deleteUser(id: Int) {
    val call: Call<User> = userService.deleteUser(id)
    call.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.d("Delete", "error")
        }
    })
} */


suspend fun getUsers(): List<User> {
    return userService.getUsers()
}

suspend fun getUser(id: Int): User {
    return userService.getUser(id)
}

suspend fun createUser(user: User) {
    return userService.createUser(user)
}

suspend fun updateUser(id: Int, user: User) {
    return userService.updateUser(id, user)
}

suspend fun deleteUser(id: Int) {
    return userService.deleteUser(id)
}